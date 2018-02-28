package com.soinve.bpc.util;


import com.soinve.bpc.App;
import com.soinve.bpc.dao.DaoMaster;
import com.soinve.bpc.dao.DaoSession;

/**
 * Created by liuzhaowei on 2016/12/28.
 */

public class GreenDaoManager {
    private static GreenDaoManager mInstance; //单例
    private DaoMaster mDaoMaster; //以一定的模式管理Dao类的数据库对象
    private DaoSession mDaoSession; //管理制定模式下的所有可用Dao对象
    public GreenDaoManager() {
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(App.getContext(), "bpc_db", null);
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }
    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }
    public DaoMaster getMaster() {
        return mDaoMaster;
    }
    public DaoSession getSession() {
        return mDaoSession;
    }
    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
