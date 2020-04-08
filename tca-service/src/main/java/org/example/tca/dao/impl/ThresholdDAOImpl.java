package org.example.tca.dao.impl;

import org.example.tca.api.Model;
import org.example.tca.api.Threshold;
import org.example.tca.dao.ThresholdDAO;
import org.example.tca.exception.ThresholdException;
import org.example.tca.persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ThresholdDAOImpl implements ThresholdDAO {

    private EntityManager m_entityManager;

    public ThresholdDAOImpl(PersistenceUtil persistenceUtil) {
        this.m_entityManager = persistenceUtil.getEntityManager();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Threshold> list(Model model) {
        int page = 1;
        int limit = 20;
        TypedQuery<Threshold> query = m_entityManager.createQuery("select t from Threshold t " +
                "where t.model = :model", Threshold.class);
        query.setParameter("model", model);
        query.setFirstResult((page-1) * limit);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Threshold get(Model model, String objectType, String tcaLabel) {
        TypedQuery<Threshold> query = m_entityManager.createQuery("select t from Threshold t " +
                "where t.objectType=:objectType " +
                "and t.tcaLabel=:tcaLabel " +
                "and t.model = :model", Threshold.class);
        query.setParameter("objectType", objectType);
        query.setParameter("tcaLabel", tcaLabel);
        query.setParameter("model", model);
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public long add(Model model, Threshold threshold) throws ThresholdException {
        if( get(model, threshold.getObjectType(), threshold.getTcaLabel()) != null) {
            throw  new ThresholdException("Threshold with objectType '" + threshold.getObjectType() + "' and tcaLabel '" + threshold.getTcaLabel() + "' already exists");
        }

        try {
            threshold.setModel(model);
            m_entityManager.persist(threshold);
            Object id = m_entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(threshold);
            if (id instanceof Long) {
                return (long) id;
            } else {
                throw new ThresholdException("Could not get threshold id");
            }
        } catch (Exception e) {
            throw new ThresholdException(e.getMessage());
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void delete(Model model, String objectType, String tcaLabel) throws ThresholdException {
        Threshold thresholdDB = get(model, objectType, tcaLabel);

        if (thresholdDB == null) {
            throw new ThresholdException("Threshold with objectType '" + objectType + "' and tcaLabel '" + tcaLabel + "' does not exist");
        }

        try {
            m_entityManager.remove(thresholdDB);
        } catch (Exception e) {
            throw new ThresholdException(e.getMessage());
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteAll(Model model) throws ThresholdException {
        try {
            Query query = m_entityManager.createQuery("DELETE FROM Threshold WHERE model = :model");
            query.setParameter("model", model).executeUpdate();
        } catch (Exception e) {
            throw new ThresholdException(e.getMessage());
        }
    }
}
