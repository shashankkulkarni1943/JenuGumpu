package com.jenugumpu.data.db;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jenugumpu.data.model.HarvestEntry;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HarvestDao_Impl implements HarvestDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HarvestEntry> __insertionAdapterOfHarvestEntry;

  private final EntityDeletionOrUpdateAdapter<HarvestEntry> __deletionAdapterOfHarvestEntry;

  private final EntityDeletionOrUpdateAdapter<HarvestEntry> __updateAdapterOfHarvestEntry;

  public HarvestDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHarvestEntry = new EntityInsertionAdapter<HarvestEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `harvest_entries` (`id`,`batchId`,`quantityKg`,`location`,`floralSource`,`harvestDate`,`grade`,`moisture`,`isFiltered`,`isLabelled`,`isListed`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final HarvestEntry entity) {
        statement.bindLong(1, entity.id);
        if (entity.batchId == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.batchId);
        }
        statement.bindDouble(3, entity.quantityKg);
        if (entity.location == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.location);
        }
        if (entity.floralSource == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.floralSource);
        }
        if (entity.harvestDate == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.harvestDate);
        }
        if (entity.grade == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.grade);
        }
        statement.bindDouble(8, entity.moisture);
        final int _tmp = entity.isFiltered ? 1 : 0;
        statement.bindLong(9, _tmp);
        final int _tmp_1 = entity.isLabelled ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        final int _tmp_2 = entity.isListed ? 1 : 0;
        statement.bindLong(11, _tmp_2);
        statement.bindLong(12, entity.createdAt);
      }
    };
    this.__deletionAdapterOfHarvestEntry = new EntityDeletionOrUpdateAdapter<HarvestEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `harvest_entries` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final HarvestEntry entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__updateAdapterOfHarvestEntry = new EntityDeletionOrUpdateAdapter<HarvestEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `harvest_entries` SET `id` = ?,`batchId` = ?,`quantityKg` = ?,`location` = ?,`floralSource` = ?,`harvestDate` = ?,`grade` = ?,`moisture` = ?,`isFiltered` = ?,`isLabelled` = ?,`isListed` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final HarvestEntry entity) {
        statement.bindLong(1, entity.id);
        if (entity.batchId == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.batchId);
        }
        statement.bindDouble(3, entity.quantityKg);
        if (entity.location == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.location);
        }
        if (entity.floralSource == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.floralSource);
        }
        if (entity.harvestDate == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.harvestDate);
        }
        if (entity.grade == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.grade);
        }
        statement.bindDouble(8, entity.moisture);
        final int _tmp = entity.isFiltered ? 1 : 0;
        statement.bindLong(9, _tmp);
        final int _tmp_1 = entity.isLabelled ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        final int _tmp_2 = entity.isListed ? 1 : 0;
        statement.bindLong(11, _tmp_2);
        statement.bindLong(12, entity.createdAt);
        statement.bindLong(13, entity.id);
      }
    };
  }

  @Override
  public long insert(final HarvestEntry entry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfHarvestEntry.insertAndReturnId(entry);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final HarvestEntry entry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfHarvestEntry.handle(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final HarvestEntry entry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfHarvestEntry.handle(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<HarvestEntry>> getAllEntries() {
    final String _sql = "SELECT * FROM harvest_entries ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"harvest_entries"}, false, new Callable<List<HarvestEntry>>() {
      @Override
      @Nullable
      public List<HarvestEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBatchId = CursorUtil.getColumnIndexOrThrow(_cursor, "batchId");
          final int _cursorIndexOfQuantityKg = CursorUtil.getColumnIndexOrThrow(_cursor, "quantityKg");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfFloralSource = CursorUtil.getColumnIndexOrThrow(_cursor, "floralSource");
          final int _cursorIndexOfHarvestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestDate");
          final int _cursorIndexOfGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "grade");
          final int _cursorIndexOfMoisture = CursorUtil.getColumnIndexOrThrow(_cursor, "moisture");
          final int _cursorIndexOfIsFiltered = CursorUtil.getColumnIndexOrThrow(_cursor, "isFiltered");
          final int _cursorIndexOfIsLabelled = CursorUtil.getColumnIndexOrThrow(_cursor, "isLabelled");
          final int _cursorIndexOfIsListed = CursorUtil.getColumnIndexOrThrow(_cursor, "isListed");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<HarvestEntry> _result = new ArrayList<HarvestEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HarvestEntry _item;
            _item = new HarvestEntry();
            _item.id = _cursor.getInt(_cursorIndexOfId);
            if (_cursor.isNull(_cursorIndexOfBatchId)) {
              _item.batchId = null;
            } else {
              _item.batchId = _cursor.getString(_cursorIndexOfBatchId);
            }
            _item.quantityKg = _cursor.getFloat(_cursorIndexOfQuantityKg);
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _item.location = null;
            } else {
              _item.location = _cursor.getString(_cursorIndexOfLocation);
            }
            if (_cursor.isNull(_cursorIndexOfFloralSource)) {
              _item.floralSource = null;
            } else {
              _item.floralSource = _cursor.getString(_cursorIndexOfFloralSource);
            }
            if (_cursor.isNull(_cursorIndexOfHarvestDate)) {
              _item.harvestDate = null;
            } else {
              _item.harvestDate = _cursor.getString(_cursorIndexOfHarvestDate);
            }
            if (_cursor.isNull(_cursorIndexOfGrade)) {
              _item.grade = null;
            } else {
              _item.grade = _cursor.getString(_cursorIndexOfGrade);
            }
            _item.moisture = _cursor.getFloat(_cursorIndexOfMoisture);
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFiltered);
            _item.isFiltered = _tmp != 0;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsLabelled);
            _item.isLabelled = _tmp_1 != 0;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsListed);
            _item.isListed = _tmp_2 != 0;
            _item.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Float> getTotalStock() {
    final String _sql = "SELECT SUM(quantityKg) FROM harvest_entries";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"harvest_entries"}, false, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Integer> getBatchCount() {
    final String _sql = "SELECT COUNT(*) FROM harvest_entries";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"harvest_entries"}, false, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<HarvestEntry> getAllEntriesSync() {
    final String _sql = "SELECT * FROM harvest_entries ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfBatchId = CursorUtil.getColumnIndexOrThrow(_cursor, "batchId");
      final int _cursorIndexOfQuantityKg = CursorUtil.getColumnIndexOrThrow(_cursor, "quantityKg");
      final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
      final int _cursorIndexOfFloralSource = CursorUtil.getColumnIndexOrThrow(_cursor, "floralSource");
      final int _cursorIndexOfHarvestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestDate");
      final int _cursorIndexOfGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "grade");
      final int _cursorIndexOfMoisture = CursorUtil.getColumnIndexOrThrow(_cursor, "moisture");
      final int _cursorIndexOfIsFiltered = CursorUtil.getColumnIndexOrThrow(_cursor, "isFiltered");
      final int _cursorIndexOfIsLabelled = CursorUtil.getColumnIndexOrThrow(_cursor, "isLabelled");
      final int _cursorIndexOfIsListed = CursorUtil.getColumnIndexOrThrow(_cursor, "isListed");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final List<HarvestEntry> _result = new ArrayList<HarvestEntry>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final HarvestEntry _item;
        _item = new HarvestEntry();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfBatchId)) {
          _item.batchId = null;
        } else {
          _item.batchId = _cursor.getString(_cursorIndexOfBatchId);
        }
        _item.quantityKg = _cursor.getFloat(_cursorIndexOfQuantityKg);
        if (_cursor.isNull(_cursorIndexOfLocation)) {
          _item.location = null;
        } else {
          _item.location = _cursor.getString(_cursorIndexOfLocation);
        }
        if (_cursor.isNull(_cursorIndexOfFloralSource)) {
          _item.floralSource = null;
        } else {
          _item.floralSource = _cursor.getString(_cursorIndexOfFloralSource);
        }
        if (_cursor.isNull(_cursorIndexOfHarvestDate)) {
          _item.harvestDate = null;
        } else {
          _item.harvestDate = _cursor.getString(_cursorIndexOfHarvestDate);
        }
        if (_cursor.isNull(_cursorIndexOfGrade)) {
          _item.grade = null;
        } else {
          _item.grade = _cursor.getString(_cursorIndexOfGrade);
        }
        _item.moisture = _cursor.getFloat(_cursorIndexOfMoisture);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFiltered);
        _item.isFiltered = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsLabelled);
        _item.isLabelled = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfIsListed);
        _item.isListed = _tmp_2 != 0;
        _item.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
