package com.leo.mytransformove;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihang on 2016/4/18.
 */
public class MyOtherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> burstList;
    private LayoutInflater inflater;
    private View.OnClickListener listener;


    /**
     * 是否可见 在移动动画完毕之前不可见，动画完毕后可见
     */
    boolean isVisible = true;
    /**
     * 要删除的position
     */
    public int remove_position = -1;
    /**
     * 是否是用户频道
     */
    private boolean isUser = false;


    public MyOtherAdapter(Context context, View.OnClickListener listener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    public void setBurstList(ArrayList<String> burstList) {
        this.burstList = burstList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;
        View v = inflater.inflate(R.layout.item_userlike_other, parent, false);
        holder = new ViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;

        String currentStr = burstList.get(position);
        viewHolder.image.setVisibility(View.VISIBLE);
        Glide.with(context).load(currentStr).dontAnimate().
                placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).
                transform(new CenterCrop(context), new GlideRoundTransform(context, 5))
                .into(viewHolder.image);


        if (!isVisible && (position == 0)) {
            viewHolder.image.setVisibility(View.GONE);
            viewHolder.relative_item.setSelected(true);
            viewHolder.relative_item.setEnabled(true);
        }
        if (remove_position == position) {
            viewHolder.image.setVisibility(View.GONE);
        }


        viewHolder.relative_item.setTag(R.id.relative_item_other, position);
        viewHolder.relative_item.setOnClickListener(listener);

    }


    @Override
    public int getItemCount() {
        return burstList == null ? 0 : burstList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        RelativeLayout relative_item;


        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            relative_item = itemView.findViewById(R.id.relative_item_other);
        }
    }

    public String getItem(int position) {
        if (burstList != null && burstList.size() != 0) {
            return burstList.get(position);
        }
        return null;
    }


    /**
     * 获取频道列表
     */
    public List<String> getChannnelLst() {
        return burstList;
    }

    /**
     * 添加频道列表
     */
    public void addItem(String channel) {
        burstList.clear();
        burstList.add(0, channel);
        notifyDataSetChanged();
    }

    /**
     * 设置删除的position
     */
    public void setRemove(int position) {
        remove_position = position;
        notifyDataSetChanged();
        // notifyDataSetChanged();
    }

    /**
     * 删除频道列表
     */
    public void remove() {
        burstList.remove(remove_position);
        remove_position = -1;
        notifyDataSetChanged();
    }


    /**
     * 设置频道列表
     */
    public void setListDate(List<String> list) {
        burstList = list;
    }

    /**
     * 获取是否可见
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * 设置是否可见
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }


}
