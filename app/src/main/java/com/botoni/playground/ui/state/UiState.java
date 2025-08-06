package com.botoni.playground.ui.state;

import com.botoni.playground.ui.model.Extract;

import java.math.BigDecimal;
import java.util.List;

public class UiState {
    private final BigDecimal value;
    private final List<Extract> extracts;


    public UiState(BigDecimal value, List<Extract> extracts) {
        this.value = value;
        this.extracts = extracts;
    }

    public BigDecimal getValue() {
        return value;
    }

    public List<Extract> getExtracts() {
        return extracts;
    }
}
