package org.example.tca.api;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity (name = "Model")
@Table (name = "tca_model", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "family"})})
public class Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "family", nullable = false, length = 128)
    private String family;

    @Column(name = "version", nullable = false, length = 5)
    private String version;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "build", length = 128)
    private String build;

    @Column(name = "date")
    private Date date;

    @Column(name = "author", length = 128)
    private String author;

    @Column(name = "activation_time")
    private Date activationTime;

    @Column(name = "model_file_name", length = 255)
    private String modelFileName;

    @OneToMany(mappedBy = "model", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Threshold> thresholds = new ArrayList<>();

    public Model() {
    }

    public Model(String name, String family, String version, String description, String build,
                 Date date, String author, Date activationTime) {
        this.name = name;
        this.family = family;
        this.version = version;
        this.description = description;
        this.build = build;
        this.date = date;
        this.author = author;
        this.activationTime = activationTime ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(Date activationTime) {
        this.activationTime = activationTime;
    }

    public String getModelFileName() {
        return modelFileName;
    }

    public void setModelFileName(String modelFileName) {
        this.modelFileName = modelFileName;
    }

    public List<Threshold> getThresholds() {
        return thresholds;
    }

    public void setThresholds(List<Threshold> thresholds) {
        this.thresholds = thresholds;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", build='" + build + '\'' +
                ", date=" + date +
                ", author='" + author + '\'' +
                ", activationTime=" + activationTime +
                ", modelFileName='" + modelFileName +
                '}';
    }
}
