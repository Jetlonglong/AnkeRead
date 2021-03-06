package cn.njcit.ankeread.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.njcit.ankeread.bean.SortIcon;
import cn.njcit.ankeread.R;
import cn.njcit.ankeread.http.Link;
import cn.njcit.ankeread.ui.adapter.SortAdapter;
import cn.njcit.ankeread.utils.HttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Create by ankele
 * <p>
 * 2020/1/29 - 19:44
 */
public class SortmanFragment extends Fragment {

    private RecyclerView mSortManRecy;
    private List<SortIcon.ResultBean.ManBean> mansorts = new ArrayList<>();
    private List<String> sorts = new ArrayList<>();
    private List<String> icons = new ArrayList<>();
    private ProgressBar mManPro;
    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sortman, container, false);
        initView(view);
        initIcon();
        return view;
    }

    private void initView(View view) {
        mSortManRecy = (RecyclerView) view.findViewById(R.id.sortMan_recy);
        mManPro = (ProgressBar) view.findViewById(R.id.man_pro);
        mSortManRecy.setVisibility(View.GONE);
        mManPro.setVisibility(View.VISIBLE);
    }

    private void initData() {
        mSortManRecy.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mSortManRecy.setAdapter(new SortAdapter(getContext(), sorts, icons,1));
    }

    private void initIcon() {
        mansorts.clear();
        sorts.clear();
        icons.clear();
        HttpUtils.httpGet(Link.Icon, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "获取失败，请截图联系客服....", Toast.LENGTH_SHORT).show();
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
                            SortIcon sortIcon = gson.fromJson(string, SortIcon.class);
                            mansorts.addAll(sortIcon.getResult().getMan());
                            for (SortIcon.ResultBean.ManBean data : mansorts) {
                                sorts.add(data.getTitle());
                            }
                            for (SortIcon.ResultBean.ManBean data : mansorts) {
                                icons.add(data.getCover());
                            }
                            mSortManRecy.setVisibility(View.VISIBLE);
                            mManPro.setVisibility(View.GONE);
                            handler.sendEmptyMessage(1);
                        }
                    }
                });
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    initData();
                    break;
            }
        }
    };

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }
}
