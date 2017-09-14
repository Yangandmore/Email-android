package com.email.util.DBUtils;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import com.email.util.base.GlobalConstants;

import java.io.File;
import java.io.IOException;

/**
 * Created by shiq_yang on 2017/9/1.
 * 修改数据库的地址
 */

public class EmailServerDaoContent extends ContextWrapper {

    public EmailServerDaoContent(Context base) {
        super(base);
    }

    @Override
    public File getDatabasePath(String name) {

        /**
         * 创建数据库路径及创建数据库文件
         */
        File f = new File(GlobalConstants.DbFileRoute.ROUTE_DB);
        if (!f.exists()) {
            f.mkdirs();
        }
        GlobalConstants.DbFileRoute.ROUTE_DB = f.getPath();

        File dbFile = new File(f, name);
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return dbFile;
    }

    /**
     *  创建数据库文件
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {

        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);

        return result;
    }
    /**
     *  创建数据库文件
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }
}
