package com.leo.mytransformove;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.leo.mytransformove.R.id.recyclerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mUserGv, mOtherGv;
    private MyAdapter mUserAdapter;
    private MyOtherAdapter mOtherAdapter;
    private ArrayList<String> mUserList = new ArrayList<>();
    private ArrayList<String> mOtherList = new ArrayList<>();
    private boolean isMove = false;


    private TextView text_desctionBySearch;//搜索框上的描述
    private ImageView image_faceso_search;//搜索框的图标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserGv = (RecyclerView) findViewById(R.id.recyclerView);
        mOtherGv = (RecyclerView) findViewById(R.id.recyclerView_top);
        text_desctionBySearch = (TextView) findViewById(R.id.text_desctionBySearch);
        image_faceso_search = (ImageView) findViewById(R.id.image_faceso_search);
        mUserAdapter = new MyAdapter(this, this);
        mOtherAdapter = new MyOtherAdapter(this, this);

        mUserList.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1530162782&di=2893e8a9a31ec343b327923eaf85b1a2&src=http://img4.duitang.com/uploads/item/201611/19/20161119111625_WaxdZ.thumb.700_0.jpeg");
        mUserList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530172867946&di=5f5169e4016f910f7fc499c993027b9e&imgtype=0&src=http%3A%2F%2Fqimg.hxnews.com%2F2018%2F0508%2F1525738181747.jpeg");
        mUserList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530172959073&di=1bfbf2bb5e88373a853ab9dda90b9e2d&imgtype=0&src=http%3A%2F%2Fimg5.iqilu.com%2Fc%2Fu%2F2017%2F0113%2F1484266302953.jpg");
        mUserList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530172980994&di=a5bf6cf26060438558721719d8d9d53a&imgtype=0&src=http%3A%2F%2Fimgq.duitang.com%2Fuploads%2Fitem%2F201502%2F11%2F20150211185113_caJZY.thumb.700_0.jpeg");
        mUserList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530173016800&di=59749cc186852cdf1e0a22c1b502a331&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F83%2F91%2F300000559649127552918930184_950.jpg");
        mUserList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530767749&di=90a2a63dfbd0c50fc3b2f5fe6b32d1e4&imgtype=jpg&er=1&src=http%3A%2F%2Fcimg.163.com%2Fmovie%2F0301%2F15%2Fcecilia11_b.jpg");
        mUserList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530173057896&di=c488e489caf25af6fa393abaaf7b36ce&imgtype=0&src=http%3A%2F%2Fnews.youth.cn%2Fyl%2F201309%2FW020130906546885673394.jpg");
        mUserList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530173098721&di=0a9db2b1ff91cadb1474665fb603921c&imgtype=0&src=http%3A%2F%2Fs6.sinaimg.cn%2Fmw690%2F00330zOMzy72Gb9kqeV15%26690");
        mUserList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530173098721&di=e238752b56553e7af30c8e6b4d3692c8&imgtype=0&src=http%3A%2F%2Fcnhuadong.net%2Fuploadfiles%2Fimages%2F2014-2-7%2F20142720524jpg10503.jpg");
        mUserAdapter.setBurstList(mUserList);
        mOtherAdapter.setBurstList(mOtherList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(MainActivity.this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mUserGv.setLayoutManager(layoutManager1);
        mUserGv.setAdapter(mUserAdapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mOtherGv.setLayoutManager(layoutManager);
        mOtherGv.setAdapter(mOtherAdapter);


    }

    private String currentStr;

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.relative_item) {
            //这是点击下面的搜索框
            text_desctionBySearch.setVisibility(View.GONE);

            if (isMove) {
                return;
            }
            isMove = true;
            Log.e("我去看看撒", mOtherAdapter.getChannnelLst().size() + "");
            Log.e("我草这是什么", isMove + "");
            final int position = (int) view.getTag(R.id.relative_item);
            final ImageView moveImageView = getView(view);
            if (moveImageView != null) {
                ImageView newTextView = (ImageView) view.findViewById(R.id.image);
                final int[] startLocation = new int[2];
                newTextView.getLocationInWindow(startLocation);
                final String channel = mUserAdapter.getItem(position);//获取点击的频道内容
                mOtherAdapter.setVisible(false);
                if (mOtherAdapter.getChannnelLst().size() > 0) {
                    currentStr = mOtherAdapter.getChannnelLst().get(0);
                    Log.e("这个东西是我想要的吧", currentStr);
                }
                //添加到最后一个
                mOtherAdapter.addItem(channel);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        try {
                            int[] endLocation = new int[2];
                            //获取终点的坐标
                            mOtherGv.getChildAt(0).getLocationInWindow(endLocation);
                            MoveAnim(moveImageView, startLocation, endLocation, channel, true);
                            mUserAdapter.setRemove(position);
                        } catch (Exception localException) {
                        }
                    }
                }, 50L);
            }
        } else if (view.getId() == R.id.relative_item_other) {
            //这是点击上面的搜索框

            if (isMove) {
                return;
            }
            isMove = true;
            Log.e("我草这是什么", isMove + "");


            final int position = (int) view.getTag(R.id.relative_item_other);
            final ImageView moveImageView = getView(view);
            if (moveImageView != null) {
                ImageView newTextView = (ImageView) view.findViewById(R.id.image);
                final int[] startLocation = new int[2];
                newTextView.getLocationInWindow(startLocation);
                final String channel = mOtherAdapter.getItem(position);
                mUserAdapter.setVisible(false);
                //添加到最后一个
                mUserAdapter.addItem(channel);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        try {
                            int[] endLocation = new int[2];
                            //获取终点的坐标
                            mUserGv.getChildAt(0).getLocationInWindow(endLocation);
                            MoveAnim(moveImageView, startLocation, endLocation, channel, false);
                            mOtherAdapter.setRemove(position);
                        } catch (Exception localException) {
                        }
                    }
                }, 50L);
            }
        }


    }


    /**
     * 获取点击的Item的对应View，
     * 因为点击的Item已经有了自己归属的父容器MyGridView，所有我们要是有一个ImageView来代替Item移动
     *
     * @param view
     * @return
     */
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(this);
        iv.setImageBitmap(cache);
        return iv;
    }


    /**
     * 点击ITEM移动动画
     *
     * @param moveView
     * @param startLocation
     * @param endLocation
     * @param moveChannel
     */
    private void MoveAnim(View moveView, int[] startLocation, int[] endLocation, final String moveChannel,
                          final boolean isUser) {
        int[] initLocation = new int[2];
        //获取传递过来的VIEW的坐标
        moveView.getLocationInWindow(initLocation);
        //得到要移动的VIEW,并放入对应的容器中
        final ViewGroup moveViewGroup = getMoveViewGroup();
        final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
        //创建移动动画
        TranslateAnimation moveAnimation = new TranslateAnimation(
                startLocation[0], endLocation[0], startLocation[1],
                endLocation[1]);
        moveAnimation.setDuration(300L);//动画时间
        //动画配置
        AnimationSet moveAnimationSet = new AnimationSet(true);
        moveAnimationSet.setFillAfter(false);//动画效果执行完毕后，View对象不保留在终止的位置
        moveAnimationSet.addAnimation(moveAnimation);
        mMoveView.startAnimation(moveAnimationSet);
        moveAnimationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isMove = false;
                moveViewGroup.removeView(mMoveView);
                // 判断点击的是DragGrid还是OtherGridView
                if (isUser) {
                    //点击下面的搜索框动画结束
                    mOtherAdapter.setVisible(true);
                    mOtherAdapter.notifyDataSetChanged();
                    if (!TextUtils.isEmpty(currentStr)) {
                        mUserAdapter.addItemLeo(currentStr);
                        currentStr = "";
                    }
                    mUserAdapter.remove();

                    if (mOtherAdapter.getChannnelLst().size() != 0) {
                        image_faceso_search.setSelected(true);
                    }

                } else {
                    //点击上面的搜索框结束
                    mUserAdapter.setVisible(true);
                    mUserAdapter.notifyDataSetChanged();
                    mOtherAdapter.remove();

                    if (mOtherAdapter.getChannnelLst().size() == 0) {
                        text_desctionBySearch.setVisibility(View.VISIBLE);
                        image_faceso_search.setSelected(false);
                    }
                }
            }
        });
    }


    /**
     * 创建移动的ITEM对应的ViewGroup布局容器
     * 用于存放我们移动的View
     */
    private ViewGroup getMoveViewGroup() {
        //window中最顶层的view
        ViewGroup moveViewGroup = (ViewGroup) getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(this);
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
    }


    /**
     * 获取移动的VIEW，放入对应ViewGroup布局容器
     *
     * @param viewGroup
     * @param view
     * @param initLocation
     * @return
     */
    private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        view.setLayoutParams(mLayoutParams);
        return view;
    }

}
