package com.botoni.playground.ui.adapters;

import java.util.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.botoni.playground.R;
import com.botoni.playground.ui.models.Extract;
import com.botoni.playground.utils.Format;


public class ExtractAdapter extends RecyclerView.Adapter<ExtractAdapter.ViewHolder> {

    private final List<Extract> extracts;
    private final Context context;

    public ExtractAdapter(Context context, List<Extract> extracts) {
        this.extracts = extracts;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var inflater = LayoutInflater.from(context);
        var view = inflater.inflate(R.layout.extract_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        var extract = extracts.get(position);
        holder.bind(extract);
    }

    @Override
    public int getItemCount() {
        return extracts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(Extract extract) {
           TextView description = itemView.findViewById(R.id.context);
           description.setText(extract.getDescription());

           TextView time = itemView.findViewById(R.id.time);
           time.setText(Format.date(extract.getDateTime()));

           TextView value = itemView.findViewById(R.id.valor);
           value.setText(Format.decimal(extract.getValue()));
        }
    }
}
