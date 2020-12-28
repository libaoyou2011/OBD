package com.baolong.obd.blackcar.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.common.widget.XCRoundRectImageView;
import com.baolong.obd.component.media.AppImageDisplay;

import java.util.ArrayList;
import java.util.List;

import static com.baolong.obd.analysis.activity.AnalysisCommentActivity.Table_telemetry;
import static com.baolong.obd.blackcar.fragment.BlackCarMainFragment.Table_wsh;

public class BlackCarListAdapter extends RecyclerView.Adapter<BlackCarListAdapter.MyViewHolder> {
    private Context mContext;
    private String mType;
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;
    private OnActionClickListener mOnActionClickListener;
    private List<Exhaust> mLists;

    public BlackCarListAdapter(Context paramContext, String paramString) {
        this.mContext = paramContext;
        this.mType = paramString;
    }

    public void setData(List<Exhaust> paramList) {
        this.mLists = paramList;
        notifyDataSetChanged();
    }

    public List<Exhaust> getData() {
        if (this.mLists == null) {
            return new ArrayList<Exhaust>();
        }
        return this.mLists;
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.mItemClickListener = paramOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener paramOnItemLongClickListener) {
        this.mItemLongClickListener = paramOnItemLongClickListener;
    }

    public void setOnActionClickListener(OnActionClickListener paramOnActionClickListener) {
        this.mOnActionClickListener = paramOnActionClickListener;
    }

    @Override
    public int getItemCount() {
        if (this.mLists == null) {
            return 0;
        }
        return this.mLists.size();
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exec_list_black_car, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Exhaust exhaust = (Exhaust) this.mLists.get(position);
        //root点击事件在ExeListFragment中回调，将该item数据返回
        myViewHolder.root.setTag(exhaust);

        //将所有的图片拼接到一起
        StringBuilder urlSB = new StringBuilder();
        if (!TextUtils.isEmpty(exhaust.getTp1())) {
            urlSB.append(exhaust.getTp1());
        }
        if (!TextUtils.isEmpty(exhaust.getTp2())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(exhaust.getTp2());
        }
        if (!TextUtils.isEmpty(exhaust.getTp3())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(exhaust.getTp3());
        }
        if (!TextUtils.isEmpty(exhaust.getTp4())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(exhaust.getTp4());
        }
        if (!TextUtils.isEmpty(exhaust.getTp5())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(exhaust.getTp5());
        }
        //显示的首张图片URL
        String[] urlArray = urlSB.toString().split(";");
        String pictureUrl = null;
        if (urlArray.length > 0) {
            pictureUrl = urlArray[0];
        }
        //glide加载图片
        if (!TextUtils.isEmpty(pictureUrl)) {
            AppImageDisplay.showImg("profile", pictureUrl, this.mContext, R.drawable.img_monitor_pic, myViewHolder.header);
        }

        myViewHolder.recordNo.setText(exhaust.getJlbh());
        myViewHolder.carNo.setText(exhaust.getHphm());
        myViewHolder.addTime.setText(exhaust.getJcrq());
        if (exhaust.getSiteInfo() != null) {
            myViewHolder.stationName.setText(exhaust.getSiteInfo().getDwmc());
        }

        // item项右上角审核状态
        //if (exhaust.getBlackSmokeReview() == 0) {
        if (Table_wsh.equals(this.mType)) {
            //未审核 --> 审核
            myViewHolder.actionContainer.setBackgroundResource(R.drawable.exec_no_do);
            Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.ic_exec_do);
            myViewHolder.isDone.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            myViewHolder.isDone.setText(R.string.execution_sh);

        } else {
            //已审核 --> 查看
            myViewHolder.actionContainer.setBackgroundResource(R.drawable.exec_done);
            Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.ic_exec_look);
            myViewHolder.isDone.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            myViewHolder.isDone.setText(R.string.execution_look);
        }

        // 是否黑烟车项：是否显示
        if (Table_wsh.equals(this.mType) || Table_telemetry.equals(this.mType)) {
            //如果是 "未审核" 导航页，则不显示该项
            myViewHolder.isBlackCarLl.setVisibility(View.GONE);
        } else {
            //如果是 "已审核"、"所有数据" 导航页，则显示是否是黑烟车
//V3报错
//            if (exhaust.getBlackSmokeReview() != 0) {  //已审核
//                if (exhaust.getIsBlackCar() == 1) {
//                    //显示：是黑烟车
//                    myViewHolder.isBlackCarLl.setVisibility(View.VISIBLE);
//                    myViewHolder.isBlackCar.setVisibility(View.VISIBLE);
//                    myViewHolder.isBlackCar.setText("是");
//                } else {
//                    //显示：不是黑烟车
//                    myViewHolder.isBlackCarLl.setVisibility(View.VISIBLE);
//                    myViewHolder.isBlackCar.setVisibility(View.VISIBLE);
//                    myViewHolder.isBlackCar.setText("否");
//                }
//            }
        }

        if (this.mItemClickListener != null) {
            myViewHolder.root.setOnClickListener((view) -> {
                this.mItemClickListener.onItemClick(exhaust, this.mType);
            });
        }

        if (this.mItemLongClickListener != null) {
            myViewHolder.root.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mItemLongClickListener.onItemLongClick(exhaust);
                }
            });
        }

        if (this.mOnActionClickListener != null) {
            myViewHolder.actionContainer.setOnClickListener((view) -> {
                this.mOnActionClickListener.onActionClick(exhaust, this.mType);
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Exhaust dataDetail, String s);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(Exhaust exhaust);
    }

    public interface OnActionClickListener {
        void onActionClick(Exhaust dataDetail, String mType);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout actionContainer;
        TextView addTime;
        TextView carNo;
        XCRoundRectImageView header;
        TextView isBlackCar;
        LinearLayout isBlackCarLl;
        TextView isDone;
        TextView recordNo;
        RelativeLayout root;
        TextView stationName;

        MyViewHolder(View paramView) {
            super(paramView);
            this.root = ((RelativeLayout) paramView.findViewById(R.id.rl_root));
            this.header = ((XCRoundRectImageView) paramView.findViewById(R.id.round_img_url));
            this.isBlackCarLl = ((LinearLayout) paramView.findViewById(R.id.ll_detail_is_black_car));
            this.recordNo = ((TextView) paramView.findViewById(R.id.tv_id));
            this.addTime = ((TextView) paramView.findViewById(R.id.tv_time));
            this.carNo = ((TextView) paramView.findViewById(R.id.tv_car_no));
            this.stationName = ((TextView) paramView.findViewById(R.id.txt_station_name));
            this.isBlackCar = ((TextView) paramView.findViewById(R.id.tv_is_black_car));
            this.actionContainer = ((LinearLayout) paramView.findViewById(R.id.ll_action_container));
            this.isDone = ((TextView) paramView.findViewById(R.id.txt_is_done));
        }
    }
}
