package org.example.tca.vo;

import org.example.tca.api.Model;
import org.example.tca.util.ModelUtil;

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

    public ModelVO() {
    }

    public ModelVO(Model model) {
        this.name = model.getName();
        this.family = model.getFamily();
        this.version = model.getVersion();
        this.description = model.getDescription();
        this.build = model.getBuild();
        this.date = ModelUtil.parseTime(model.getDate());
        this.author = model.getAuthor();
        this.activationTime = ModelUtil.parseTime(model.getActivationTime());
        this.noOfTCAs = model.getThresholds() == null ? 0 : model.getThresholds().size();
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

    @Override
    public String toString() {
        return "ModelVO{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", build='" + build + '\'' +
                ", date=" + date +
                ", author='" + author + '\'' +
                ", activationTime=" + activationTime +
                '}';
    }
}
