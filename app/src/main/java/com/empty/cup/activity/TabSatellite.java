package com.empty.cup.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.empty.cup.R;
import com.empty.cuplibrary.weight.tools.ToastLogTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by empty cup on 2017/8/29.
 * 实现卫星菜单效果 tab 动画
 */

public class TabSatellite extends Activity{

    private static final String Tag = "TabSatellite";
    private ImageView add_image;
    private ImageView iamage_photo1;
    private ImageView iamage_photo2;
    private ImageView iamage_photo3;
    private ImageView iamage_photo4;
    private ImageView iamage_photo5;
    private ImageView iamage_photo6;

    private int x;
    private int y;
    private int r;//设置的半径

    private List<View> imagelist = new ArrayList<>();
    private double sin30;
    private double cos30;
    private int index = 1;

    private ObjectAnimator objectAnimatorQiute1;
    private ObjectAnimator objectAnimatorQiute2;
    private ObjectAnimator objectAnimatorQiute3;
    private ObjectAnimator objectAnimatorQiute4;
    private AnimatorSet setout;
    private AnimatorSet setcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbarsetllite);
        Init();
    }

    private void Init() {
        add_image = (ImageView) findViewById(R.id.add_image);

        iamage_photo1 = (ImageView) findViewById(R.id.iamage_photo1);
        iamage_photo2 = (ImageView) findViewById(R.id.iamage_photo2);
        iamage_photo3 = (ImageView) findViewById(R.id.iamage_photo3);
        iamage_photo4 = (ImageView) findViewById(R.id.iamage_photo4);
        iamage_photo5 = (ImageView) findViewById(R.id.iamage_photo5);

        imagelist.add(iamage_photo1);
        imagelist.add(iamage_photo2);
        imagelist.add(iamage_photo3);
        imagelist.add(iamage_photo4);
        imagelist.add(iamage_photo5);

        Onclick(add_image);

        OnClick(iamage_photo1);
        OnClick(iamage_photo2);
        OnClick(iamage_photo3);
        OnClick(iamage_photo4);
        OnClick(iamage_photo5);

    }

    private void Onclick(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 1){
                    get_AddImageXY();
                }else {
                    image_qute();
                }


            }
        });
    }

    /**
     * 获取add_image的XY坐标
     */
    private void get_AddImageXY(){

        int[] location = new int[2];
        add_image.getLocationOnScreen(location);
        x = location[0];
        r = x;
        y = location[1];
        Log.v(Tag,x +" X z坐标  Y   "+y);
        image_numTran();
//        iamage_photo1.setTranslationX(r/2);
//        iamage_photo1.setTranslationY(y/2);
    }
    private void OnClick(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.iamage_photo1:
                        ToastLogTools.Toast_ShortTip(TabSatellite.this,"点击了iamage_photo1");
                        break;
                    case R.id.iamage_photo2:
                        ToastLogTools.Toast_ShortTip(TabSatellite.this,"点击了iamage_photo2");
                        break;
                    case R.id.iamage_photo3:
                        ToastLogTools.Toast_ShortTip(TabSatellite.this,"点击了iamage_photo3");
                        break;
                    case R.id.iamage_photo4:
                        ToastLogTools.Toast_ShortTip(TabSatellite.this,"点击了iamage_photo4");
                        break;
                    case R.id.iamage_photo5:
                        ToastLogTools.Toast_ShortTip(TabSatellite.this,"点击了iamage_photo5");
                        break;
                }
            }
        });
    }

    /**
     * 移动 出去
     * Math.sqrt(3); 开根号
     * Math.sin((30))) 传入弧度
     */
    private void image_numTran(){
        for (int i = 0;i<imagelist.size();i++){

            sin30 = Math.sin((30*(i+1))*Math.PI/180);
            cos30 = Math.cos((30*(i+1))*Math.PI/180);

//            imagelist.get(i).setTranslationX((float) -cos30*(r-r/3));
//            imagelist.get(i).setTranslationY(-(float) sin30*(r-r/3));

            objectAnimatorQiute2=ObjectAnimator.ofFloat(imagelist.get(i), "translationY", 0f,-(float) sin30*(r-r/3));

            objectAnimatorQiute1=ObjectAnimator.ofFloat(imagelist.get(i), "translationX",0f,(float) -cos30*(r-r/3));

//            objectAnimatorQiute1.setTarget(imagelist.get(i));
//            objectAnimatorQiute2.set(imagelist.get(i));
//            objectAnimatorQiute1.setFloatValues(0f,0f,(float) -cos30*(r-r/3));
//            objectAnimatorQiute2.setFloatValues(-(float) sin30*(r-r/3));

            if (setout == null){
                setout=new AnimatorSet();
            }
            setout.setDuration(500);
            setout.playTogether(objectAnimatorQiute1,objectAnimatorQiute2);//同时执行
            setout.start();

        }
        index = 2;
    }

    /**
     * 回来
     */
    private void image_qute(){
        for (int i = 0;i<imagelist.size();i++){

            ObjectAnimator objectAnimatorretain5 = ObjectAnimator.ofFloat(imagelist.get(i), "rotation", 0.0f,360.0f);
//            ObjectAnimator objectAnimatorretain6 = ObjectAnimator.ofFloat(imagelist.get(i), "retainY", 0.0f,360.0f);
            objectAnimatorretain5.setDuration(500);

            objectAnimatorretain5.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    for (int i = 0;i<imagelist.size();i++){
                        objectAnimatorQiute3=ObjectAnimator.ofFloat(imagelist.get(i), "translationX",0f);
                        objectAnimatorQiute4=ObjectAnimator.ofFloat(imagelist.get(i), "translationY", 0f);
                        AnimatorSet setcome=new AnimatorSet();
//                        if (setcome == null){
//                            setcome=new AnimatorSet();
//
//                        }
                        setcome.setDuration(500);
                        setcome.playTogether(objectAnimatorQiute3,objectAnimatorQiute4);//同时执行
                        setcome.start();
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            objectAnimatorretain5.start();


        }
        index = 1;
    }

}
