package com.botoni.playground.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.botoni.playground.R;
import com.botoni.playground.ui.models.Extract;
import com.botoni.playground.utils.Format;

public class ExtractDetailFragment extends DialogFragment {

    private Extract extract;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            extract = getArguments().getSerializable("extract", Extract.class);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.extract_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView value = view.findViewById(R.id.valor_extract);
        value.setText(Format.decimal(extract.getValue()));

        TextView val = view.findViewById(R.id.context_extract);
        val.setText(extract.getVal());

        TextView dateTime = view.findViewById(R.id.time_extract);
        dateTime.setText(Format.date(extract.getDateTime()));

        TextView description = view.findViewById(R.id.description_extract);
        description.setText(extract.getDescription());

    }


    @Override
    public void onStart() {
        super.onStart();
        setupDialogWindow();
    }

    private void setupDialogWindow() {
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();

            int marginDp = 24;
            float density = getResources().getDisplayMetrics().density;
            int marginPx = (int) (marginDp * density);

            int width = getResources().getDisplayMetrics().widthPixels - 2 * marginPx;

            window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(R.drawable.rounded_background);
        }
    }
}
