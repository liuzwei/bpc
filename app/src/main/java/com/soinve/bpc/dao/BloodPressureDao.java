package com.soinve.bpc.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.soinve.bpc.entity.BloodPressure;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BLOOD_PRESSURE".
*/
public class BloodPressureDao extends AbstractDao<BloodPressure, Long> {

    public static final String TABLENAME = "BLOOD_PRESSURE";

    /**
     * Properties of entity BloodPressure.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property HighPressure = new Property(1, Integer.class, "highPressure", false, "HIGH_PRESSURE");
        public final static Property LowPressure = new Property(2, Integer.class, "lowPressure", false, "LOW_PRESSURE");
        public final static Property HeartRate = new Property(3, Integer.class, "heartRate", false, "HEART_RATE");
        public final static Property CreateTime = new Property(4, java.util.Date.class, "createTime", false, "CREATE_TIME");
    }


    public BloodPressureDao(DaoConfig config) {
        super(config);
    }
    
    public BloodPressureDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BLOOD_PRESSURE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"HIGH_PRESSURE\" INTEGER," + // 1: highPressure
                "\"LOW_PRESSURE\" INTEGER," + // 2: lowPressure
                "\"HEART_RATE\" INTEGER," + // 3: heartRate
                "\"CREATE_TIME\" INTEGER);"); // 4: createTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BLOOD_PRESSURE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BloodPressure entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer highPressure = entity.getHighPressure();
        if (highPressure != null) {
            stmt.bindLong(2, highPressure);
        }
 
        Integer lowPressure = entity.getLowPressure();
        if (lowPressure != null) {
            stmt.bindLong(3, lowPressure);
        }
 
        Integer heartRate = entity.getHeartRate();
        if (heartRate != null) {
            stmt.bindLong(4, heartRate);
        }
 
        java.util.Date createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindLong(5, createTime.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BloodPressure entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer highPressure = entity.getHighPressure();
        if (highPressure != null) {
            stmt.bindLong(2, highPressure);
        }
 
        Integer lowPressure = entity.getLowPressure();
        if (lowPressure != null) {
            stmt.bindLong(3, lowPressure);
        }
 
        Integer heartRate = entity.getHeartRate();
        if (heartRate != null) {
            stmt.bindLong(4, heartRate);
        }
 
        java.util.Date createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindLong(5, createTime.getTime());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BloodPressure readEntity(Cursor cursor, int offset) {
        BloodPressure entity = new BloodPressure( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // highPressure
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // lowPressure
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // heartRate
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)) // createTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BloodPressure entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setHighPressure(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setLowPressure(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setHeartRate(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setCreateTime(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BloodPressure entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BloodPressure entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BloodPressure entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
