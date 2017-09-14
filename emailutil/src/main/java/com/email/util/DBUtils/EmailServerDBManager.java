package com.email.util.DBUtils;

import android.database.sqlite.SQLiteDatabase;

import com.email.util.models.EmailServerData;
import com.email.util.greendao.gen.DaoMaster;
import com.email.util.greendao.gen.DaoSession;
import com.email.util.greendao.gen.EmailServerDataDao;
import com.email.util.utils.InitUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by shiq_yang on 2017/9/1.
 * 用户信息数据库
 */

public class EmailServerDBManager {

    private final static String dbName = "USER_DATA_DB";
    private static EmailServerDBManager emailServerDBManager;
    private DaoMaster.DevOpenHelper openHelper;
    private DaoSession daoSession;

    /**
     * 初始化
     */
    public EmailServerDBManager() {
        openHelper = new DaoMaster.DevOpenHelper(new EmailServerDaoContent(InitUtil.getContext()), dbName, null);
        SQLiteDatabase database = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    /**
     * 单例模型
     */
    public static EmailServerDBManager getInstance() {
        if (emailServerDBManager == null)
            synchronized (EmailServerDBManager.class) {
                if (emailServerDBManager == null)
                    emailServerDBManager = new EmailServerDBManager();
            }

        return emailServerDBManager;
    }

    /**
     * 增
     */
    public void insert(EmailServerData emailServerData) {
        EmailServerDataDao emailServerDataDao = daoSession.getEmailServerDataDao();
        emailServerDataDao.insert(emailServerData);
    }
    public void insertList(List<EmailServerData> list) {
        if (list == null || list.isEmpty())
            return ;
        EmailServerDataDao emailServerDataDao = daoSession.getEmailServerDataDao();
        emailServerDataDao.insertInTx(list);
    }

    /**
     * 删
     */
    public void delete(EmailServerData emailServerData) {
        EmailServerDataDao emailServerDataDao = daoSession.getEmailServerDataDao();
        emailServerDataDao.delete(emailServerData);
    }
    public void deleteAll() {
        EmailServerDataDao emailServerDataDao = daoSession.getEmailServerDataDao();
        emailServerDataDao.deleteAll();
    }

    /**
     * 改
     */
    public void update(EmailServerData emailServerData) {
        EmailServerDataDao emailServerDataDao = daoSession.getEmailServerDataDao();
        emailServerDataDao.update(emailServerData);
    }

    /**
     * 查
     */
    public List<EmailServerData> query() {
        EmailServerDataDao emailServerDataDao = daoSession.getEmailServerDataDao();
        QueryBuilder<EmailServerData> list = emailServerDataDao.queryBuilder();
        return list.list();
    }


    /**
     * 关闭数据库
     */
    public void close() {
        if (openHelper != null) {
            openHelper.close();
            openHelper = null;
        }
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

}