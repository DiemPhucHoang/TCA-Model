package org.example.tca.util;

import org.example.tca.api.Threshold;
import org.example.tca.vo.ThresholdVO;

public class ThresholdUtil {
    public static Threshold parseThresholdVOToEntity(ThresholdVO thresholdVO) throws Exception {
        if (thresholdVO == null) {
            throw new Exception("Invalid threshold info");
        }

        return new Threshold(thresholdVO.getObjectType(), thresholdVO.getTcaLabel(), thresholdVO.getDescription());
    }
}
