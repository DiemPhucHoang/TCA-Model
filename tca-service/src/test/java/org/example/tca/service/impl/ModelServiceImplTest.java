package org.example.tca.service.impl;

import com.google.gson.JsonSyntaxException;
import org.example.tca.api.Model;
import org.example.tca.dao.ModelDAO;
import org.example.tca.exception.ModelException;
import org.example.tca.parsing.ModelParsing;
import org.example.tca.vo.ModelVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ModelServiceImplTest {

    public static final String MODEL_NAME = "copper";
    public static final String MODEL_FAMILY_LS_DPU_CFAS_H = "LS-DPU-CFAS-H-19B.12";
    private static final String MODEL_FAMILY_LS_DPU_CFAS_P = "LS-DPU-CFAS-P-19B.12";
    public static final String MODEL_VERSION = "1.0.0";
    private static final String MODEL_DATE = "10-Mar-2020 04:30";
    private static final String MODEL_FILE_NAME = "tca-copper-19B.12.json";

    private ModelDAO m_modelDAO;
    private ModelServiceImpl m_modelService;

    @Before
    public void init() {
        m_modelDAO = mock(ModelDAO.class);
        m_modelService = new ModelServiceImpl(m_modelDAO);
    }

    @After
    public void destroy() {
        // release resources (e.g temp file or folder,...)
    }

    @Test
    public void testImportModel_ParsingException() {
        try {
            m_modelService.importModel("test", MODEL_FILE_NAME);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof JsonSyntaxException);
        }
    }

    @Test
    public void testImportModel_Exception() {
        try {
            m_modelService.importModel("{}", MODEL_FILE_NAME);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Model name is missing or invalid", e.getMessage());
        }
    }

    @Test
    public void testImportModel_2_Models() throws Exception {
        // Input: String json, String fileName
        String json = "{\n" +
                "    \"name\": \"copper\",\n" +
                "    \"family\": \"LS-DPU-CFAS-H-19B.09, LS-DPU-CFAS-P-19B.09\",\n" +
                "    \"version\": \"1.0.0\",\n" +
                "    \"description\": \"TCA model for copper devices\",\n" +
                "    \"build\": \"20.3.0-SNAPSHOT_9999\",\n" +
                "    \"date\": \"10-Feb-2020 15:41\",\n" +
                "    \"author\": \"Nokia\",\n" +
                "    \"thresholds\": [{\n" +
                "        \"objectType\": \"ietf-interfaces:interfaces/interface\",\n" +
                "        \"tcaLabel\": \"uplink\",\n" +
                "        \"rules\": [{\n" +
                "                \"conditions\": [{\n" +
                "                    \"attributeName\": \"statistics/in-octets\",\n" +
                "                    \"attributeGuiName\": \"Inbound Packets\",\n" +
                "                    \"objectType\":\"ietf-interfaces:interfaces-state/interface\",\n" +
                "                    \"operator\": \"lt\",\n" +
                "                    \"defaultValue\": \"0\",\n" +
                "                    \"rate\": \"false\"\n" +
                "                }],\n" +
                "                \"alarm\": {\n" +
                "                    \"alarmTypeId\": \"incoming-traffic-utilization-threshold-crossed-alarm\",\n" +
                "                    \"perceivedSeverity\": \"critical\"\n" +
                "                },\n" +
                "                \"aggregator\": \"avg\",\n" +
                "                \"aggregationPeriod\": \"PT1M\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }]\n" +
                "}";
        m_modelService.importModel(json, MODEL_FILE_NAME);
        verify(m_modelDAO, times(2)).add(any());
        // do family minh co 2 items cho nen minh se chay qua line m_modelDAO.add(model); 2 lan
    }

    @Test
    public void testImportModel_1_Model() throws Exception {
        // Input: String json, String fileName
        String json = "{\n" +
                "    \"name\": \"copper\",\n" +
                "    \"family\": \"LS-DPU-CFAS-H-19B.09\",\n" +
                "    \"version\": \"1.0.0\",\n" +
                "    \"description\": \"TCA model for copper devices\",\n" +
                "    \"build\": \"20.3.0-SNAPSHOT_9999\",\n" +
                "    \"date\": \"10-Feb-2020 15:41\",\n" +
                "    \"author\": \"Nokia\",\n" +
                "    \"thresholds\": [{\n" +
                "        \"objectType\": \"ietf-interfaces:interfaces/interface\",\n" +
                "        \"tcaLabel\": \"uplink\",\n" +
                "        \"rules\": [{\n" +
                "                \"conditions\": [{\n" +
                "                    \"attributeName\": \"statistics/in-octets\",\n" +
                "                    \"attributeGuiName\": \"Inbound Packets\",\n" +
                "                    \"objectType\":\"ietf-interfaces:interfaces-state/interface\",\n" +
                "                    \"operator\": \"lt\",\n" +
                "                    \"defaultValue\": \"0\",\n" +
                "                    \"rate\": \"false\"\n" +
                "                }],\n" +
                "                \"alarm\": {\n" +
                "                    \"alarmTypeId\": \"incoming-traffic-utilization-threshold-crossed-alarm\",\n" +
                "                    \"perceivedSeverity\": \"critical\"\n" +
                "                },\n" +
                "                \"aggregator\": \"avg\",\n" +
                "                \"aggregationPeriod\": \"PT1M\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }]\n" +
                "}";
        m_modelService.importModel(json, MODEL_FILE_NAME);
        verify(m_modelDAO, times(1)).add(any());
    }

    @Test
    public void testGetModels_Empty() {
        List<ModelVO> result = m_modelService.getModels();
        assertEquals(0, result.size());
    }

    @Test
    public void testGetModels_Has_1_Model() {
        List<Model> models = new ArrayList<Model>() {{
            add(buildModel(MODEL_FAMILY_LS_DPU_CFAS_H));
        }};
        when(m_modelDAO.list()).thenReturn(models);
        List<ModelVO> result = m_modelService.getModels();
        assertEquals(1, result.size());
    }

    @Test
    public void testGetModels_Has_Multi_Models() {
        List<Model> models = new ArrayList<Model>() {{
            add(buildModel(MODEL_FAMILY_LS_DPU_CFAS_H));
            add(buildModel(MODEL_FAMILY_LS_DPU_CFAS_P));
        }};
        when(m_modelDAO.list()).thenReturn(models);
        List<ModelVO> result = m_modelService.getModels();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetModel_Not_Exists() {
        when(m_modelDAO.get(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H)).thenReturn(null);
        ModelVO modelVO = m_modelService.getModel(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H);
        assertNull(modelVO);
    }

    @Test
    public void testGetModel_Exists() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H)).thenReturn(model);
        ModelVO modelVO = m_modelService.getModel(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H);
        assertNotNull(modelVO);
        assertEquals(MODEL_NAME, modelVO.getName());
        assertEquals(MODEL_FAMILY_LS_DPU_CFAS_H, modelVO.getFamily());
        assertEquals(MODEL_VERSION, modelVO.getVersion());
    }

    @Test
    public void testUpdateModel_Exception() throws Exception {
        ModelVO modelVO = new ModelVO();
        modelVO.setName(MODEL_NAME);
        modelVO.setFamily(MODEL_FAMILY_LS_DPU_CFAS_H);
        modelVO.setVersion(MODEL_VERSION);
        modelVO.setDate(MODEL_DATE);

        Model model = ModelParsing.parseModelVOToEntity(modelVO);

        m_modelService = new ModelServiceImpl(m_modelDAO) {
            @Override
            protected Model parseModelVOToEntity(ModelVO modelVO) throws Exception {
                return model;
            }
        };

        doThrow(new ModelException("Update model exception")).when(m_modelDAO).update(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H,model);

        try {
            m_modelService.updateModel(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H, modelVO);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Update model exception", e.getMessage());
        }
    }

    @Test
    public void testUpdateModel() throws Exception {
        ModelVO modelVO = new ModelVO();
        modelVO.setName(MODEL_NAME);
        modelVO.setFamily(MODEL_FAMILY_LS_DPU_CFAS_H);
        modelVO.setVersion(MODEL_VERSION);
        modelVO.setDate(MODEL_DATE);

        Model model = ModelParsing.parseModelVOToEntity(modelVO);

        m_modelService = new ModelServiceImpl(m_modelDAO) {
            @Override
            protected Model parseModelVOToEntity(ModelVO modelVO) throws Exception {
                return model;
            }
        };

        m_modelService.updateModel(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H, modelVO);
        verify(m_modelDAO, times(1)).update(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H, model);
    }

    @Test
    public void testDeleteModel() {
        m_modelService.deleteModel(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H);
        verify(m_modelDAO, times(1)).delete(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H);
    }

    @Test
    public void testDeleteModel_Exception() {
        doThrow(new ModelException("Delete model exception")).when(m_modelDAO).delete(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H);

        try {
            m_modelService.deleteModel(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Delete model exception", e.getMessage());
        }
    }

    @Test
    public void testDeleteAllModel() {
        m_modelService.deleteAllModel();
        verify(m_modelDAO).deleteAll();
    }

    @Test
    public void testDeleteAllModel_Exception() {
        doThrow(new ModelException("Delete all model exception")).when(m_modelDAO).deleteAll();

        try {
            m_modelService.deleteAllModel();
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Delete all model exception", e.getMessage());
        }
    }

    public static Model buildModel(String family) {
        return new Model(MODEL_NAME, family, MODEL_VERSION, "TCA model for copper devices",
                "20.3.0-SNAPSHOT_9024", new Date(), "Nokia", new Date());
    }
}
