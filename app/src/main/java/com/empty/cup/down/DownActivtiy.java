package com.empty.cup.down;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.empty.cup.activity.ViewPageJDActivity;

import java.io.File;

/**
 * Created by empty cup on 2017/9/15.
 */

public class DownActivtiy extends Activity implements ViewPageJDActivity.OpenApk {
    private DownloadUtils downloadUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadUtils = new DownloadUtils(this,this);
        downloadUtils.downloadAPK("http://119.6.239.46:9999/gdown.baidu.com/data/wisegame/0904344dee4a2d92/QQ_718.apk", "QF_r.apk");

    }

    //下载到本地后执行安装
    private void installAPK() {
        //获取下载文件的Uri
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"QF_r.apk");
        Uri downloadFileUri = Uri.fromFile(file);
        if (downloadFileUri != null) {
            Intent intent= new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
            startActivity(intent);
        }
    }

    @Override
    public void openAPK() {
        installAPK();
    }
}
