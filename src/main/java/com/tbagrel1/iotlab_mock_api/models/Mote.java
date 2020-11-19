package com.tbagrel1.iotlab_mock_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tbagrel1.iotlab_mock_api.Utils;
import com.tbagrel1.iotlab_mock_api.forms.MoteForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "motes")
public class Mote {
    public static final String LABEL = "light1";

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "timestamp", nullable = false)
    private Long timestamp;

    @JsonProperty("mote")
    public String getId() {
        return id;
    }

    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("label")
    public String getLabel() {
        return LABEL;
    }

    public Mote() {
        this.id = null;
        this.value = null;
        this.timestamp = System.currentTimeMillis();
    }

    public Mote(String id) {
        this.id = id;
        this.value = null;
        this.timestamp = System.currentTimeMillis();
    }

    @JsonIgnore
    public void updateWithForm(MoteForm form) {
        Utils.ensureNotNull("value", form.value);
        this.value = form.value;
        this.timestamp = System.currentTimeMillis();
    }
}
