package org.example.tca.dao.impl;

import org.example.tca.api.Rule;
import org.example.tca.api.Threshold;
import org.example.tca.dao.RuleDAO;
import org.example.tca.exception.RuleException;
import org.example.tca.persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RuleDAOImpl implements RuleDAO {

    private EntityManager m_entityManager;

    public RuleDAOImpl(PersistenceUtil persistenceUtil) {
        this.m_entityManager = persistenceUtil.getEntityManager();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Rule> list(Threshold threshold) {
        int page = 1;
        int limit = 20;
        TypedQuery<Rule> query = m_entityManager.createQuery("select r from Rule r " +
                "where r.threshold = :threshold", Rule.class);
        query.setParameter("threshold", threshold);
        query.setFirstResult((page-1) * limit);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Rule get(Threshold threshold, Long id) {
        TypedQuery<Rule> query = m_entityManager.createQuery("select r from Rule r " +
                "where r.id=:id " +
                "and r.threshold = :threshold", Rule.class);
        query.setParameter("id", id);
        query.setParameter("threshold", threshold);
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public long add(Threshold threshold, Rule rule) throws RuleException {
        try {
            rule.setThreshold(threshold);
            m_entityManager.persist(rule);
            Object id = m_entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(rule);
            if (id instanceof Long) {
                return (long) id;
            } else {
                throw new RuleException("Could not get rule id");
            }
        } catch (Exception e) {
            throw new RuleException(e.getMessage());
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void update(Threshold threshold, Long id, Rule rule) throws RuleException {
        Rule ruleDB = get(threshold, id);

        if( ruleDB == null ) {
            throw new RuleException("Rule with ID '" + id + "' does not exist");
        }

        try {
            ruleDB.setThreshold(threshold);
            ruleDB.setConditionLogicalOperator(rule.getConditionLogicalOperator());
            ruleDB.setAggregator(rule.getAggregator());
            ruleDB.setAggregationPeriod(rule.getAggregationPeriod());
            m_entityManager.merge(ruleDB);
        } catch (Exception e) {
            throw new RuleException(e.getMessage());
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void delete(Threshold threshold, Long id) throws RuleException {
        Rule ruleDB = get(threshold, id);
        if( ruleDB == null ) {
            throw new RuleException("Rule with ID '" + id + "' does not exist");
        }
        try {
            m_entityManager.remove(ruleDB);
        } catch (Exception e) {
            throw new RuleException(e.getMessage());
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteAll(Threshold threshold) throws RuleException {
        try {
            Query query = m_entityManager.createQuery("DELETE FROM Rule WHERE threshold = :threshold");
            query.setParameter("threshold", threshold).executeUpdate();
        } catch (Exception e) {
            throw new RuleException(e.getMessage());
        }
    }
}
