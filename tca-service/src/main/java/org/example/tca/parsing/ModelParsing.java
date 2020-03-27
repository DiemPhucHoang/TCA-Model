package org.example.tca.parsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.example.tca.api.Aggregator;
import org.example.tca.api.Alarm;
import org.example.tca.api.Condition;
import org.example.tca.api.ConditionLogicalOperator;
import org.example.tca.api.Model;
import org.example.tca.api.Operator;
import org.example.tca.api.PerceivedSeverity;
import org.example.tca.api.Threshold;
import org.example.tca.api.Rule;
import org.example.tca.vo.ModelVO;
import org.example.tca.vo.ThresholdVO;
import org.example.tca.vo.AlarmVO;
import org.example.tca.vo.ConditionVO;
import org.example.tca.vo.RuleVO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ModelParsing {

    private static final String DATE_TIME_PATTERN = "dd-MMM-yyyy HH:mm";

    private static final GsonBuilder m_gBuilder = new GsonBuilder()
            .setDateFormat(DATE_TIME_PATTERN)
            .setPrettyPrinting();

    private static final Gson GSON_MAPPER = m_gBuilder.create();

    public static Model parseModelVOToEntity(ModelVO modelVO) throws Exception {
        if (modelVO == null) {
            throw new Exception("Invalid model info");
        }

        if (modelVO.getName() == null || modelVO.getName().isEmpty()
                || modelVO.getFamily() == null || modelVO.getFamily().isEmpty()
                || modelVO.getVersion() == null || modelVO.getVersion().isEmpty()) {
            throw new Exception("Model name, family and version are mandatory");
        }

        return new Model(modelVO.getName(), modelVO.getFamily(), modelVO.getVersion(), modelVO.getDescription(),
                modelVO.getBuild(), parseTime(modelVO.getDate()), modelVO.getAuthor(), new Date());
    }

    public static Date parseTime(String time) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(DATE_TIME_PATTERN);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.parse(time);
    }

    public static String parseTime(Date date) {
        DateFormat formatter = new SimpleDateFormat(DATE_TIME_PATTERN);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

    public static ModelVO parseJsonToObject(String json) throws Exception {
        if (json == null || json.isEmpty()) {
            throw new JsonSyntaxException("The TCA model file is empty");
        }
        ModelVO modelVO = GSON_MAPPER.fromJson(json, ModelVO.class);
        if (modelVO == null) {
            return null;
        }
        if (modelVO.getName() == null || modelVO.getName().isEmpty()) {
            throw new Exception("Model name is missing or invalid");
        }
        if (modelVO.getFamily() == null || modelVO.getFamily().isEmpty()) {
            throw new Exception("Model family is missing or invalid");
        }
        if (modelVO.getVersion() == null || modelVO.getVersion().isEmpty()) {
            throw new Exception("Model version is missing or invalid");
        }
        return modelVO;
    }

    public static Model convertVOToEntity(ModelVO modelVO) {
        if (modelVO == null) {
            return null;
        }
        try {
            Model model = new Model(modelVO.getName(), modelVO.getFamily(), modelVO.getVersion(), modelVO.getDescription(),
                    modelVO.getBuild(), parseTime(modelVO.getDate()), modelVO.getAuthor(), new Date(), modelVO.getModelFileName());
            model.setThresholds(convertThresholds(modelVO.getThresholds(), model));
            return model;
        } catch (ParseException e) {
            System.out.println("Error while converting VO to entity" + e.getMessage());
            return null;
        }
    }

    private static List<Threshold> convertThresholds(List<ThresholdVO> thresholdVOs, Model model) {
        if (thresholdVOs == null || thresholdVOs.isEmpty()) {
            return null;
        }
        List<Threshold> thresholds = new ArrayList<>();
        for (ThresholdVO thresholdVO : thresholdVOs) {
            Threshold threshold = new Threshold(thresholdVO.getObjectType(), thresholdVO.getTcaLabel());
            threshold.setModel(model);
            threshold.setRules(convertRules(thresholdVO.getRules(), threshold));
            thresholds.add(threshold);
        }
        return thresholds;
    }

    private static List<Rule> convertRules(List<RuleVO> ruleVOs, Threshold threshold) {
        if (ruleVOs == null || ruleVOs.isEmpty()) {
            return null;
        }
        List<Rule> rules = new ArrayList<>();
        for (RuleVO ruleVO : ruleVOs) {
            Aggregator aggregator = ruleVO.getAggregator() == null ? null : Aggregator.from(ruleVO.getAggregator());
            ConditionLogicalOperator conditionLogicalOperator = ruleVO.getConditionLogicalOperator() == null ? null : ConditionLogicalOperator.from(ruleVO.getConditionLogicalOperator());
            Rule rule = new Rule(ruleVO.getId(), conditionLogicalOperator, aggregator, ruleVO.getAggregationPeriod());
            rule.setThreshold(threshold);
            rule.setConditions(convertConditions(ruleVO.getConditions(), rule));
            rule.setAlarm(convertAlarm(ruleVO.getAlarm(), rule));
            rules.add(rule);
        }
        return rules;
    }

    private static List<Condition> convertConditions(List<ConditionVO> conditionVOs, Rule rule) {

        if (conditionVOs == null || conditionVOs.isEmpty()) {
            return null;
        }
        List<Condition> conditions = new ArrayList<>();
        for (ConditionVO conditionVO : conditionVOs) {
            Operator operator = conditionVO.getOperator() == null ? null : Operator.from(conditionVO.getOperator());
            Operator clearOperator = conditionVO.getClearOperator() == null ? null : Operator.from(conditionVO.getClearOperator());
            conditions.add(new Condition(conditionVO.getAttributeName(), conditionVO.getAttributeGuiName(), conditionVO.getObjectType(),
                    operator, conditionVO.getDefaultValue(), clearOperator, conditionVO.getClearDefaultValue(), conditionVO.getRate(),
                    conditionVO.getCounterMax(), rule));
        }
        return conditions;
    }

    private static Alarm convertAlarm(AlarmVO alarmVO, Rule rule) {
        PerceivedSeverity perceivedSeverity = alarmVO.getPerceivedSeverity() == null ? null : PerceivedSeverity.from(alarmVO.getPerceivedSeverity());
        return alarmVO == null ? null : new Alarm(alarmVO.getAlarmTypeId(), perceivedSeverity, rule);
    }
}
