package com.yang.email.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import com.email.util.utils.InitUtil;
import com.komi.slider.SliderConfig;
import com.komi.slider.position.SliderPosition;
import com.yang.email.R;

import java.io.File;

/**
 * Created by shiq_yang on 2017/8/28.
 *
 */

public class EmailApplication extends Application {

    public static boolean isFirst = true;
    public static Context context;
    private static final int DEFAULT_DISK_CACHE_SIZE = 1024 * 1024 * 10;
    public static String cachePath, filePath, downLoadPath;
    public static SliderConfig sliderConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initGlobalParams();
        InitUtil.init(context, cachePath);
        SliderPosition sliderPosition = SliderPosition.getSliderPosition(0);
        sliderConfig = new SliderConfig.Builder()
                        .primaryColor(getResources().getColor(R.color.theme_color))
                        .secondaryColor(Color.TRANSPARENT)
                        .position(sliderPosition)
                        .edge(false)
                        .build();
    }

    private void initGlobalParams() {
        if (isExternalStorageExist()) {
            if (getUsableSpace(Environment.getExternalStorageDirectory()) > DEFAULT_DISK_CACHE_SIZE) {
                File cDir = getExternalCacheDirectory();
                if (!cDir.exists()) {
                    cDir.mkdirs();
                }
                File dDir = getExternalDownLoadDirectory();
                if (!dDir.exists()) {
                    dDir.mkdirs();
                }
                File fDir = getExternalFileDirectory(null);
                if (!fDir.exists()) {
                    fDir.mkdirs();
                }
                cachePath = cDir.getPath();
                downLoadPath = dDir.getPath();
                filePath = fDir.getPath();
            } else {
            }
        }
        if (cachePath == null) {
            File f = context.getCacheDir();
            cachePath = f.getPath();
        }
        if (downLoadPath == null) {
            File f = new File(context.getCacheDir().getParent().concat("/download"));
            if (!f.exists()) {
                f.mkdirs();
            }
            downLoadPath = f.getPath();
        }
        if (filePath == null) {
            File f = context.getFilesDir();
            filePath = f.getPath();
        }
        Log.d("sssssss-----", cachePath);
    }

    /**
     * 判断sd卡是否存在
     */
    public static boolean isExternalStorageExist() {
        String state = Environment.getExternalStorageState();
        if (state != null && state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static long getUsableSpace(File path) {
        if (path == null || !path.exists()) {
            return -1;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return path.getUsableSpace();
        }
        final StatFs stats = new StatFs(path.getPath());
        return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
    }

    private File getExternalCacheDirectory() {
        File dir = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            dir = context.getExternalCacheDir();
        } else {
            String cacheDir = "/Android/data/" + context.getPackageName() + "/cache";
            dir = new File(Environment.getExternalStorageDirectory().getPath().concat(cacheDir));
        }
        return dir;
    }

    private File getExternalDownLoadDirectory() {
        String downLoadDir = "/Android/data/" + context.getPackageName() + "/download";
        File dir = new File(Environment.getExternalStorageDirectory().getPath().concat(downLoadDir));
        return dir;
    }

    private File getExternalFileDirectory(String type) {
        File dir = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            if (type != null && !type.equals("")) {
                dir = context.getExternalFilesDir(type);
            } else {
                dir = context.getExternalFilesDir("");
            }
        } else {
            String fileDir = "";
            if (type != null && !type.equals("")) {
                fileDir = "/Android/data/" + context.getPackageName() + "/files/" + type;
            } else {
                fileDir = "/Android/data/" + context.getPackageName() + "/files";
            }
            dir = new File(Environment.getExternalStorageDirectory().getPath().concat(fileDir));
        }
        return dir;
    }
}
