package com.jenugumpu.ui.grading;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.jenugumpu.R;

public class GradingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grading, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SeekBar seekMoisture = view.findViewById(R.id.seek_moisture);
        TextView tvMoistPct  = view.findViewById(R.id.tv_moisture_pct);
        ProgressBar pbFill   = view.findViewById(R.id.pb_moisture_fill);
        TextView tvResult    = view.findViewById(R.id.tv_grade_result);
        TextView tvAdvice    = view.findViewById(R.id.tv_grade_advice);

        // Color cards
        View cardGolden = view.findViewById(R.id.card_golden);
        View cardAmber  = view.findViewById(R.id.card_amber);
        View cardDark   = view.findViewById(R.id.card_dark);

        cardGolden.setOnClickListener(v -> showColorInfo(tvAdvice,
                "🟡 Golden Yellow", "Fresh, light floral honey. Grade A candidate."));
        cardAmber.setOnClickListener(v -> showColorInfo(tvAdvice,
                "🟠 Amber", "Medium strength honey. Check moisture before grading."));
        cardDark.setOnClickListener(v -> showColorInfo(tvAdvice,
                "🟤 Dark Brown", "Strong flavour. May have higher mineral content."));

        seekMoisture.setMin(10);
        seekMoisture.setMax(30);
        seekMoisture.setProgress(17);
        updateGrade(view, 17, tvMoistPct, pbFill, tvResult, tvAdvice);

        seekMoisture.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar sb, int p, boolean user) {
                updateGrade(view, p, tvMoistPct, pbFill, tvResult, tvAdvice);
            }
            @Override public void onStartTrackingTouch(SeekBar sb) {}
            @Override public void onStopTrackingTouch(SeekBar sb) {}
        });
    }

    private void updateGrade(View view, int moisture,
                             TextView tvPct, ProgressBar pb, TextView tvResult, TextView tvAdvice) {
        tvPct.setText(moisture + "%");
        pb.setMax(20);
        pb.setProgress(moisture - 10);

        if (moisture < 18) {
            tvResult.setText("⭐ Grade A — Retail Ready ✓");
            tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.forest));
            tvAdvice.setText("Excellent! Moisture below 18% means low fermentation risk. Ready to filter and bottle for retail.");
        } else if (moisture <= 20) {
            tvResult.setText("🔶 Grade B — Filter Before Selling");
            tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.honey));
            tvAdvice.setText("Good quality but needs filtering. Reduce moisture through gentle warming or dehydration before bottling.");
        } else {
            tvResult.setText("⚠ Grade C — High Fermentation Risk!");
            tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_dark));
            tvAdvice.setText("Moisture too high. Do NOT sell yet. Dry the honey further or use for value-added products like mead.");
        }
    }

    private void showColorInfo(TextView tvAdvice, String title, String desc) {
        tvAdvice.setText(title + "\n" + desc);
    }
}
