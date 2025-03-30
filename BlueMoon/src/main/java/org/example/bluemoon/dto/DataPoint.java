package org.example.bluemoon.dto;

public class DataPoint {
    private String label;
    private Long value;

    public DataPoint(String label, Long value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Long getValue() {
        return value;
    }
}
