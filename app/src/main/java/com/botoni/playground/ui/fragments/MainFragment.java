package com.botoni.playground.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.botoni.playground.R;

public class MainFragment extends Fragment {

    private TextView counter;
    private Button increment;
    private Button decrement;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.counter = view.findViewById(R.id.counter);
        this.increment = view.findViewById(R.id.increase);
        this.decrement =  view.findViewById(R.id.decrease);
        setupListeners();
    }

    private void setupListeners(){
        increment.setOnClickListener(v ->
                counter.setText(String.valueOf(increment(counter.getText().toString())))
        );

        decrement.setOnClickListener(v ->
            counter.setText(String.valueOf(decrement(counter.getText().toString())))
        );
    }

    private int increment(String string) {
        int i = Integer.parseInt(string);
        i++;
        return i;
    }

    private int decrement(String string) {
        int i = Integer.parseInt(string);
        i--;
        return i;
    }
}
