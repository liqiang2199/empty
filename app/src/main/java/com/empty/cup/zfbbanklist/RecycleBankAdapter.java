package com.empty.cup.zfbbanklist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.empty.cup.R;

/**
 * Created by empty cup on 2017/12/17.
 * 适配器  层叠效果
 */

public class RecycleBankAdapter extends RecyclerView.Adapter<ViewHandler>{
    @Override
    public ViewHandler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zfbbank_item,parent,false);
        ViewHandler holder = new ViewHandler(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHandler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


}
class ViewHandler extends RecyclerView.ViewHolder{

    public ViewHandler(View itemView) {
        super(itemView);
    }
}
