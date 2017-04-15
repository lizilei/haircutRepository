package com.nxedu.haircutreserve.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.bean.HeadBean;

import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleItemAdapterType4 extends RecyclerView.Adapter {
    private Context context;
    private List<HeadBean.BodyBean> results;
    private MyItemClickListener mItemClickListener;

    //get & set
    public void getResults(List<HeadBean.BodyBean> results) {
        this.results = results;
//        results.add(new HeadBean.BodyBean());
        notifyDataSetChanged();
    }

    public RecycleItemAdapterType4(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type4_item, parent, false),mItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            bind((ItemViewHolder) holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return results!=null? results.size():0;
    }

    private void bind(ItemViewHolder holder, int position){
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.cheese_1) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.cheese_1)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.cheese_1)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
//                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
                //设置图片加入缓存前，对bitmap进行设置
                //.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成

        ImageLoader.getInstance().displayImage(results.get(position).getImageurl(), holder.item_recyc_type4_item_img, options);
        holder.tv.setText(results.get(position).getTitle());
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv;
        public ImageView item_recyc_type4_item_img;
        private MyItemClickListener mListener;
        public ItemViewHolder(View itemView, MyItemClickListener myItemClickListener) {
            super(itemView);
            item_recyc_type4_item_img = (ImageView) itemView.findViewById(R.id.item_recyc_type4_item_img);
            tv = ((TextView) itemView.findViewById(R.id.item_recyc_type4_item_tv));
            //将全局的监听赋值给接口
            this.mListener = myItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     *
     * @param myItemClickListener
     */
    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }
}
