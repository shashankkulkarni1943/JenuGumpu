package com.jenugumpu.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.jenugumpu.data.model.HarvestEntry;
import java.util.List;

@Dao
public interface HarvestDao {

    @Insert
    long insert(HarvestEntry entry);

    @Update
    void update(HarvestEntry entry);

    @Delete
    void delete(HarvestEntry entry);

    @Query("SELECT * FROM harvest_entries ORDER BY createdAt DESC")
    LiveData<List<HarvestEntry>> getAllEntries();

    @Query("SELECT SUM(quantityKg) FROM harvest_entries")
    LiveData<Float> getTotalStock();

    @Query("SELECT COUNT(*) FROM harvest_entries")
    LiveData<Integer> getBatchCount();

    @Query("SELECT * FROM harvest_entries ORDER BY createdAt DESC")
    List<HarvestEntry> getAllEntriesSync();
}
