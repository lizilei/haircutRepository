package com.nxedu.haircutreserve.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.activity.GoodShopActivity;
import com.nxedu.haircutreserve.activity.HaircutActivity;
import com.nxedu.haircutreserve.activity.HomeInfoActivity;
import com.nxedu.haircutreserve.bean.HeadBean;
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
    private MyItemClickListener mItemClickListener;
    private List<HeadBean.BodyBean> headCarousel;
    private List<HeadBean.BodyBean> hairStylist;
    private List<HeadBean.BodyBean> imgList;
    //type
    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0xff02;
    public static final int TYPE_3 = 0xff03;
    public static final int TYPE_4 = 0xff04;
    public static final int TYPE_MAIN = 0xffff;

    public HairCutHomeMultipleItemAdapter(Context context) {
        this.context = context;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.cheese_1) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.cheese_1)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.cheese_1)  //设置图片加载/解码过程中错误时候显示的图片
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
    public void setDatas(List<HeadBean.BodyBean> data){
        this.headCarousel = data;
        notifyDataSetChanged();
    }
    public void setDataStylist(List<HeadBean.BodyBean> data){
        this.hairStylist = data;
        notifyDataSetChanged();
    }
    public void setDataStyle(List<HeadBean.BodyBean> data){
        this.imgList = data;

        imgList.add(0,new HeadBean.BodyBean());
        imgList.add(0,new HeadBean.BodyBean());
        imgList.add(0,new HeadBean.BodyBean());
        imgList.add(0,new HeadBean.BodyBean());
        notifyDataSetChanged();
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
                return new MyViewHolderMain(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_typemain, parent, false), mItemClickListener);
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
            return imgList!=null?imgList.size():0;
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

    private void bindType1(MyViewHolder1 holder, final int position) {
        holder.slideShowView.setImageUrls(headCarousel);
        holder.slideShowView.startPlay();
//        holder.slideShowView.setOnGolistener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, HomeInfoActivity.class);
//                intent.putExtra("url",headCarousel.get(position).getContenturl());
//                context.startActivity(intent);
//            }
//        });
    }

    private void bindType2(MyViewHolder2 holder, int position) {

    }

    private void bindType3(MyViewHolder3 holder, int position) {

    }

    private void bindType4(MyViewHolder4 holder, int position) {
        RecycleItemAdapterType4 adapter = new RecycleItemAdapterType4(context);
        adapter.getResults(hairStylist);
        holder.item_recyc_type4.setLayoutManager(new GridLayoutManager(holder.item_recyc_type4.getContext(), 3, GridLayoutManager.VERTICAL, false));
        adapter.setItemClickListener(new RecycleItemAdapterType4.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Log.e("---aa",hairStylist.get(position).getTitle()+"--"+hairStylist.size());
                Intent intent = new Intent(context, HomeInfoActivity.class);
                intent.putExtra("url",hairStylist.get(position).getContenturl());
                context.startActivity(intent);
            }
        });
        holder.item_recyc_type4.setAdapter(adapter);
        holder.item_recyc_type4.setNestedScrollingEnabled(false);
    }

    private void bindTypeMain(MyViewHolderMain holder, int position) {
        holder.tv.setText(imgList.get(position).getTitle());
        ImageLoader.getInstance().displayImage(imgList.get(position).getImageurl(), holder.item_imgmain, options);
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        public SlideShowView slideShowView;

        public MyViewHolder1(View itemView) {
            super(itemView);
            slideShowView = (SlideShowView) itemView.findViewById(R.id.slideShowView);
        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

        public MyViewHolder2(final View itemView) {
            super(itemView);
            itemView.findViewById(R.id.layout_wash_cut_blow).setOnClickListener(this);
            itemView.findViewById(R.id.layout_hair_dye).setOnClickListener(this);
            itemView.findViewById(R.id.layout_hu_fa).setOnClickListener(this);
            itemView.findViewById(R.id.layout_hair_perm).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_wash_cut_blow:
                    Toast.makeText(context, "洗剪吹", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, HaircutActivity.class));
                    break;
                case R.id.layout_hair_dye:
                    Toast.makeText(context, "染发", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, HaircutActivity.class));
                    break;
                case R.id.layout_hu_fa:
                    Toast.makeText(context, "护发", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, HaircutActivity.class));
                    break;
                case R.id.layout_hair_perm:
                    Toast.makeText(context, "烫发", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, HaircutActivity.class));
                    break;
            }
        }
    }

    public class MyViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {

        public MyViewHolder3(final View itemView) {
            super(itemView);
            itemView.findViewById(R.id.layout_good_shop).setOnClickListener(this);
            itemView.findViewById(R.id.layout_booking_hairstylist).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_good_shop:
                    Toast.makeText(context, "身边好店", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, GoodShopActivity.class));
                    break;
                case R.id.layout_booking_hairstylist:
                    Toast.makeText(context, "预约发型", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, GoodShopActivity.class));
                    break;
            }
        }
    }

    public class MyViewHolder4 extends RecyclerView.ViewHolder {
        public RecyclerView item_recyc_type4;

        public MyViewHolder4(final View itemView) {
            super(itemView);
            item_recyc_type4 = (RecyclerView) itemView.findViewById(R.id.item_recyc_type4);
        }
    }

    public class MyViewHolderMain extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tv;
        public ImageView item_imgmain;
        private MyItemClickListener mListener;

        public MyViewHolderMain(final View itemView, MyItemClickListener myItemClickListener) {
            super(itemView);
            //将全局的监听赋值给接口
            this.mListener = myItemClickListener;
            itemView.setOnClickListener(this);
            item_imgmain = (ImageView) itemView.findViewById(R.id.item_imgmain);
            tv = ((TextView) itemView.findViewById(R.id.item_imgmain_tv));
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
