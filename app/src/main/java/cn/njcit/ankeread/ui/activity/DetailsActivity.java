package cn.njcit.ankeread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import cn.carbs.android.expandabletextview.library.ExpandableTextView;
import cn.njcit.ankeread.bean.NovelsResult;
import cn.njcit.ankeread.R;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mMTitle;
    private Toolbar mToolbar;
    private ImageView mDetailsPic;
    /**
     * 斗罗大陆
     */
    private TextView mDetailsName;
    /**
     * 唐家三少
     */
    private TextView mDetailsAuthor;
    /**
     * 玄幻
     */
    private TextView mDetailsTag;
    /**
     * 书籍状态：连载中
     */
    private TextView mDetailsStatus;
    /**
     * 这里是简介 简介这里是简介 这里是简介 这里是简介 这里是简介这里是简介
     */
    private ExpandableTextView mDetailsInfo;
    private LinearLayout mLayNew;
    private LinearLayout mLayCatalog;
    /**
     * 加入书架
     */
    private TextView mTextColl;
    /**
     * 开始阅读
     */
    private TextView mTextStartRead;
    /**
     * 全本缓存
     */
    private TextView mTextDow;

    private static NovelsResult.ListBean details;
    /**
     * 最后更新时间：2020-01-05
     */
    private TextView mDetailsTime;
    /**
     * 这里是最近更新的
     */
    private TextView mDetailsNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        initData();
    }

    public void startActivity(Context context, NovelsResult.ListBean datas) {
        Intent intent = new Intent(context, DetailsActivity.class);
        context.startActivity(intent);
        details = datas;
        Log.d("源",details.getUrl());
    }

    private void initView() {
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDetailsPic = (ImageView) findViewById(R.id.details_pic);
        mDetailsName = (TextView) findViewById(R.id.details_name);
        mDetailsAuthor = (TextView) findViewById(R.id.details_author);
        mDetailsTag = (TextView) findViewById(R.id.details_tag);
        mDetailsStatus = (TextView) findViewById(R.id.details_status);
        mDetailsInfo = (ExpandableTextView) findViewById(R.id.details_info);
        mLayNew = (LinearLayout) findViewById(R.id.lay_new);
        mLayNew.setOnClickListener(this);
        mLayCatalog = (LinearLayout) findViewById(R.id.lay_catalog);
        mLayCatalog.setOnClickListener(this);
        mTextColl = (TextView) findViewById(R.id.text_coll);
        mTextColl.setOnClickListener(this);
        mTextStartRead = (TextView) findViewById(R.id.text_startRead);
        mTextStartRead.setOnClickListener(this);
        mTextDow = (TextView) findViewById(R.id.text_dow);
        mTextDow.setOnClickListener(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mToolbar.setTitle("");
        mMTitle.setText("书籍详情");
        mDetailsTime = (TextView) findViewById(R.id.details_time);
        mDetailsNew = (TextView) findViewById(R.id.details_new);
    }

    private void initData() {
        Glide.with(this).load(details.getCover()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                mDetailsPic.setImageResource(R.mipmap.nocover);
                return true;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(mDetailsPic);
        mDetailsName.setText(details.getName());
        mDetailsAuthor.setText(details.getAuthor());
        mDetailsInfo.setText("    " + details.getIntroduce());
        mDetailsTag.setText(details.getTag());
        mDetailsTime.setText("最后更新时间：" + details.getTime());
        mDetailsStatus.setText("书籍状态：" + details.getStatus());
        mDetailsNew.setText(details.getNum());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.lay_new:
                break;
            case R.id.lay_catalog:
                break;
            case R.id.text_coll:
                break;
            case R.id.text_startRead:
                break;
            case R.id.text_dow:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
