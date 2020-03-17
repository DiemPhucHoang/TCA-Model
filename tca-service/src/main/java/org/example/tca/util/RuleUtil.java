package org.example.tca.util;

import org.example.tca.api.Rule;
import org.example.tca.vo.RuleVO;

public class RuleUtil {
    public static Rule parseRuleVOToEntity(RuleVO ruleVO) throws Exception {
        if (ruleVO == null) {
            throw new Exception("Invalid rule info");
        }

        return new Rule(ruleVO.getConditionLogicalOperator(), ruleVO.getAggregation(), ruleVO.getAggregationPeriod());
    }
}
