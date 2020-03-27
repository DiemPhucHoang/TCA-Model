package org.example.tca.api;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "Threshold")
@Table (name = "tca_threshold", uniqueConstraints = {@UniqueConstraint(columnNames = {"object_type", "tca_label", "model_id"})})
public class Threshold implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column(name = "object_type", length = 255)
    private String objectType;

    @Column(name = "tca_label", length = 65)
    private String tcaLabel;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @OneToMany(mappedBy = "threshold", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Rule> rules = new ArrayList<>();

    public Threshold() {
    }

    public Threshold(String objectType, String tcaLabel) {
        this.objectType = objectType;
        this.tcaLabel = tcaLabel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
