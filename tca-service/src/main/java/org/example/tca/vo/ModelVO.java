package org.example.tca.vo;

import org.example.tca.api.Model;
import org.example.tca.parsing.ModelParsing;

import java.util.List;

public class ModelVO {

    private String name;
    private String family;
    private String version;
    private String description;
    private String build;
    private String date;
    private String author;
    private String activationTime;
    private int noOfTCAs;
    private String modelFileName;
    private List<ThresholdVO> thresholds;

    public ModelVO() {
    }

    public ModelVO(Model model) {
        this.name = model.getName();
        this.family = model.getFamily();
        this.version = model.getVersion();
        this.description = model.getDescription();
        this.build = model.getBuild();
        this.date = ModelParsing.parseTime(model.getDate());
        this.author = model.getAuthor();
        this.activationTime = ModelParsing.parseTime(model.getActivationTime());
        this.modelFileName = model.getModelFileName();
        this.noOfTCAs = model.getThresholds() == null ? 0 : model.getThresholds().size();
    }

    public ModelVO(ModelVO modelVO) {
        this.name = modelVO.getName();
        this.family = modelVO.getFamily();
        this.version = modelVO.getVersion();
        this.description = modelVO.getDescription();
        this.build = modelVO.getBuild();
        this.date = modelVO.getDate();
        this.author = modelVO.getAuthor();
        this.activationTime = modelVO.getActivationTime();
        this.modelFileName = modelVO.getModelFileName();
        this.thresholds = modelVO.getThresholds();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(String activationTime) {
        this.activationTime = activationTime;
    }

    public int getNoOfTCAs() {
        return noOfTCAs;
    }

    public void setNoOfTCAs(int noOfTCAs) {
        this.noOfTCAs = noOfTCAs;
    }

    public String getModelFileName() {
        return modelFileName;
    }

    public void setModelFileName(String modelFileName) {
        this.modelFileName = modelFileName;
    }

    public List<ThresholdVO> getThresholds() {
        return thresholds;
    }

    public void setThresholds(List<ThresholdVO> thresholds) {
        this.thresholds = thresholds;
    }

    @Override
    public String toString() {
        return "ModelVO{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", build='" + build + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", activationTime='" + activationTime + '\'' +
                ", noOfTCAs=" + noOfTCAs +
                '}';
    }
}
