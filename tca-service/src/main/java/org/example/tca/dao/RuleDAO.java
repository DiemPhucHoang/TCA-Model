package org.example.tca.dao;

import org.example.tca.api.Rule;
import org.example.tca.api.Threshold;
import org.example.tca.exception.RuleException;

import java.util.List;

public interface RuleDAO {
    List<Rule> list(Threshold threshold);

    Rule get(Threshold threshold, long id) ;

    long add(Threshold threshold, Rule rule) throws RuleException;

    void update(Threshold threshold, long id, Rule rule) throws RuleException;

    void delete(Threshold threshold, long id) throws RuleException;

    void deleteAll(Threshold threshold) throws RuleException;
}
