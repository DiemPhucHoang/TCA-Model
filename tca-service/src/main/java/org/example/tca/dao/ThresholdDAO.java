package org.example.tca.dao;

import org.example.tca.api.Model;
import org.example.tca.api.Threshold;
import org.example.tca.exception.ThresholdException;

import java.util.List;

public interface ThresholdDAO {

    List<Threshold> list(Model model);

    Threshold get(Model model, String objectType, String tcaLabel);

    long add(Model model, Threshold threshold) throws ThresholdException;

    void delete(Model model, String objectType, String tcaLabel) throws ThresholdException;

    void deleteAll(Model model) throws ThresholdException;
}
