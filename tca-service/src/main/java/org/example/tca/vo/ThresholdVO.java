package org.example.tca.vo;

import org.example.tca.api.Threshold;

public class ThresholdVO {

    private String objectType;

    private String tcaLabel;

    private String description;

    public ThresholdVO() {
    }

    public ThresholdVO(Threshold threshold) {
        this.objectType = threshold.getObjectType();
        this.tcaLabel = threshold.getTcaLabel();
        this.description = threshold.getDescription();
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getTcaLabel() {
        return tcaLabel;
    }

    public void setTcaLabel(String tcaLabel) {
        this.tcaLabel = tcaLabel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
