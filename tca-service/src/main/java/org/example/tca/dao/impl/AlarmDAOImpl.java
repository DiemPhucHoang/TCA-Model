package org.example.tca.dao.impl;

import org.example.tca.api.Alarm;
import org.example.tca.api.Rule;
import org.example.tca.dao.AlarmDAO;
import org.example.tca.exception.AlarmException;
import org.example.tca.persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional
public class AlarmDAOImpl implements AlarmDAO {
    private EntityManager m_entityManager;

    public AlarmDAOImpl(PersistenceUtil persistenceUtil) {
        this.m_entityManager = persistenceUtil.getEntityManager();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Alarm get(Rule rule, Long id) {
        TypedQuery<Alarm> query = m_entityManager.createQuery("select a from Alarm a " +
                "where a.id = :id "+
                "and a.rule = :rule", Alarm.class);
        query.setParameter("id", id);
        query.setParameter("rule", rule);
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public long add(Rule rule, Alarm alarm) throws AlarmException {
        try {
            alarm.setRule(rule);
            m_entityManager.persist(alarm);
            Object id = m_entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(alarm);
            if (id instanceof Long) {
                return (long) id;
            } else {
                throw new AlarmException("Could not get alarm id");
            }
        } catch (Exception e) {
            throw new AlarmException(e.getMessage());
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void update(Rule rule, Long id, Alarm alarm) throws AlarmException {
        Alarm alarmDB = get(rule, id);
        if (alarmDB == null) {
            throw new AlarmException("Alarm with ID '" + id + " does not exist");
        }
        try {
            alarmDB.setRule(rule);
            alarmDB.setAlarmTypeId(alarm.getAlarmTypeId());
            alarmDB.setPerceivedSeverity(alarm.getPerceivedSeverity());
            m_entityManager.merge(alarmDB);
        } catch (Exception e) {
            throw new AlarmException(e.getMessage());
        }
    }

    @Override
    public void delete(Rule rule, Long id) throws AlarmException {
        Alarm alarmDB = get(rule, id);
        if (alarmDB == null) {
            throw new AlarmException("Alarm with ID '" + id + " does not exist");
        }

        try {
            m_entityManager.remove(alarmDB);
        } catch (Exception e) {
            throw new AlarmException(e.getMessage());
        }
    }

    @Override
    public void deleteAll(Rule rule) throws AlarmException {
        try {
            Query query = m_entityManager.createQuery("DELETE FROM Alarm WHERE rule = :rule");
            query.setParameter("rule", rule).executeUpdate();
        } catch (Exception e) {
            throw new AlarmException(e.getMessage());
        }
    }
}
