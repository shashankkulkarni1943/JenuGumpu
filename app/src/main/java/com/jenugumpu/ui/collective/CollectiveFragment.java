package com.jenugumpu.ui.collective;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.jenugumpu.R;
import com.jenugumpu.ui.harvest.HarvestViewModel;

public class CollectiveFragment extends Fragment {

    // Seed member data
    private static final String[] MEMBER_NAMES = {
            "Raju Kumar", "Savitha Bai", "Mohan Nayak", "Lakshmi Patel", "Venkat Gowda", "Kamala Raju"
    };
    private static final String[] MEMBER_LOCS = {
            "Sakleshpur", "Belur", "Alur", "Arkalgud", "Chennarayapatna", "Holenarasipur"
    };
    private static final float[] MEMBER_KG = {18f, 22f, 14f, 11f, 9f, 13f};
    private static final float SEED_COLLECTIVE = 87f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collective, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HarvestViewModel harvestVM = new ViewModelProvider(requireActivity())
                .get(HarvestViewModel.class);

        TextView tvTotal = view.findViewById(R.id.tv_collective_total);
        LinearLayout memberList = view.findViewById(R.id.ll_member_list);

        // Observe total stock from DB + seed
        harvestVM.totalStock.observe(getViewLifecycleOwner(), dbKg -> {
            float extra = dbKg != null ? dbKg : 0f;
            float total = SEED_COLLECTIVE + extra;
            tvTotal.setText((int) total + " kg");
        });

        // Inflate member rows
        for (int i = 0; i < MEMBER_NAMES.length; i++) {
            View row = LayoutInflater.from(requireContext())
                    .inflate(R.layout.item_member, memberList, false);
            ((TextView) row.findViewById(R.id.tv_member_initials))
                    .setText(getInitials(MEMBER_NAMES[i]));
            ((TextView) row.findViewById(R.id.tv_member_name)).setText(MEMBER_NAMES[i]);
            ((TextView) row.findViewById(R.id.tv_member_loc)).setText("📍 " + MEMBER_LOCS[i]);
            ((TextView) row.findViewById(R.id.tv_member_kg)).setText((int) MEMBER_KG[i] + " kg");
            memberList.addView(row);
        }
    }

    private String getInitials(String name) {
        String[] parts = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String p : parts) if (!p.isEmpty()) sb.append(p.charAt(0));
        return sb.length() > 2 ? sb.substring(0, 2) : sb.toString();
    }
}
