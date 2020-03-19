package org.example.tca.dao.impl;

import org.example.tca.api.Model;
import org.example.tca.dao.ModelDAO;
import org.example.tca.exception.ModelException;
import org.example.tca.persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public class ModelDAOImpl implements ModelDAO {

    private EntityManager m_entityManager;

    public ModelDAOImpl(PersistenceUtil persistenceUtil) {
        this.m_entityManager = persistenceUtil.getEntityManager();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Model> list() {
        TypedQuery<Model> query = m_entityManager.createQuery("SELECT m FROM Model m", Model.class);
        return query.getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Model get(String name, String family) {
        TypedQuery<Model> query = m_entityManager.createQuery("SELECT m FROM Model m " +
                "WHERE m.name=:name " +
                "and m.family=:family", Model.class);
        query.setParameter("name", name);
        query.setParameter("family", family);
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public long add(Model model) throws ModelException {
        Model modelDB = get(model.getName(), model.getFamily());
        if (modelDB != null) {
            throw new ModelException("Model with name '" + model.getName() + "' and family '" + model.getFamily() + "' already exists");
        }
        try {
            model.setActivationTime(new Date());
            m_entityManager.persist(model);
            Object id = m_entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(model);
            if (id instanceof Long) {
                return (long) id;
            } else {
                throw new ModelException("Could not get model id");
            }
        } catch (Exception e) {
            throw new ModelException(e.getMessage());
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void update(String name, String family, Model model) throws ModelException {
        Model modelDB = get(name, family);
        if (modelDB == null) {
            throw new ModelException("Model with name '" + name + "' and family '" + family + "' does not exists");
        }
        try {
            updateExistsModel(modelDB, model, name, family);
            m_entityManager.merge(modelDB);
        } catch (Exception e) {
            throw new ModelException(e.getMessage());
        }
    }

    private void updateExistsModel(Model modelDB, Model model, String name, String family) throws ModelException {
        if (model == null) {
            throw new ModelException("Invalid model info");
        }
        if (!name.equals(model.getName()) || !family.equals(model.getFamily())) {
            throw new ModelException("Model name or family are not allowed to edit");
        }
        modelDB.setAuthor(model.getAuthor());
        modelDB.setDescription(model.getDescription());
        modelDB.setBuild(model.getBuild());
        modelDB.setVersion(model.getVersion());
//        modelDB.setDate(model.getDate());
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void delete(String name, String family) throws ModelException {
        Model modelDB = get(name, family);
        if (modelDB == null) {
            throw new ModelException("Model with name '" + name + "' and family '" + family + "' does not exists");
        }
        try {
            m_entityManager.remove(modelDB);
        } catch (Exception e) {
            throw new ModelException(e.getMessage());
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteAll() throws ModelException {
        try {
            Query query = m_entityManager.createQuery("DELETE Model");
            query.executeUpdate();
        } catch (Exception e) {
            throw new ModelException(e.getMessage());
        }
    }
}
