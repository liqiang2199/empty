package com.empty.cup.zfbbanklist;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.empty.cup.R;

/**
 * Created by empty cup on 2017/12/17.
 * 实现支付宝 银行列表的 层叠效果
 */

public class ZFBBankListActivity extends Activity {

    RecyclerView recycle_bank;
    private RecycleBankAdapter recycleBankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zfbbank_list);
        recycle_bank = (RecyclerView) findViewById(R.id.recycle_bank);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recycle_bank.setLayoutManager(layoutmanager);

        recycleBankAdapter = new RecycleBankAdapter();
        recycle_bank.setAdapter(recycleBankAdapter);

        //实现层叠 效果 http://m.blog.csdn.net/hjw45611/article/details/77534182
        recycle_bank.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
//                outRect.bottom = 10;
                if (parent.getChildPosition(view) != (10 - 1)) {
                    outRect.bottom = -100;
                }

            }
        });

    }
}
