package com.coolweather.coolweather.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coolweather.coolweather.R;
import com.coolweather.coolweather.activity.MainActivity;
import com.coolweather.coolweather.model.AddedCity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bian on 2016/5/6 11:40.
 */
public class AreaMangerAdapter extends RecyclerView.Adapter {

    private List<AddedCity> mList;
    private Context mContext;
    private Boolean mIsEdit = false;

    public AreaMangerAdapter(List<AddedCity> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_area_manger, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        AddedCity addedCity = mList.get(viewHolder.getLayoutPosition());
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("position", viewHolder.getLayoutPosition());
                ((Activity) mContext).setResult(Activity.RESULT_OK, intent);
                ((Activity) mContext).finish();
                ((Activity) mContext).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
        viewHolder.timeText.setText(addedCity.getTime());
        viewHolder.cityText.setText(addedCity.getName());
        viewHolder.wenDuText.setText(addedCity.getWenDu());
        if (addedCity.getDdingWei()) {
            viewHolder.dingWeiImage.setVisibility(View.VISIBLE);
        } else {
            viewHolder.dingWeiImage.setVisibility(View.GONE);
        }
        if (mIsEdit) {
            viewHolder.relativeLayout.setEnabled(false);
            viewHolder.deleteImg.setVisibility(View.VISIBLE);
            viewHolder.deleteImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(viewHolder.getLayoutPosition());
                    notifyDataSetChanged();
                }
            });
        } else {
            viewHolder.relativeLayout.setEnabled(true);
            viewHolder.deleteImg.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void isEdit(Boolean isEdit) {
        mIsEdit = isEdit;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_area_manger_layout)
        RelativeLayout relativeLayout;
        @Bind(R.id.time_text)
        TextView timeText;
        @Bind(R.id.ding_wei_image)
        ImageView dingWeiImage;
        @Bind(R.id.city_text)
        TextView cityText;
        @Bind(R.id.wen_du_text)
        TextView wenDuText;
        @Bind(R.id.delete_img)
        ImageView deleteImg;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setList(List<AddedCity> list) {
        mList = list;
        notifyDataSetChanged();
    }
}
