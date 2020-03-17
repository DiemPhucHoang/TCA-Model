package org.example.tca.service;

import org.example.tca.exception.ModelException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.vo.ThresholdVO;

import java.util.List;

public interface ThresholdService {

    List<ThresholdVO> getThresholds(String name, String family) throws ModelException;

    ThresholdVO getThreshold(String name, String family, String objectType, String tcaLabel) throws ModelException;

    void addThreshold(String name, String family, ThresholdVO thresholdVO) throws ModelException, ThresholdException;

    void updateThreshold(String name, String family, String objectType, String tcaLabel, ThresholdVO thresholdVO) throws ModelException, ThresholdException;

    void deleteThreshold(String name, String family, String objectType, String tcaLabel) throws ModelException, ThresholdException;

    void deleteAllThreshold(String name, String family) throws ModelException;
}
