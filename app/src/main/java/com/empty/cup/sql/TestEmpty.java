package com.empty.cup.sql;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.empty.cup.R;
import com.empty.cup.wight.MaskProgress;
import com.empty.cuplibrary.weight.dataprocessing.JsonUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by empty cup on 2017/7/21.
 * 仿美团的底部tab 点击动画效果
 * 使用遮罩层实现
 */

public class TestEmpty extends Activity{

   private MaskProgress maskProgress;
   private MaskProgress maskProgress1;
   private MaskProgress maskProgress2;
   private MaskProgress maskProgress3;

    private List<MaskProgress> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        maskProgress = (MaskProgress) findViewById(R.id.star_pross);
        maskProgress1 = (MaskProgress) findViewById(R.id.star_pross1);
        maskProgress2 = (MaskProgress) findViewById(R.id.star_pross2);
        maskProgress3 = (MaskProgress) findViewById(R.id.star_pross3);

        OnClick(maskProgress);
        OnClick(maskProgress1);
        OnClick(maskProgress2);
        OnClick(maskProgress3);

        list.add(maskProgress);
        list.add(maskProgress1);
        list.add(maskProgress2);
        list.add(maskProgress3);

    }
    private void OnClick(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.star_pross:
                        OnClick_False(maskProgress);


                        break;
                    case R.id.star_pross1:
                        OnClick_False(maskProgress1);

                        break;
                    case R.id.star_pross2:
                        OnClick_False(maskProgress2);
                        break;
                    case R.id.star_pross3:
                        OnClick_False(maskProgress3);
                        break;
                }
            }
        });
    }

    private void OnClick_False(MaskProgress maskProgress1){
        for (MaskProgress maskProgress : list){
            maskProgress.setProgress(0);
            maskProgress.updateProgress();
        }
        maskProgress1.setProgress(360);
        maskProgress1.initial();
    }

}
