package com.nxedu.haircutreserve.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.view.SlideShowView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 郭君华 on 2017/3/22.
 * Email：guojunhua3369@163.com
 */

public class HairCutHomeMultipleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private DisplayImageOptions options;
    //type
    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0xff02;
    public static final int TYPE_3 = 0xff03;
    public static final int TYPE_4 = 0xff04;
    public static final int TYPE_MAIN = 0xffff;

    public HairCutHomeMultipleItemAdapter(Context context) {
        this.context = context;
//        this.results = results;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
                //设置图片加入缓存前，对bitmap进行设置
                //.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_1:
                return new MyViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type1, parent, false));
            case TYPE_2:
                return new MyViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type2, parent, false));
            case TYPE_3:
                return new MyViewHolder3(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type3, parent, false));
            case TYPE_4:
                return new MyViewHolder4(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type4, parent, false));
            case TYPE_MAIN:
                return new MyViewHolderMain(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_typemain, parent, false));
            default:
                Log.d("error", "viewholder is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder1) {
            bindType1((MyViewHolder1) holder, position);
        } else if (holder instanceof MyViewHolder2) {
            bindType2((MyViewHolder2) holder, position);
        } else if (holder instanceof MyViewHolder3) {
            bindType3((MyViewHolder3) holder, position);
        } else if (holder instanceof MyViewHolder4) {
            bindType4((MyViewHolder4) holder, position);
        } else if (holder instanceof MyViewHolderMain) {
            bindTypeMain((MyViewHolderMain) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return 40;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_1;
        } else if (position == 1) {
            return TYPE_2;
        } else if (position == 2) {
            return TYPE_3;
        } else if (position == 3) {
            return TYPE_4;
        } else {
            return TYPE_MAIN;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_1:
                        case TYPE_2:
                        case TYPE_3:
                        case TYPE_4:
                            return gridManager.getSpanCount();
                        default:
                            return 1;
                    }
                }
            });
        }
    }


    private void bindType1(MyViewHolder1 holder, int position) {
        String img = "http://h.hiphotos.baidu.com/zhidao/pic/item/0eb30f2442a7d9334f268ca9a84bd11372f00159.jpg";
        String[] imgs = new String[]{img, img, img, img, img, img, img};
        holder.slideShowView.setImageUrls(imgs);
        holder.slideShowView.startPlay();
    }

    private void bindType2(MyViewHolder2 holder, int position) {
    }

    private void bindType3(MyViewHolder3 holder, int position) {

    }

    private void bindType4(MyViewHolder4 holder, int position) {
        String img = "http://pica.nipic.com/2007-10-09/200710994020530_2.jpg";
        holder.item_recyc_type4.setLayoutManager(new GridLayoutManager(holder.item_recyc_type4.getContext(), 3, GridLayoutManager.VERTICAL, false));
        List<String> results = new ArrayList<String>();
        String img1 = "http://pica.nipic.com/2007-10-09/200710994020530_2.jpg";
        results.add(img1);
        results.add(img1);
        results.add(img1);
        results.add(img1);
        results.add(img1);
        results.add(img1);
        holder.item_recyc_type4.setAdapter(new RecycleItemAdapterType4(context, results));
        holder.item_recyc_type4.setNestedScrollingEnabled(false);
    }

    private void bindTypeMain(MyViewHolderMain holder, int position) {
        String img = "http://pica.nipic.com/2007-10-09/200710994020530_2.jpg";
        ImageLoader.getInstance().displayImage(img, holder.item_imgmain, options);
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        public SlideShowView slideShowView;

        public MyViewHolder1(View itemView) {
            super(itemView);
            slideShowView = (SlideShowView) itemView.findViewById(R.id.slideShowView);
        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        public MyViewHolder2(final View itemView) {
            super(itemView);
        }
    }

    public class MyViewHolder3 extends RecyclerView.ViewHolder {

        public MyViewHolder3(final View itemView) {
            super(itemView);
        }
    }

    public class MyViewHolder4 extends RecyclerView.ViewHolder {
        public RecyclerView item_recyc_type4;

        public MyViewHolder4(final View itemView) {
            super(itemView);
            item_recyc_type4 = (RecyclerView) itemView.findViewById(R.id.item_recyc_type4);
        }
    }

    public class MyViewHolderMain extends RecyclerView.ViewHolder {
        public ImageView item_imgmain;

        public MyViewHolderMain(final View itemView) {
            super(itemView);
            item_imgmain = (ImageView) itemView.findViewById(R.id.item_imgmain);
        }
    }
}
