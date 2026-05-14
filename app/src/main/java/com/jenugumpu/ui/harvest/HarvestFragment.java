package com.jenugumpu.ui.harvest;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jenugumpu.R;
import com.jenugumpu.data.model.HarvestEntry;
import java.text.SimpleDateFormat;
import java.util.*;

public class HarvestFragment extends Fragment {

    private HarvestViewModel viewModel;
    private HarvestAdapter adapter;
    private String selectedDate = "";
    private String selectedFloral = "Coffee Blossom";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_harvest, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(HarvestViewModel.class);

        // Set today's date
        selectedDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        TextView tvDate = view.findViewById(R.id.tv_selected_date);
        tvDate.setText(selectedDate);

        // Date picker
        view.findViewById(R.id.btn_pick_date).setOnClickListener(v -> {
            Calendar cal = Calendar.getInstance();
            new DatePickerDialog(requireContext(), (dp, y, m, d) -> {
                selectedDate = d + " " + getMonthName(m) + " " + y;
                tvDate.setText(selectedDate);
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
        });

        // Floral source chips
        String[] florals = {"Coffee Blossom", "Wildflower", "Jamun", "Eucalyptus", "Neem"};
        LinearLayout chipGroup = view.findViewById(R.id.floral_chip_group);
        for (String floral : florals) {
            TextView chip = (TextView) LayoutInflater.from(requireContext())
                    .inflate(R.layout.chip_floral, chipGroup, false);
            chip.setText(floral);
            chip.setSelected(floral.equals(selectedFloral));
            chip.setOnClickListener(cv -> {
                selectedFloral = floral;
                for (int i = 0; i < chipGroup.getChildCount(); i++)
                    chipGroup.getChildAt(i).setSelected(false);
                chip.setSelected(true);
            });
            chipGroup.addView(chip);
        }

        // RecyclerView
        RecyclerView rv = view.findViewById(R.id.rv_harvest);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new HarvestAdapter();
        rv.setAdapter(adapter);

        viewModel.allEntries.observe(getViewLifecycleOwner(), entries -> {
            adapter.setEntries(entries);
        });

        // Add button
        view.findViewById(R.id.btn_add_harvest).setOnClickListener(v -> {
            EditText etQty = view.findViewById(R.id.et_qty);
            EditText etLoc = view.findViewById(R.id.et_location);
            EditText etMoisture = view.findViewById(R.id.et_moisture);

            String qtyStr = etQty.getText().toString().trim();
            String loc = etLoc.getText().toString().trim();
            String moistStr = etMoisture.getText().toString().trim();

            if (qtyStr.isEmpty()) { etQty.setError("Enter quantity"); return; }
            if (loc.isEmpty()) { etLoc.setError("Enter location"); return; }
            if (moistStr.isEmpty()) { etMoisture.setError("Enter moisture %"); return; }

            HarvestEntry entry = new HarvestEntry();
            entry.quantityKg = Float.parseFloat(qtyStr);
            entry.location = loc;
            entry.harvestDate = selectedDate;
            entry.floralSource = selectedFloral;
            entry.moisture = Float.parseFloat(moistStr);

            viewModel.addEntry(entry);
            etQty.setText("");
            etLoc.setText("");
            etMoisture.setText("");
            Toast.makeText(requireContext(), "✓ Harvest logged!", Toast.LENGTH_SHORT).show();
        });
    }

    private String getMonthName(int m) {
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        return months[m];
    }
}
