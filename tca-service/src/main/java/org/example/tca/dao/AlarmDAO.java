package org.example.tca.dao;

import org.example.tca.api.Alarm;
import org.example.tca.api.Rule;
import org.example.tca.exception.AlarmException;

public interface AlarmDAO {

    Alarm get(Rule rule, Long id);

    long add(Rule rule, Alarm alarm) throws AlarmException;

    void update(Rule rule, Long id, Alarm alarm) throws AlarmException;

    void delete(Rule rule, Long id) throws AlarmException;

    void deleteAll(Rule rule) throws AlarmException;
}
