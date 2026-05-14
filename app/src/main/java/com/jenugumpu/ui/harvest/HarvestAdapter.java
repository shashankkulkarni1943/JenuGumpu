package com.jenugumpu.ui.harvest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.jenugumpu.R;
import com.jenugumpu.data.model.HarvestEntry;
import java.util.ArrayList;
import java.util.List;

public class HarvestAdapter extends RecyclerView.Adapter<HarvestAdapter.ViewHolder> {

    private List<HarvestEntry> entries = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(HarvestEntry entry);
    }

    public void setOnItemClickListener(OnItemClickListener l) { this.listener = l; }

    public void setEntries(List<HarvestEntry> list) {
        this.entries = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_harvest, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        HarvestEntry e = entries.get(position);
        h.tvBatchId.setText(e.batchId);
        h.tvGrade.setText("Grade " + e.grade);
        h.tvName.setText(e.floralSource + " Honey");
        h.tvLocation.setText("📍 " + e.location);
        h.tvDate.setText("📅 " + e.harvestDate);
        h.tvQty.setText(e.quantityKg + " kg");
        h.tvMoisture.setText("Moisture: " + e.moisture + "%");

        // Grade badge color
        int bg, fg;
        switch (e.grade) {
            case "A": bg = R.color.grade_a_bg; fg = R.color.grade_a_fg; break;
            case "B": bg = R.color.grade_b_bg; fg = R.color.grade_b_fg; break;
            default:  bg = R.color.grade_c_bg; fg = R.color.grade_c_fg; break;
        }
        h.tvGrade.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        ContextCompat.getColor(h.itemView.getContext(), bg)));
        h.tvGrade.setTextColor(ContextCompat.getColor(h.itemView.getContext(), fg));

        h.itemView.setOnClickListener(v -> { if (listener != null) listener.onItemClick(e); });
    }

    @Override
    public int getItemCount() { return entries.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBatchId, tvGrade, tvName, tvLocation, tvDate, tvQty, tvMoisture;
        ViewHolder(View v) {
            super(v);
            tvBatchId   = v.findViewById(R.id.tv_batch_id);
            tvGrade     = v.findViewById(R.id.tv_grade);
            tvName      = v.findViewById(R.id.tv_harvest_name);
            tvLocation  = v.findViewById(R.id.tv_location);
            tvDate      = v.findViewById(R.id.tv_date);
            tvQty       = v.findViewById(R.id.tv_qty);
            tvMoisture  = v.findViewById(R.id.tv_moisture);
        }
    }
}
