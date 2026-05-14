package com.jenugumpu.ui.tracker;

import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jenugumpu.R;
import com.jenugumpu.data.model.HarvestEntry;
import java.util.*;

public class TrackerAdapter extends RecyclerView.Adapter<TrackerAdapter.VH> {

    private List<HarvestEntry> entries = new ArrayList<>();

    public void setEntries(List<HarvestEntry> list) {
        this.entries = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tracker, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        HarvestEntry e = entries.get(position);
        h.tvBatch.setText(e.batchId);
        h.tvName.setText(e.floralSource + " — " + e.location);
        h.tvInfo.setText(e.quantityKg + " kg  |  Moisture: " + e.moisture + "%  |  " + e.harvestDate);
        h.cbFiltered.setChecked(e.isFiltered);
        h.cbLabelled.setChecked(e.isLabelled);
        h.cbListed.setChecked(e.isListed);
        // Note: DB update on check-change would need ViewModel access; kept simple here
    }

    @Override public int getItemCount() { return entries.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvBatch, tvName, tvInfo;
        CheckBox cbFiltered, cbLabelled, cbListed;
        VH(View v) {
            super(v);
            tvBatch    = v.findViewById(R.id.tv_tracker_batch);
            tvName     = v.findViewById(R.id.tv_tracker_name);
            tvInfo     = v.findViewById(R.id.tv_tracker_info);
            cbFiltered = v.findViewById(R.id.cb_filtered);
            cbLabelled = v.findViewById(R.id.cb_labelled);
            cbListed   = v.findViewById(R.id.cb_listed);
        }
    }
}
