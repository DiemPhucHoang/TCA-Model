package org.example.tca.parsing;

import org.example.tca.api.Aggregator;
import org.example.tca.api.ConditionLogicalOperator;
import org.example.tca.api.Rule;
import org.example.tca.exception.RuleException;
import org.example.tca.vo.RuleVO;

public class RuleParsing {

    public static Rule parseRuleVOToEntity(RuleVO ruleVO) throws RuleException {
        if (ruleVO == null) {
            throw new RuleException("Invalid rule info");
        }

        ConditionLogicalOperator conditionLogicalOperator = null;
        if (ruleVO.getConditionLogicalOperator() != null) {
            conditionLogicalOperator = ConditionLogicalOperator.from(ruleVO.getConditionLogicalOperator());
            if (conditionLogicalOperator == null) {
                throw new RuleException ("Value " + ruleVO.getConditionLogicalOperator() + " is invalid ConditionLogicalOperator value");
            }
        }

        Aggregator aggregator = null;
        if (ruleVO.getAggregator() != null) {
            aggregator = Aggregator.from(ruleVO.getAggregator());
            if (aggregator == null) {
                throw new RuleException ("Value " + ruleVO.getAggregator() + " is invalid Aggregator value");
            }
        }

        return new Rule(conditionLogicalOperator, aggregator, ruleVO.getAggregationPeriod());
    }
}
