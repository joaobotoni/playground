package com.botoni.playground.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import com.botoni.playground.ui.states.UiState;
import com.botoni.playground.ui.models.Extract;

import java.util.List;
import java.math.BigDecimal;


public class MainViewModel extends ViewModel {
    private final MutableLiveData<UiState> uiState = new MutableLiveData<>(new UiState(BigDecimal.ZERO, null));

    public LiveData<UiState> getUiState() {
        return uiState;
    }

    public void updateBalanceTotalState(BigDecimal value, List<Extract> extracts) {
        for (Extract extract : extracts) {
            value = value.add(extract.getValue());
        }
        uiState.setValue(new UiState(value, extracts));
    }
}
