package org.example.tca.dao;

import org.example.tca.api.Condition;
import org.example.tca.api.Rule;
import org.example.tca.exception.ConditionExeption;

import java.util.List;

public interface ConditionDAO {

    List<Condition> list(Rule rule);

    Condition get(Rule rule, String attributeName);

    long add(Rule rule, Condition condition) throws ConditionExeption;

    void update(Rule rule, String attributeName, Condition condition) throws ConditionExeption;

    void delete(Rule rule, String attributeName) throws ConditionExeption;

    void deleteAll(Rule rule) throws ConditionExeption;
}
