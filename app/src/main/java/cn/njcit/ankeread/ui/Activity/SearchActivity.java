package cn.njcit.ankeread.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cn.njcit.ankeread.Bean.HotWord;
import cn.njcit.ankeread.Bean.NovelsResult;
import cn.njcit.ankeread.DB.SearchHistory;
import cn.njcit.ankeread.R;
import cn.njcit.ankeread.http.Link;
import cn.njcit.ankeread.ui.Adapter.HistoryAdapter;
import cn.njcit.ankeread.ui.Adapter.HotAdapter;
import cn.njcit.ankeread.ui.Adapter.NovelResultAdapter;
import cn.njcit.ankeread.utils.HttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, HistoryAdapter.OnHistoryClickListener, HotAdapter.OnHotClickListener {

    /**
     * 搜索作者或者书名
     */
    private EditText mEditSearch;
    /**
     * 取消
     */
    private TextView mTextCancel;
    private ImageView mImgRes;
    private RecyclerView mHotRec;
    private ImageView mImgDelete;
    private RecyclerView mHistoryRec;
    private RelativeLayout mLayTop;
    private RelativeLayout mLayPeople;
    private View mViewLine;
    private RelativeLayout mLayHistory;

    private List<Integer> nums = new ArrayList<>();
    private List<NovelsResult.ListBean> results = new ArrayList<>();
    private List<HotWord.BooksBean> words = new ArrayList<>();
    private RecyclerView mResultRec;
    private RelativeLayout mLayResult;
    private LinearLayout mLayLoad;
    /**
     * 暂无数据
     */
    private TextView mTextEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mEditSearch = (EditText) findViewById(R.id.edit_search);
        mTextCancel = (TextView) findViewById(R.id.text_cancel);
        mImgRes = (ImageView) findViewById(R.id.img_res);
        mHotRec = (RecyclerView) findViewById(R.id.hotRec);
        mImgDelete = (ImageView) findViewById(R.id.img_delete);
        mHistoryRec = (RecyclerView) findViewById(R.id.historyRec);
        mTextCancel.setOnClickListener(this);
        mImgRes.setOnClickListener(this);
        mImgDelete.setOnClickListener(this);
        mLayTop = (RelativeLayout) findViewById(R.id.lay_top);
        mLayPeople = (RelativeLayout) findViewById(R.id.lay_people);
        mViewLine = (View) findViewById(R.id.view_line);
        mLayHistory = (RelativeLayout) findViewById(R.id.lay_history);
        mResultRec = (RecyclerView) findViewById(R.id.resultRec);
        mLayResult = (RelativeLayout) findViewById(R.id.lay_result);
        mLayLoad = (LinearLayout) findViewById(R.id.lay_load);
        mTextEmpty = (TextView) findViewById(R.id.text_empty);
    }

    private void initData() {
        initHot();
        initHistory();
        Intent intent = getIntent();
        mEditSearch.setText(intent.getStringExtra("Name"));
        if (TextUtils.isEmpty(mEditSearch.getText().toString())){

        }else {
            toSearch(mEditSearch.getText().toString());
        }
    }

    private void initListener() {
        mEditSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                    toSearch(mEditSearch.getText().toString());
                }
                return false;
            }
        });
        HistoryAdapter.setmListener(this);
        HotAdapter.setmListener(this);
    }

    private void initHot() {
        nums.add(R.drawable.bg_hot1);
        nums.add(R.drawable.bg_hot2);
        nums.add(R.drawable.bg_hot3);
        nums.add(R.drawable.bg_hot4);
        nums.add(R.drawable.bg_hot5);
        nums.add(R.drawable.bg_hot6);
        HttpUtils.httpGet(Link.HotWord, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SearchActivity.this, "访问失败"+e, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();
                final Gson gson = new Gson();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()){
                            words.clear();
                            HotWord hotWord = gson.fromJson(string,HotWord.class);
                            words.addAll(hotWord.getBooks());
                            handler.sendEmptyMessage(1);
                        }else {

                        }
                    }
                });
            }
        });
    }

    //换一批
    private void getMore() {
        List<String> names = new ArrayList<>();
        for (int i=0;i<6;i++){
            names.add(words.remove(new Random().nextInt(words.size())).getTitle());
        }
        mHotRec.setLayoutManager(new GridLayoutManager(this,2));
        mHotRec.setAdapter(new HotAdapter(this,nums,names));
    }

    //搜索

    private void toSearch(String name) {
        if (!TextUtils.isEmpty(mEditSearch.getText().toString())) {
            mLayHistory.setVisibility(View.GONE);
            mHistoryRec.setVisibility(View.GONE);
            mHotRec.setVisibility(View.GONE);
            mLayPeople.setVisibility(View.GONE);
            mViewLine.setVisibility(View.GONE);
            mResultRec.setVisibility(View.GONE);
            initSearchResult(name);
            toSaveSearch();

        } else {
            Toast.makeText(this, "不知道你要搜的是什么哦....", Toast.LENGTH_SHORT).show();
        }

    }

    /*
    初始化搜索结果
     */

    private void initSearchResult(String name) {
        results.clear();
        mLayLoad.setVisibility(View.VISIBLE);
        mTextEmpty.setVisibility(View.GONE);
        HttpUtils.httpGet(Link.Search + name, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SearchActivity.this, "请尝试重新加载...." , Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();
                final Gson gson = new Gson();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            NovelsResult novelsResult = gson.fromJson(string, NovelsResult.class);
                            if (novelsResult.getMessage().equals("成功!")) {
                                results.addAll(novelsResult.getList());
                                mResultRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                mResultRec.setAdapter(new NovelResultAdapter(SearchActivity.this, results));
                                mLayResult.setVisibility(View.VISIBLE);
                                mResultRec.setVisibility(View.VISIBLE);
                                mLayLoad.setVisibility(View.GONE);
                            } else {
                                mLayLoad.setVisibility(View.GONE);
                                mTextEmpty.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(SearchActivity.this, "访问失败,请截图联系管理员..." + string, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //保存搜索历史

    private void toSaveSearch() {
        if (isExits()) {

        } else {
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setBookName(mEditSearch.getText().toString());
            searchHistory.save();
        }
    }

    //历史记录是否存在
    //不二次存储

    private boolean isExits() {
        List<SearchHistory> lists = LitePal.findAll(SearchHistory.class);
        for (SearchHistory searchHistory : lists) {
            if (searchHistory.getBookName().equals(mEditSearch.getText().toString())) {
                return true;
            }
        }
        return false;
    }

    private void initHistory() {
        List<SearchHistory> histories = LitePal.findAll(SearchHistory.class);
        Collections.reverse(histories);
        mHistoryRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mHistoryRec.setAdapter(new HistoryAdapter(this, histories));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.text_cancel:
                finish();
                break;
            case R.id.img_res:
                initHot();
                break;
            case R.id.img_delete:
                LitePal.deleteAll(SearchHistory.class);
                initHistory();
                Toast.makeText(this, "清除成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //处理请求
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    getMore();
                    break;
            }
        }
    };

    @Override
    public void HistoryClick(String bookName) {
        mEditSearch.setText(bookName);
        toSearch(mEditSearch.getText().toString());
    }

    @Override
    public void HotClick(String bookName) {
        mEditSearch.setText(bookName);
        toSearch(mEditSearch.getText().toString());
    }
}
