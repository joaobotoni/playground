package com.botoni.playground.ui.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Extract implements Serializable {
    private String val;
    private String description;
    private LocalDateTime dateTime;
    private BigDecimal value;

    public Extract(String val, String description, LocalDateTime dateTime, BigDecimal value) {
        this.val = val;
        this.description = description;
        this.dateTime = dateTime;
        this.value = value;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
