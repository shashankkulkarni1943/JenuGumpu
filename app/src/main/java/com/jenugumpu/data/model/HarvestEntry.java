package com.jenugumpu.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "harvest_entries")
public class HarvestEntry {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String batchId;
    public float quantityKg;
    public String location;
    public String floralSource;
    public String harvestDate;
    public String grade;        // A, B, or C
    public float moisture;      // percentage
    public boolean isFiltered;
    public boolean isLabelled;
    public boolean isListed;
    public long createdAt;

    public HarvestEntry() {
        this.createdAt = System.currentTimeMillis();
        this.isFiltered = false;
        this.isLabelled = false;
        this.isListed = false;
    }
}
