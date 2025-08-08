package com.botoni.playground.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.botoni.playground.R;
import com.botoni.playground.utils.Format;
import com.botoni.playground.ui.models.Extract;
import com.botoni.playground.ui.adapters.ExtractAdapter;
import com.botoni.playground.ui.viewmodels.MainViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MainFragment extends Fragment implements ExtractAdapter.OnItemClickListener {

    private TextView value;
    private RecyclerView list;
    private List<Extract> extracts;
    private View indicator;
    private LineChart lineChart;

    private double totalValue;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extracts = new ArrayList<>();
        extracts.add(new Extract("Valor em conta", "Investimento 1", LocalDateTime.now(), new BigDecimal("230.19")));
        extracts.add(new Extract("Valor em conta", "Investimento 2", LocalDateTime.now(), new BigDecimal("221.91")));
        extracts.add(new Extract("Valor em conta", "Investimento 3", LocalDateTime.now(), new BigDecimal("258.39")));
        extracts.add(new Extract("Valor em conta", "Investimento 4", LocalDateTime.now(), new BigDecimal("339.79")));
        extracts.add(new Extract("Valor em conta", "Investimento 5", LocalDateTime.now(), new BigDecimal("-339.79")));
        extracts.add(new Extract("Valor em conta", "Investimento 6", LocalDateTime.now(), new BigDecimal("-339.79")));
        extracts.add(new Extract("Valor em conta", "Investimento 7", LocalDateTime.now(), new BigDecimal("-339.79")));
        extracts.add(new Extract("Valor em conta", "Investimento 8", LocalDateTime.now(), new BigDecimal("-339.79")));
        extracts.add(new Extract("Valor em conta", "Investimento 9", LocalDateTime.now(), new BigDecimal("-339.79")));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        value = view.findViewById(R.id.valor);
        list = view.findViewById(R.id.lista);
        lineChart = view.findViewById(R.id.lineChart);
        indicator = view.findViewById(R.id.indicador);

        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);
        model.updateBalanceTotalState(BigDecimal.ZERO, extracts);

        model.getUiState().observe(requireActivity(), uiState -> {
            value.setText(Format.decimal(uiState.getValue()));
            list.setLayoutManager(new LinearLayoutManager(getContext()));
            list.setAdapter(new ExtractAdapter(getContext(), uiState.getExtracts(), this));
            setupLineChart(uiState.getExtracts());
            BigDecimal totalValue = uiState.getExtracts().stream()
                    .map(Extract::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            if (totalValue.doubleValue() <= 0.0) {
                indicator.getBackground().setTint(Color.RED);
            }
        });
    }

    @Override
    public void onItemClick(Extract position) {
        ExtractDetailFragment fragment = new ExtractDetailFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("extract", position);
        fragment.setArguments(bundle);

        fragment.show(getParentFragmentManager(), null);
    }


    private void setupLineChart(List<Extract> extracts) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < extracts.size(); i++) {
            float yValue = extracts.get(i).getValue().floatValue();
            entries.add(new Entry(i, yValue));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Histórico");

        dataSet.setDrawCircles(true);
        dataSet.setDrawValues(false);
        dataSet.setLineWidth(2f);
        dataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.invalidate();
    }

}

