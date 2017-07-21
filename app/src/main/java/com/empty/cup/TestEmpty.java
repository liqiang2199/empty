package com.empty.cup;

import android.app.Activity;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void Init(){
        try {
            JsonUtil.rentrunJsonBeen(null,mapslist);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
