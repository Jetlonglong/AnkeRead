package cn.njcit.ankeread.ui.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.njcit.ankeread.Bean.BannerPic;
import cn.njcit.ankeread.Bean.RankBean;
import cn.njcit.ankeread.R;
import cn.njcit.ankeread.http.Link;
import cn.njcit.ankeread.ui.Activity.RankingActivity;
import cn.njcit.ankeread.ui.Activity.SearchActivity;
import cn.njcit.ankeread.ui.Activity.SortActivity;
import cn.njcit.ankeread.ui.Adapter.QualityAdapter;
import cn.njcit.ankeread.utils.HttpUtils;
import cn.njcit.ankeread.utils.SaveUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LibraryFragment extends Fragment implements View.OnClickListener {

    private Banner mTopBanner;
    private BannerLoader mBannerLoader = new BannerLoader();
    private List<BannerPic.ResultBean.ListBean> datas = new ArrayList<>();
    private List<RankBean.BooksBean> books = new ArrayList<>();
    private TextView mTextSort;
    private TextView mTextRank;
    private TextView mTextCartoon;
    private TextView mQuaRes;
    private RecyclerView mQuaRecy;
    private ProgressBar mQuaLoading;
    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_library, container, false);
        setHasOptionsMenu(true);
        initView(root);
        initData();
        return root;
    }

    private void initView(View root) {
        mTopBanner = (Banner) root.findViewById(R.id.top_banner);
        mTextSort = (TextView) root.findViewById(R.id.text_sort);
        mTextSort.setOnClickListener(this);
        mTextRank = (TextView) root.findViewById(R.id.text_rank);
        mTextRank.setOnClickListener(this);
        mTextCartoon = (TextView) root.findViewById(R.id.text_Cartoon);
        mTextCartoon.setOnClickListener(this);
        mQuaRes = (TextView) root.findViewById(R.id.qua_res);
        mQuaRecy = (RecyclerView) root.findViewById(R.id.qua_recy);
        mQuaRes.setOnClickListener(this);
        mQuaLoading = (ProgressBar) root.findViewById(R.id.qua_loading);
    }

    private void initData() {
        initBannerData();
        if (SaveUtils.getSex(mActivity).equals("男")){
            initQuaManData();
        }else {
            initQuaWomanData();
        }

    }

    private void initBannerData() {
        HttpUtils.httpGet(Link.Banner, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mActivity, "轮播图获取失败，请联系管理员！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();
                final Gson gson = new Gson();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            BannerPic bannerPic = gson.fromJson(string, BannerPic.class);
                            datas.addAll(bannerPic.getResult().getList());
                            handler.sendEmptyMessage(1);
                        } else {
                            Toast.makeText(mActivity, "轮播图获取失败，请联系管理员！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initBanner() {
        List<String> banners = new ArrayList<>();
        for (BannerPic.ResultBean.ListBean data : datas) {
            banners.add(data.getCover());
        }
        mTopBanner.setImageLoader(mBannerLoader);
        mTopBanner.setImages(banners);
        mTopBanner.start();
    }

    private void initQuaManData() {
        mQuaLoading.setVisibility(View.VISIBLE);
        mQuaRecy.setVisibility(View.GONE);
        HttpUtils.httpGet(Link.MaleHotMonth, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "精品推荐获取失败，请截图联系管理员！" + e, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                final Gson gson = new Gson();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        books.clear();
                        RankBean rankBean = gson.fromJson(string, RankBean.class);
                        books.addAll(rankBean.getBooks());
                        handler.sendEmptyMessage(2);
                        mQuaLoading.setVisibility(View.GONE);
                        mQuaRecy.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    private void initQuaWomanData() {
        mQuaLoading.setVisibility(View.VISIBLE);
        mQuaRecy.setVisibility(View.GONE);
        HttpUtils.httpGet(Link.FeMaleHotMonth, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "精品推荐获取失败，请截图联系管理员！" + e, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                final Gson gson = new Gson();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        books.clear();
                        RankBean rankBean = gson.fromJson(string, RankBean.class);
                        books.addAll(rankBean.getBooks());
                        handler.sendEmptyMessage(2);
                        mQuaLoading.setVisibility(View.GONE);
                        mQuaRecy.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    private void getMore() {
        List<RankBean.BooksBean> booksBeans = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            booksBeans.add(books.remove(new Random().nextInt(books.size())));
            Log.d("推荐", books.get(i).getTitle());
        }
        mQuaRecy.setLayoutManager(new GridLayoutManager(mActivity, 4));
        mQuaRecy.setAdapter(new QualityAdapter(booksBeans, mActivity));
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    initBanner();
                    break;
                case 2:
                    getMore();
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qua_res:
                if (SaveUtils.getSex(mActivity).equals("男")){
                    initQuaManData();
                }else {
                    initQuaWomanData();
                }
                Toast.makeText(mActivity, "请稍等哦....", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text_sort:
                mActivity.startActivity(new Intent(mActivity, SortActivity.class));
                break;
            case R.id.text_rank:
                mActivity.startActivity(new Intent(mActivity, RankingActivity.class));
                break;
            case R.id.text_Cartoon:
                Toast.makeText(mActivity, "先预留个按钮.....", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public class BannerLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, final ImageView imageView) {
            Glide.with(context.getApplicationContext()).load((String) path).into(imageView);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_menu:
                startActivity(new Intent(mActivity, SearchActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }
}