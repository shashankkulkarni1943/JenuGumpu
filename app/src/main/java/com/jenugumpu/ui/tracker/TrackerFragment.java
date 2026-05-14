package com.jenugumpu.ui.tracker;

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
import com.jenugumpu.ui.harvest.HarvestViewModel;

public class TrackerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tracker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HarvestViewModel vm = new ViewModelProvider(requireActivity())
                .get(HarvestViewModel.class);

        RecyclerView rv = view.findViewById(R.id.rv_batches);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        TrackerAdapter adapter = new TrackerAdapter();
        rv.setAdapter(adapter);

        vm.allEntries.observe(getViewLifecycleOwner(), entries -> {
            adapter.setEntries(entries);
        });

        // Status update via checkbox / toggle handled in adapter
    }
}
