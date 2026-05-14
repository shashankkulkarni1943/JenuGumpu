package com.jenugumpu.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile HarvestDao _harvestDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `harvest_entries` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `batchId` TEXT, `quantityKg` REAL NOT NULL, `location` TEXT, `floralSource` TEXT, `harvestDate` TEXT, `grade` TEXT, `moisture` REAL NOT NULL, `isFiltered` INTEGER NOT NULL, `isLabelled` INTEGER NOT NULL, `isListed` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'aa9c43dd5267cd4c6fb21c50354a21d3')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `harvest_entries`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsHarvestEntries = new HashMap<String, TableInfo.Column>(12);
        _columnsHarvestEntries.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvestEntries.put("batchId", new TableInfo.Column("batchId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvestEntries.put("quantityKg", new TableInfo.Column("quantityKg", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvestEntries.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvestEntries.put("floralSource", new TableInfo.Column("floralSource", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvestEntries.put("harvestDate", new TableInfo.Column("harvestDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvestEntries.put("grade", new TableInfo.Column("grade", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvestEntries.put("moisture", new TableInfo.Column("moisture", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvestEntries.put("isFiltered", new TableInfo.Column("isFiltered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvestEntries.put("isLabelled", new TableInfo.Column("isLabelled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvestEntries.put("isListed", new TableInfo.Column("isListed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvestEntries.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHarvestEntries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHarvestEntries = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHarvestEntries = new TableInfo("harvest_entries", _columnsHarvestEntries, _foreignKeysHarvestEntries, _indicesHarvestEntries);
        final TableInfo _existingHarvestEntries = TableInfo.read(db, "harvest_entries");
        if (!_infoHarvestEntries.equals(_existingHarvestEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "harvest_entries(com.jenugumpu.data.model.HarvestEntry).\n"
                  + " Expected:\n" + _infoHarvestEntries + "\n"
                  + " Found:\n" + _existingHarvestEntries);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "aa9c43dd5267cd4c6fb21c50354a21d3", "7f169d6a6caeff8e6f9c3e58ae37ffa9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "harvest_entries");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `harvest_entries`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(HarvestDao.class, HarvestDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public HarvestDao harvestDao() {
    if (_harvestDao != null) {
      return _harvestDao;
    } else {
      synchronized(this) {
        if(_harvestDao == null) {
          _harvestDao = new HarvestDao_Impl(this);
        }
        return _harvestDao;
      }
    }
  }
}
