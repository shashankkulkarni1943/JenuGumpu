package com.jenugumpu.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.jenugumpu.data.db.AppDatabase;
import com.jenugumpu.data.db.HarvestDao;
import com.jenugumpu.data.model.HarvestEntry;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HarvestRepository {

    private final HarvestDao dao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public final LiveData<List<HarvestEntry>> allEntries;
    public final LiveData<Float> totalStock;
    public final LiveData<Integer> batchCount;

    public HarvestRepository(Application app) {
        AppDatabase db = AppDatabase.getInstance(app);
        dao = db.harvestDao();
        allEntries = dao.getAllEntries();
        totalStock = dao.getTotalStock();
        batchCount = dao.getBatchCount();
    }

    public void insert(HarvestEntry entry) {
        executor.execute(() -> dao.insert(entry));
    }

    public void update(HarvestEntry entry) {
        executor.execute(() -> dao.update(entry));
    }

    public void delete(HarvestEntry entry) {
        executor.execute(() -> dao.delete(entry));
    }
}
