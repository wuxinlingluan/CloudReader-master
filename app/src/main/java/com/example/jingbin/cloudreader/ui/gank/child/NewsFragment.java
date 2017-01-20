package com.example.jingbin.cloudreader.ui.gank.child;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.bean.JokeBean;
import com.example.jingbin.cloudreader.bean.NewsBean;
import com.example.jingbin.cloudreader.ui.gank.NewsWebActivity;
import com.example.jingbin.cloudreader.utils.ImgLoadUtil;
import com.example.jingbin.cloudreader.utils.IntentUtils;
import com.example.jingbin.cloudreader.utils.OkHttpClientUtils;
import com.example.jingbin.cloudreader.utils.ToastUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qbw.customview.RefreshLoadMoreLayout;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ${sheldon} on 2017/1/17.
 */
public class NewsFragment extends Fragment implements RefreshLoadMoreLayout.CallBack {


    private List<NewsBean.ResultBean.DataBean> mDatas=new ArrayList<>();
    protected static final int GET_SUCCESS =1<<0;
    protected static final int NET_ERROR=1<<1;
    protected static final int PULL_DOWN_REFRESHING=1<<2;//下拉刷新
    protected static final int LOADING_MORE=1<<3;//上拉加载
    private int pageNum=1;
    private Message message;
    private RefreshLoadMoreLayout mRefreshLoadMoreLayout;
    private RecyclerView recyclerView;
    private CommonAdapter<NewsBean.ResultBean.DataBean> commonAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_joke, null);
        mRefreshLoadMoreLayout = (RefreshLoadMoreLayout)inflate.findViewById(R.id.rlm);//上拉加载 下拉刷新
        mRefreshLoadMoreLayout.init(new RefreshLoadMoreLayout.Config(this).canRefresh(true).canLoadMore(true).autoLoadMore().showLastRefreshTime(getActivity() .getClass(), "yyyy-MM-dd").multiTask());//设置属性
        recyclerView = (RecyclerView) mRefreshLoadMoreLayout.getContentView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        initEvent();
        getAccountBalanceOperation();
        return inflate;
    }
    /**
     * 上拉加载,下拉刷新
     * */
    private void initEvent() {
    }


    /**
     * 获取news内容
     */
    private void getAccountBalanceOperation() {
        String url = "http://v.juhe.cn/toutiao/index";
        List<OkHttpClientUtils.Param> params = new ArrayList<OkHttpClientUtils.Param>();
        params.add(new OkHttpClientUtils.Param("key", "1b4862c0040bf72d8079a70d7a3386ba"));
        OkHttpClientUtils.postAsyn(url, params,
                new OkHttpClientUtils.ResultCallback<NewsBean>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                        // 隔0.15s发送网络错误的空消息
                        mHandler.sendEmptyMessageDelayed(NET_ERROR, 150);
                    }

                    @Override
                    public void onResponse(NewsBean response) {
                        if ("成功的返回".equals(response.getReason())){
                            message = mHandler.obtainMessage();
                            message.what= GET_SUCCESS;
                            message.obj= response.getResult();
                            mHandler.sendMessage(message);
                        }
                    }

                });
    }
    /**
     * 定义消息处理器
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case NET_ERROR:
                    ToastUtil.showToast("网络故障");
                    break;

                case GET_SUCCESS:
                    resetUpPullOrDownPullStatus();
                    NewsBean.ResultBean resultBean = (NewsBean.ResultBean) msg.obj;//获取数据
                         mDatas = resultBean.getData();
                    commonAdapter = new CommonAdapter<NewsBean.ResultBean.DataBean>(getActivity(), R.layout.item_newslist, mDatas) {
                        @Override
                            protected void convert(final ViewHolder holder, NewsBean.ResultBean.DataBean dataBean, int position) {
                                holder.setText(R.id.tv_news_title,mDatas.get(position).getTitle());//设置数据
                                holder.setText(R.id.tv_new_from,"来源:"+mDatas.get(position).getAuthor_name());
                                holder.setText(R.id.tv_news_time,mDatas.get(position).getDate());
                            String thumbnail_pic_s = mDatas.get(position).getThumbnail_pic_s();
                            Glide.with(getActivity()).load(thumbnail_pic_s).asBitmap().into(new SimpleTarget<Bitmap>() {  //解析图片
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    holder.setImageBitmap(R.id.iv_news_icon,resource);
                                }
                            });

                        }
                        };
                        recyclerView.setAdapter(commonAdapter);
                    commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {  //条目点击
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            String url = mDatas.get(position).getUrl();
//                            Intent intent = new Intent(getActivity(), NewsWebActivity.class);
//                            intent.putExtra("url",url);
//                            System.out.println("111111111111111"+url);
//                            getActivity().startActivity(intent);
                            Bundle bundle = new Bundle();
                            bundle.putString("url",url);
                            IntentUtils.changeActivity(getActivity(),NewsWebActivity.class,bundle);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                    break;
                case PULL_DOWN_REFRESHING:
                    getAccountBalanceOperation();
                    break;
                case LOADING_MORE:
                    ToastUtil.showToast("没有更多数据");
                    break;
            }
        };
    };

    //重置上拉下拉状态
    private void resetUpPullOrDownPullStatus() {
            if(mRefreshLoadMoreLayout.isCanLoadMore()){
                mRefreshLoadMoreLayout.stopLoadMore();
            }
        if (mRefreshLoadMoreLayout.isCanRefresh()){
            mRefreshLoadMoreLayout.stopRefresh();
        }
    }


    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(PULL_DOWN_REFRESHING, 150);
    }

    @Override
    public void onLoadMore() {
//        mHandler.sendEmptyMessageDelayed(LOADING_MORE, 150);
        ToastUtil.showToast("没有更多数据");
        mRefreshLoadMoreLayout.stopLoadMore();
    }
}
