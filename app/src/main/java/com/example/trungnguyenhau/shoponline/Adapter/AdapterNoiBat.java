package com.example.trungnguyenhau.shoponline.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trungnguyenhau.shoponline.R;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public class AdapterNoiBat extends RecyclerView.Adapter<AdapterNoiBat.ViewHolder> {
    Context context;
    List<String> stringList;

    public AdapterNoiBat(Context context,List<String> stringList){
        this.context = context;
        this.stringList = stringList;
    }

    //Chạy thứ 2
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txtTieuDeNoiBat);
        }
    }

    //Chạy đầu tiên
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_recycleview_noibat,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //Chay thứ 3
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }


}
