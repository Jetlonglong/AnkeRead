package cn.njcit.ankeread.ui.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.njcit.ankeread.Bean.RankBean;
import cn.njcit.ankeread.R;
import cn.njcit.ankeread.http.Link;
import cn.njcit.ankeread.ui.Adapter.RankBookAdapter;
import cn.njcit.ankeread.utils.HttpUtils;
import cn.njcit.ankeread.utils.SaveUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Create by ankele
 * <p>
 * 2020/2/7 - 16:41
 */
public class WeekFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private List<RankBean.BooksBean> books = new ArrayList<>();
    private Activity mActivity;
    private SwipeRefreshLayout mWeekSrl;
    private RecyclerView mWeekRec;
    private LinearLayout mLayLoading;
    private String path = "";
    private int index;

    public WeekFragment(int index) {
        this.index = index;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mWeekSrl = (SwipeRefreshLayout) view.findViewById(R.id.weekSrl);
        mWeekRec = (RecyclerView) view.findViewById(R.id.weekRec);
        mLayLoading = (LinearLayout) view.findViewById(R.id.layLoading);
        mWeekSrl.setOnRefreshListener(this);
    }

    private void initData() {
        mLayLoading.setVisibility(View.VISIBLE);
        if (SaveUtils.getSex(mActivity).equals("男")){
            if (index==0){
                path = Link.MaleHotWeek;
                getBooks();
            }else if (index==1){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-male-hotsearch-week";
                getBooks();
            }else if (index==2){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-male-potenial-week";
                getBooks();
            }else if (index==3){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-male-remain-week";
                getBooks();
            }else if (index==4){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-male-finish-week";
                getBooks();
            }
        }else {
            if (index==0){
                path = Link.FeMaleHotWeek;
                getBooks();
            }else if (index==1){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-female-hotsearch-week";
                getBooks();
            }else if (index==2){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-female-potenial-week";
                getBooks();
            }else if (index==3){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-female-remain-week";
                getBooks();
            }else if (index==4){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-female-finish-week";
                getBooks();
            }
        }
    }

    private void getBooks() {
        HttpUtils.httpGet(path, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mActivity, "访问失败"+e, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                final String string = response.body().string();
                final Gson gson = new Gson();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()){
                            RankBean rankBean = gson.fromJson(string,RankBean.class);
                            books.addAll(rankBean.getBooks());
                            handler.sendEmptyMessage(1);
                            mLayLoading.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    mWeekRec.setAdapter(new RankBookAdapter(mActivity,books));
                    mWeekRec.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
                    break;
            }
        }
    };

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mWeekSrl.setRefreshing(false);
                Toast.makeText(mActivity, "刷新成功...", Toast.LENGTH_SHORT).show();
            }
        }, 1000);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

}
