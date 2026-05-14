package com.jenugumpu.ui.harvest;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.jenugumpu.data.model.HarvestEntry;
import com.jenugumpu.data.repository.HarvestRepository;
import java.util.List;

public class HarvestViewModel extends AndroidViewModel {

    private final HarvestRepository repository;
    public final LiveData<List<HarvestEntry>> allEntries;
    public final LiveData<Float> totalStock;
    public final LiveData<Integer> batchCount;

    // Seed data total (2 pre-existing batches)
    public static final float SEED_KG = 29f;
    public static final int SEED_BATCHES = 2;

    public HarvestViewModel(Application application) {
        super(application);
        repository = new HarvestRepository(application);
        allEntries = repository.allEntries;
        totalStock = repository.totalStock;
        batchCount = repository.batchCount;
    }

    public void addEntry(HarvestEntry entry) {
        // Determine batch ID from count
        int count = (batchCount.getValue() == null ? 0 : batchCount.getValue()) + SEED_BATCHES + 1;
        entry.batchId = String.format("BATCH-%03d", count);
        // Auto-grade based on moisture
        if (entry.moisture < 18) entry.grade = "A";
        else if (entry.moisture <= 20) entry.grade = "B";
        else entry.grade = "C";
        repository.insert(entry);
    }

    public void updateEntry(HarvestEntry entry) {
        repository.update(entry);
    }

    public void deleteEntry(HarvestEntry entry) {
        repository.delete(entry);
    }
}
