package com.empty.cup;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.empty.cuplibrary.weight.httpupdown.ProgressDownloader;
import com.empty.cuplibrary.weight.httpupdown.ProgressResponseBody;

import java.io.File;

/**
 * 实现  OkHttp  下载
 */
public class MainActivity extends AppCompatActivity implements ProgressResponseBody.ProgressListener{

    public static final String TAG = "MainActivity";
    public static final String PACKAGE_URL = "http://gdown.baidu.com/data/wisegame/df65a597122796a4/weixin_821.apk";
    ProgressBar bar;
    Button star;
    Button end_star;
    Button jixu_star;
    TextView text_bar;

    private long breakPoints;
    private ProgressDownloader downloader;
    private File file;
    private long totalBytes;
    private long contentLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    private void Init(){
        bar = (ProgressBar) findViewById(R.id.bar);
        star = (Button) findViewById(R.id.star);
        end_star = (Button) findViewById(R.id.end_star);
        jixu_star = (Button) findViewById(R.id.jixu_star);
//        text_bar = (TextView) findViewById(R.id.text_bar);

        onClickVdd(star);
        onClickVdd(end_star);
        onClickVdd(jixu_star);

    }

    public void onClickVdd(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.star:
                        // 新下载前清空断点信息
                        breakPoints = 0L;
                        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "sample.apk");
                        downloader = new ProgressDownloader(PACKAGE_URL, file, MainActivity.this);
                        downloader.download(0L);
                        break;
                    case R.id.end_star:
                        downloader.pause();
                        Toast.makeText(MainActivity.this, "下载暂停", Toast.LENGTH_SHORT).show();
                        // 存储此时的totalBytes，即断点位置。
                        breakPoints = totalBytes;
                        break;
                    case R.id.jixu_star:
                        downloader.download(breakPoints);
                        break;
                }
            }
        });
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            text_bar.setText(contentLength / 1024+"");
            bar.setMax((int) (contentLength / 1024));
        }
    };

    @Override
    public void onPreExecute(final long contentLength) {
// 文件总长只需记录一次，要注意断点续传后的contentLength只是剩余部分的长度
        if (this.contentLength == 0L) {
            this.contentLength = contentLength;
            Log.v("thisdown","      bar 呵呵呵呵       "+(totalBytes + breakPoints) / 1024);
            handler.obtainMessage().sendToTarget();
        }
    }

    @Override
    public void update(long totalBytes, boolean done) {
        // 注意加上断点的长度
        this.totalBytes = totalBytes + breakPoints;
        bar.setProgress((int) (totalBytes + breakPoints) / 1024);
        if (done) {
            // 切换到主线程
//            Observable
//                    .empty()
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnCompleted(new Action0() {
//                        @Override
//                        public void call() {
//                            Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .subscribe();
        }
    }
}
