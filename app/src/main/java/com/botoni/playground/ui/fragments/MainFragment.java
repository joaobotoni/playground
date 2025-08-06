package com.botoni.playground.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.botoni.playground.R;
import com.botoni.playground.ui.model.Extract;
import com.botoni.playground.ui.adapters.ExtractAdapter;
import com.botoni.playground.utils.Format;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private TextView value;
    private RecyclerView list;
    private List<Extract> extracts;

    public MainFragment() {
        super(R.layout.fragment_main);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extracts = new ArrayList<>();
        extracts.add(new Extract("Valor em conta", LocalDateTime.now(), new BigDecimal("278.19")));
        extracts.add(new Extract("Valor em conta", LocalDateTime.now(), new BigDecimal("277.91")));
        extracts.add(new Extract("Valor em conta", LocalDateTime.now(), new BigDecimal("228.39")));
        extracts.add(new Extract("Valor em conta", LocalDateTime.now(), new BigDecimal("338.79")));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        value = view.findViewById(R.id.valor);
        list = view.findViewById(R.id.lista);
    }

    @Override
    public void onStart() {
        super.onStart();
        setup();
    }

    public void setup() {
        setupViews();
        setupRecyclerView();
    }

    public void setupViews() {
        value.setText(Format.decimal(new BigDecimal("1123.28")));
    }

    public void setupRecyclerView() {
        list.setAdapter(new ExtractAdapter(requireContext(), extracts));
        list.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}

