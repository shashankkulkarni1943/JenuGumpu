package com.jenugumpu.ui.price;

import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jenugumpu.R;

public class PriceFragment extends Fragment {

    // Static retail price data (can be replaced with API later)
    private static final float[][] PRICES = {
            // {retail, wholesale, middleman}
            {480f, 300f, 120f},  // Coffee Blossom
            {350f, 220f, 100f},  // Wildflower
            {420f, 260f, 110f},  // Jamun
            {380f, 240f, 105f},  // Eucalyptus
            {320f, 200f, 95f},   // Neem
    };
    private static final String[] FLORAL_NAMES = {
            "Coffee Blossom", "Wildflower", "Jamun", "Eucalyptus", "Neem"
    };

    private int selectedIdx = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_price, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinner = view.findViewById(R.id.spinner_floral);
        ArrayAdapter<String> spAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, FLORAL_NAMES);
        spinner.setAdapter(spAdapter);

        TextView tvRetail      = view.findViewById(R.id.tv_retail_price);
        TextView tvWholesale   = view.findViewById(R.id.tv_wholesale_price);
        TextView tvMiddleman   = view.findViewById(R.id.tv_middleman_price);
        EditText etQty         = view.findViewById(R.id.et_calc_qty);
        EditText etCost        = view.findViewById(R.id.et_calc_cost);
        TextView tvProfit      = view.findViewById(R.id.tv_profit_result);
        TextView tvLoss        = view.findViewById(R.id.tv_loss_result);
        TextView tvGain        = view.findViewById(R.id.tv_gain_result);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> p, View v2, int pos, long id) {
                selectedIdx = pos;
                updatePrices(tvRetail, tvWholesale, tvMiddleman);
                recalc(etQty, etCost, tvProfit, tvLoss, tvGain);
            }
            @Override public void onNothingSelected(AdapterView<?> p) {}
        });

        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
            @Override public void onTextChanged(CharSequence s, int st, int b, int c) {
                recalc(etQty, etCost, tvProfit, tvLoss, tvGain);
            }
            @Override public void afterTextChanged(Editable s) {}
        };
        etQty.addTextChangedListener(watcher);
        etCost.addTextChangedListener(watcher);

        updatePrices(tvRetail, tvWholesale, tvMiddleman);
        recalc(etQty, etCost, tvProfit, tvLoss, tvGain);
    }

    private void updatePrices(TextView tvRetail, TextView tvWholesale, TextView tvMiddleman) {
        float[] p = PRICES[selectedIdx];
        tvRetail.setText("₹" + (int) p[0] + "/kg");
        tvWholesale.setText("₹" + (int) p[1] + "/kg");
        tvMiddleman.setText("₹" + (int) p[2] + "/kg");
    }

    private void recalc(EditText etQty, EditText etCost,
                        TextView tvProfit, TextView tvLoss, TextView tvGain) {
        float qty  = parseFloat(etQty);
        float cost = parseFloat(etCost);
        float retail    = PRICES[selectedIdx][0];
        float middleman = PRICES[selectedIdx][2];

        float profit = qty * (retail - cost);
        float lossAmount = qty * (middleman - cost);

        tvProfit.setText("₹" + formatINR((int) profit));
        tvGain.setText("₹" + formatINR((int) profit));
        tvLoss.setText("₹" + formatINR((int) Math.max(0, lossAmount)));
    }

    private float parseFloat(EditText et) {
        try { return Float.parseFloat(et.getText().toString()); }
        catch (NumberFormatException e) { return 0f; }
    }

    private String formatINR(int val) {
        // Simple INR formatting
        if (val >= 100000) return String.format("%.1fL", val / 100000f);
        if (val >= 1000)   return String.format("%.1fK", val / 1000f);
        return String.valueOf(val);
    }
}
