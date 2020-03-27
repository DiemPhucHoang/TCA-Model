package org.example.tca.dao.impl;

import org.example.tca.api.Condition;
import org.example.tca.api.Rule;
import org.example.tca.dao.ConditionDAO;
import org.example.tca.exception.ConditionExeption;
import org.example.tca.persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ConditionDAOImpl implements ConditionDAO {

    private EntityManager m_entityManager;

    public ConditionDAOImpl(PersistenceUtil persistenceUtil) {
        this.m_entityManager = persistenceUtil.getEntityManager();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Condition> list(Rule rule) {
        TypedQuery<Condition> query = m_entityManager.createQuery("select c from Condition c " +
                "where c.rule = :rule", Condition.class);
        query.setParameter("rule", rule);
        return query.getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Condition get(Rule rule, String attributeName) {
        TypedQuery<Condition> query = m_entityManager.createQuery("select c from Condition c " +
                "where c.attributeName = :attributeName " +
                "and c.rule = :rule", Condition.class);
        query.setParameter("attributeName", attributeName);
        query.setParameter("rule", rule);
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public long add(Rule rule, Condition condition) throws ConditionExeption {
        Condition conditionDB = get(rule, condition.getAttributeName());
        if (conditionDB != null) {
            throw new ConditionExeption("Condition with attributeName '" + condition.getAttributeName() + "' already exists");
        }

        try {
            condition.setRule(rule);
            m_entityManager.persist(condition);
            Object id = m_entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(condition);
            if (id instanceof Long) {
                return (long) id;
            } else {
                throw new ConditionExeption("Could not get condition id");
            }
        } catch (Exception e) {
            throw new ConditionExeption(e.getMessage());
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void update(Rule rule, String attributeName, Condition condition) throws ConditionExeption {
        Condition conditionDB = get(rule, condition.getAttributeName());
        if (conditionDB == null) {
            throw new ConditionExeption("Condition with attributeName '" + condition.getAttributeName() + "' does not exist");
        }
        try {
            updateConditionExits(conditionDB, condition, rule, attributeName);
            m_entityManager.merge(conditionDB);
        } catch (Exception e) {
            throw new ConditionExeption(e.getMessage());
        }
    }
    private void updateConditionExits(Condition conditionDB, Condition condition, Rule rule, String attributeName) {
        if (condition == null) {
            throw new ConditionExeption("Invalid condition info");
        }
        if (!attributeName.equals(condition.getAttributeName())) {
            throw new ConditionExeption("Condition with attributeName are not allowed to edit");
        }
        conditionDB.setRule(rule);
//        conditionDB.setAttributeName(condition.getAttributeName());
        conditionDB.setAttributeGuiName(condition.getAttributeGuiName());
        conditionDB.setObjectType(condition.getObjectType());
        conditionDB.setOperator(condition.getOperator());
        conditionDB.setDefaultValue(condition.getDefaultValue());
        conditionDB.setClearOperator(condition.getClearOperator());
        conditionDB.setClearDefaultValue(condition.getClearDefaultValue());
        conditionDB.setRate(condition.getRate());
        conditionDB.setCounterMax(condition.getCounterMax());
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void delete(Rule rule, String attributeName) throws ConditionExeption {
        Condition conditionDB = get(rule, attributeName);
        if (conditionDB == null) {
            throw new ConditionExeption("Condition with attributeName '" + attributeName + "' does not exist");
        }
        try {
            m_entityManager.remove(conditionDB);
        } catch (Exception e) {
            throw new ConditionExeption(e.getMessage());
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteAll(Rule rule) throws ConditionExeption {
        try {
            Query query = m_entityManager.createQuery("DELETE FROM Condition WHERE rule = :rule");
            query.setParameter("rule", rule).executeUpdate();
        } catch (Exception e) {
            throw new ConditionExeption(e.getMessage());
        }
    }
}
