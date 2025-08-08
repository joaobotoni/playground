package com.botoni.playground.ui.fragments;

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

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MainFragment extends Fragment implements ExtractAdapter.OnItemClickListener {

    private TextView value;
    private RecyclerView list;
    private List<Extract> extracts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extracts = new ArrayList<>();
        extracts.add(new Extract("Valor em conta","Investimento 1", LocalDateTime.now(), new BigDecimal("230.19")));
        extracts.add(new Extract("Valor em conta","Investimento 2", LocalDateTime.now(), new BigDecimal("221.91")));
        extracts.add(new Extract("Valor em conta","Investimento 3", LocalDateTime.now(), new BigDecimal("258.39")));
        extracts.add(new Extract("Valor em conta", "Investimento 4", LocalDateTime.now(), new BigDecimal("339.79")));
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

        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);
        model.updateBalanceTotalState(BigDecimal.ZERO, extracts);

        model.getUiState().observe(requireActivity(), uiState -> {
            value.setText(Format.decimal(uiState.getValue()));
            list.setLayoutManager(new LinearLayoutManager(getContext()));
            list.setAdapter(new ExtractAdapter(getContext(), uiState.getExtracts(), this));
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
}

