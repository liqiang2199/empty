package com.empty.cup;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.empty.cuplibrary.weight.dataprocessing.JsonUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by empty cup on 2017/7/21.
 */

public class TestEmpty extends Activity{

    private Map<String,String>  maplist = new HashMap<>();
    private ArrayList<Map<String,String>> mapslist = new ArrayList<>();

    private ImageView image_normal;
    private ImageView image_press;
    private LinearLayout liner_ren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

//        liner_ren = (LinearLayout) findViewById(R.id.liner_ren);

//        image_press = (ImageView) findViewById(R.id.image_press);
//        image_normal = (ImageView) findViewById(R.id.image_normal);
//        image_normal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                liner_ren.setVisibility(View.VISIBLE);
//                Animation_F();
//            }
//        });

    }
    private void Animation_F(){
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(liner_ren, "scaleX", 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(liner_ren, "scaleY", 0f, 1f);
        ObjectAnimator scale = ObjectAnimator.ofFloat(liner_ren, "alpha", 1f, 0f);

        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY).with(scale);//两个动画同时开始
        animatorSet.start();
    }
}
