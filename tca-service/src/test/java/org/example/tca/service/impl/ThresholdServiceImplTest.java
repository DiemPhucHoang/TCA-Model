package org.example.tca.service.impl;

import org.example.tca.api.Model;
import org.example.tca.api.Threshold;
import org.example.tca.dao.ModelDAO;
import org.example.tca.dao.ThresholdDAO;
import org.example.tca.exception.ModelException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.parsing.ThresholdParsing;
import org.example.tca.vo.ThresholdVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.tca.service.impl.ModelServiceImplTest.MODEL_FAMILY_LS_DPU_CFAS_H;
import static org.example.tca.service.impl.ModelServiceImplTest.MODEL_NAME;
import static org.example.tca.service.impl.ModelServiceImplTest.buildModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ThresholdServiceImplTest {

    public static final String THRESHOLD_OBJECT_TYPE_INTERFACE = "ietf-interfaces:interfaces-state/interface";
    public static final String THRESHOLD_OBJECT_TYPE_AAA = "ietf-interfaces:interfaces-state/aaa";
    public static final String THRESHOLD_TCA_LABEL = "uplink";

    private ModelDAO m_modelDAO;
    private ThresholdDAO m_thresholdDAO;
    private ThresholdServiceImpl m_thresholdService;

    @Before
    public void init() {
        m_modelDAO = mock(ModelDAO.class);
        m_thresholdDAO = mock(ThresholdDAO.class);
        m_thresholdService = new ThresholdServiceImpl(m_modelDAO, m_thresholdDAO);
    }

    @After
    public void destroy() {

    }

    @Test
    public void testGetThresholds_Empty() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        List<ThresholdVO> result = m_thresholdService.getThresholds(model.getName(), model.getFamily());

        assertEquals(0, result.size());
    }

    @Test
    public void testGetThresholds_Has_1_Threshold() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        List<Threshold> thresholds = new ArrayList<Threshold>() {{
            add(buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE));
        }};

        when(m_thresholdDAO.list(model)).thenReturn(thresholds);
        List<ThresholdVO> result = m_thresholdService.getThresholds(model.getName(), model.getFamily());
        assertEquals(1, result.size());
    }

    @Test
    public void testGetThresholds_Has_Multi_Thresholds() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        List<Threshold> thresholds = new ArrayList<Threshold>() {{
            add(buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE));
            add(buildThreshold(model, THRESHOLD_OBJECT_TYPE_AAA));
        }};

        when(m_thresholdDAO.list(model)).thenReturn(thresholds);
        List<ThresholdVO> result = m_thresholdService.getThresholds(model.getName(), model.getFamily());
        assertEquals(2, result.size());
    }

    @Test
    public void testGetThresholds_Exception_Model () {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(null);

        try {
            m_thresholdService.getThresholds(model.getName(), model.getFamily());
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Model with name '" + model.getName() + "' and family '" + model.getFamily() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testGetThresholds_Exception_Threshold () {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(null);

        try {
            m_thresholdService.getThresholds(model.getName(), model.getFamily());
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Model with name '" + model.getName() + "' and family '" + model.getFamily() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testGetThreshold__Not_Exists() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        when(m_thresholdDAO.get(model, THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL)).thenReturn(null);

        ThresholdVO thresholdVO = m_thresholdService.getThreshold(model.getName(), model.getFamily(), THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL);
        assertNull(thresholdVO);
    }

    @Test
    public void testGetThreshold__Exists() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL)).thenReturn(threshold);

        ThresholdVO thresholdVO = m_thresholdService.getThreshold(model.getName(), model.getFamily(), THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL);
        assertNotNull(thresholdVO);
        assertEquals(THRESHOLD_OBJECT_TYPE_INTERFACE, thresholdVO.getObjectType());
        assertEquals(THRESHOLD_TCA_LABEL, thresholdVO.getTcaLabel());
    }

    @Test
    public void testAddThreshold() throws Exception {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        ThresholdVO thresholdVO = new ThresholdVO(THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL);

        Threshold threshold = ThresholdParsing.parseThresholdVOToEntity(thresholdVO);

        m_thresholdService = new ThresholdServiceImpl(m_modelDAO, m_thresholdDAO) {
            @Override
            protected Threshold parseThresholdVOToEntity(ThresholdVO thresholdVO) {
                return threshold;
            }
        };

        m_thresholdService.addThreshold(model.getName(), model.getFamily(), thresholdVO);
        verify(m_thresholdDAO, times(1)).add(model, threshold);
    }

    @Test
    public void testAddThreshold_Exception_Model() throws Exception {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(null);

        ThresholdVO thresholdVO = new ThresholdVO(THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL);

        Threshold threshold = ThresholdParsing.parseThresholdVOToEntity(thresholdVO);

        m_thresholdService = new ThresholdServiceImpl(m_modelDAO, m_thresholdDAO) {
            @Override
            protected Threshold parseThresholdVOToEntity(ThresholdVO thresholdVO) {
                return threshold;
            }
        };

        try {
            m_thresholdService.addThreshold(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H, thresholdVO);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Model with name '" + model.getName() + "' and family '" + model.getFamily() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testAddThreshold_Exception_Threshold() throws Exception {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        ThresholdVO thresholdVO = new ThresholdVO(THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL);

        Threshold threshold = ThresholdParsing.parseThresholdVOToEntity(thresholdVO);

        m_thresholdService = new ThresholdServiceImpl(m_modelDAO, m_thresholdDAO) {
            @Override
            protected Threshold parseThresholdVOToEntity(ThresholdVO thresholdVO) {
                return threshold;
            }
        };

        doThrow(new ThresholdException("Add threshold exception")).when(m_thresholdDAO).add(model, threshold);

        try {
            m_thresholdService.addThreshold(MODEL_NAME, MODEL_FAMILY_LS_DPU_CFAS_H, thresholdVO);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ThresholdException);
            assertEquals("Add threshold exception", e.getMessage());
        }
    }

    @Test
    public void testDeleteThreshold() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        m_thresholdService.deleteThreshold(model.getName(), model.getFamily(), THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL);
        verify(m_thresholdDAO, times(1)).delete(model, THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL);
    }

    @Test
    public void testDeleteThreshold_Exception_Model() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(null);

        try {
            m_thresholdService.deleteThreshold(model.getName(), model.getFamily(), THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Model with name '" + model.getName() + "' and family '" + model.getFamily() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testDeleteThreshold_Exception_Threshold() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        doThrow(new ThresholdException("Delete threshold exception")).when(m_thresholdDAO).delete(model, THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL);

        try {
            m_thresholdService.deleteThreshold(model.getName(), model.getFamily(), THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ThresholdException);
            assertEquals("Delete threshold exception", e.getMessage());
        }
    }

    @Test
    public void testDeleteAllThreshold() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        m_thresholdService.deleteAllThreshold(model.getName(), model.getFamily());
        verify(m_thresholdDAO).deleteAll(model);
    }

    @Test
    public void testDeleteAllThreshold_Exception_Model() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(null);

        try {
            m_thresholdService.deleteAllThreshold(model.getName(), model.getFamily());
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Model with name '" + model.getName() + "' and family '" + model.getFamily() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testDeleteAllThreshold_Exception_Threshold() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        doThrow(new ThresholdException("Delete all model exception")).when(m_thresholdDAO).deleteAll(model);

        try {
            m_thresholdService.deleteAllThreshold(model.getName(), model.getFamily());
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ThresholdException);
            assertEquals( "Delete all model exception", e.getMessage());
        }
    }

    public static Threshold buildThreshold (Model model, String objectType) {
        Threshold threshold = new Threshold(objectType, THRESHOLD_TCA_LABEL);
        threshold.setModel(model);
        return threshold;
    }
}
