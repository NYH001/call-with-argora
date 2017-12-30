package com.nicholasnie.call_with_argora.Service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicholasnie.call_with_argora.R;

import java.util.List;

/**
 * Created by NicholasNie on 2017/12/25.
 */

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<String> mDatas;

    public NameAdapter(Context context, List<String> datas){
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.name_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tvName = (TextView)view.findViewById(R.id.tv_name);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvName.setText(mDatas.get(position));

        if(mOnItemClickListener != null){
            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public String getText(int position){
        return mDatas.get(position);
    }
}
