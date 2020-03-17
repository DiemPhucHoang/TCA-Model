package org.example.tca.dao;

import org.example.tca.api.Model;
import org.example.tca.exception.ModelException;

import java.util.List;

public interface ModelDAO {

    List<Model> list();

    Model get(String name, String family);

    long add(Model model) throws ModelException;

    void update(String name, String family, Model model) throws ModelException ;

    void delete(String name, String family) throws ModelException ;

    void deleteAll() throws ModelException;
}
