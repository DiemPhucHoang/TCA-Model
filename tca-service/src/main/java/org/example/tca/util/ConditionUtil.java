package org.example.tca.util;

import org.example.tca.api.Condition;
import org.example.tca.api.Operator;
import org.example.tca.exception.RuleException;
import org.example.tca.vo.ConditionVO;

public class ConditionUtil {
    public static Condition parseConditionVOToEntity(ConditionVO conditionVO) throws Exception {
        if (conditionVO == null) {
            throw new Exception("Invalid condition info");
        }

        Operator operator = null;
        if (conditionVO.getOperator() != null) {
            operator = Operator.from(conditionVO.getOperator());
            if (operator == null) {
                throw new RuleException("Value " + conditionVO.getOperator() + " is invalid Operator value");
            }
        }

        Operator clearOperator = null;
        if (conditionVO.getClearOperator() != null) {
            clearOperator = Operator.from(conditionVO.getClearOperator());
            if (clearOperator == null) {
                throw new RuleException("Value " + conditionVO.getClearOperator() + " is invalid clearOperator value");
            }
        }

        return new Condition(conditionVO.getAttributeName(), conditionVO.getAttributeGuiName(), conditionVO.getObjectType(), operator,
                conditionVO.getValue(), clearOperator, conditionVO.getClearValue(),
                conditionVO.getRate());
    }
}
