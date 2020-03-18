package org.example.tca.util;

import org.example.tca.api.Condition;
import org.example.tca.vo.ConditionVO;

public class ConditionUtil {
    public static Condition parseConditionVOToEntity(ConditionVO conditionVO) throws Exception {
        if (conditionVO == null) {
            throw new Exception("Invalid condition info");
        }

        return new Condition(conditionVO.getAttributeName(), conditionVO.getAttributeGuiName(), conditionVO.getObjectType(), conditionVO.getOperator(),
                conditionVO.getValue(), conditionVO.getClearOperator(), conditionVO.getClearValue(), conditionVO.getRate(), conditionVO.getCounterMax());
    }
}
