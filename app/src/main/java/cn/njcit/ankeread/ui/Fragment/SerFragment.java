package cn.njcit.ankeread.ui.Fragment;

import android.app.Activity;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.njcit.ankeread.Bean.SortBook;
import cn.njcit.ankeread.R;
import cn.njcit.ankeread.http.Link;
import cn.njcit.ankeread.ui.Adapter.SortBookAdapter;
import cn.njcit.ankeread.utils.HttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Create by ankele
 * <p>
 * 2020/1/30 - 20:17
 */
public class SerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private String index;
    private String major;
    private SwipeRefreshLayout mSerSrl;
    private RecyclerView mSerBooksRec;
    private LinearLayout mMoreLoading;
    private LinearLayout mLayLoading;
    private int start =0;
    private SortBook sortBook;
    private List<SortBook.BooksBean> booksBeans = new ArrayList<>();
    private SortBookAdapter sortBookAdapter;
    private Activity mActivity;

    public SerFragment(String index, String major) {
        this.index = index;
        this.major = major;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ser, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mSerSrl = (SwipeRefreshLayout) view.findViewById(R.id.serSrl);
        mSerBooksRec = (RecyclerView) view.findViewById(R.id.serBooksRec);
        mMoreLoading = (LinearLayout) view.findViewById(R.id.moreLoading);
        mLayLoading = (LinearLayout) view.findViewById(R.id.layLoading);
        mSerSrl.setOnRefreshListener(this);
    }

    private void initData() {
        mLayLoading.setVisibility(View.VISIBLE);
        HttpUtils.httpGet(Link.SortList + "?gender=" + index + "&type=" + 3 + "&major=" + major + "&start=" + start, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mActivity, "获取失败...请截图联系管理员.." + e, Toast.LENGTH_SHORT).show();
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
                        sortBook = gson.fromJson(string, SortBook.class);
                        if (sortBook.isOk()) {
                            booksBeans.addAll(sortBook.getBooks());
                            handler.sendEmptyMessage(1);
                            mLayLoading.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(mActivity, "获取失败...请截图联系管理员.." + string, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void getMore() {
        HttpUtils.httpGet(Link.SortList + "?gender=" + index + "&type=" + 3 + "&major=" + major + "&start=" + start, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mActivity, "获取失败...请截图联系管理员.." + e, Toast.LENGTH_SHORT).show();
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
                        sortBook = gson.fromJson(string, SortBook.class);
                        if (sortBook.isOk()) {
                            booksBeans.addAll(sortBook.getBooks());
                            mMoreLoading.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(mActivity, "获取失败...请截图联系管理员.." + string, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initBooks() {
        sortBookAdapter = new SortBookAdapter(mActivity, booksBeans);
        mSerBooksRec.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mSerBooksRec.setAdapter(sortBookAdapter);
        mSerBooksRec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //滑动到底部
                if (!recyclerView.canScrollVertically(1)) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            start += 50;
                            if (start==sortBook.getTotal()){
                                Toast.makeText(mActivity, "已无更多数据！", Toast.LENGTH_SHORT).show();
                            }else {
                                getMore();
                                mMoreLoading.setVisibility(View.VISIBLE);
                                sortBookAdapter.notifyDataSetChanged();
                            }
                        }
                    }, 100);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }

    private Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    initBooks();
                    break;
            }
        }
    };

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSerSrl.setRefreshing(false);
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
