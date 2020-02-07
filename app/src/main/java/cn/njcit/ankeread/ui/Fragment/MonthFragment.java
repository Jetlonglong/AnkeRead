package cn.njcit.ankeread.ui.Fragment;

import android.app.Activity;
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
 * 2020/2/7 - 16:42
 */
public class MonthFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mMonthSrl;
    private RecyclerView mMonthRec;
    private LinearLayout mLayLoading;
    private List<RankBean.BooksBean> books = new ArrayList<>();
    private Activity mActivity;
    private String path = "";
    private int index;

    public MonthFragment(int index) {
        this.index = index;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mMonthSrl = (SwipeRefreshLayout) view.findViewById(R.id.monthSrl);
        mMonthRec = (RecyclerView) view.findViewById(R.id.monthRec);
        mLayLoading = (LinearLayout) view.findViewById(R.id.layLoading);
        mMonthSrl.setOnRefreshListener(this);
    }

    private void initData() {
        mLayLoading.setVisibility(View.VISIBLE);
        if (SaveUtils.getSex(mActivity).equals("男")){
            if (index==0){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-male-hot-month";
                getBooks();
            }else if (index==1){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-male-hotsearch-month";
                getBooks();
            }else if (index==2){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-male-potenial-month";
                getBooks();
            }else if (index==3){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-male-remain-month";
                getBooks();
            }else if (index==4){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-male-finish-month";
                getBooks();
            }
        }else {
            if (index==0){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-male-hot-month";
                getBooks();
            }else if (index==1){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-female-hotsearch-month";
                getBooks();
            }else if (index==2){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-female-potenial-month";
                getBooks();
            }else if (index==3){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-female-remain-month";
                getBooks();
            }else if (index==4){
                path = "http://lunbo.wgfgr.cn/node/info?nodeAlias=rankinglist-female-finish-month";
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
                    mMonthRec.setAdapter(new RankBookAdapter(mActivity,books));
                    mMonthRec.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
                    break;
            }
        }
    };

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMonthSrl.setRefreshing(false);
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
