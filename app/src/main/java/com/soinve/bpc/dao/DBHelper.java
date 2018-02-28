package com.soinve.bpc.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.soinve.bpc.entity.BloodPressure;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import java.util.List;

/**
 * Created by liuzwei on 2015/3/13.
 */
public class DBHelper {
    private static DBHelper instance;
    private static DaoMaster.DevOpenHelper helper;
    private BloodPressureDao bloodPressureDao;

    private static SQLiteDatabase db;
    private static DaoMaster daoMaster;
    private DBHelper(){}
    public static DBHelper getInstance(Context context){
        if (instance == null){
            instance = new DBHelper();
            helper = new DaoMaster.DevOpenHelper(context, "bpc_db", null);
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);

            instance.bloodPressureDao = daoMaster.newSession(IdentityScopeType.None).getBloodPressureDao();
        }
        return instance;
    }


    /**
     * 插入一个信息
     * @param bloodPressure
     */
    public void insertPressInfo(BloodPressure bloodPressure){
        bloodPressureDao.insertOrReplace(bloodPressure);
    }

    /**
     * 更新一个信息
     * @param bloodPressure
     */
    public void updatePressInfo(BloodPressure bloodPressure){
        bloodPressureDao.update(bloodPressure);
    }

    /**
     * 删除一个信息
     * @param bloodPressure
     */
    public void deletePressInfo(BloodPressure bloodPressure){
        bloodPressureDao.deleteByKey(bloodPressure.getId());
    }

    /**
     * 加载所有的信息
     * @return
     */
    public List<BloodPressure> loadAll(){
        return bloodPressureDao.loadAll();
    }

    /**
     * 加载所有的信息
     * @return
     */
    public List<BloodPressure> loadAllReverseOrder(){
        return bloodPressureDao.queryBuilder().orderDesc(BloodPressureDao.Properties.Id).list();
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    public List<BloodPressure> pageSelectInfo(Integer page, Integer pageSize){

        return bloodPressureDao.queryBuilder().offset((page-1)*pageSize).limit(pageSize).orderDesc(BloodPressureDao.Properties.Id).list();
    }
}
