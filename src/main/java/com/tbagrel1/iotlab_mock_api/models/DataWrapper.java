package com.tbagrel1.iotlab_mock_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataWrapper {
    private final List<Mote> motes;

    public DataWrapper(List<Mote> motes) {
        this.motes = motes;
    }

    @JsonProperty("data")
    public List<Mote> getMotes() {
        return motes;
    }
}
