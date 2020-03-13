package cn.njcit.ankeread.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.njcit.ankeread.R;
import cn.njcit.ankeread.ui.activity.SearchActivity;
import cn.njcit.ankeread.ui.activity.SortActivity;

public class ShelfFragment extends Fragment implements View.OnClickListener {

    private LinearLayout mLayCenter;
    private Button mBtnShelfAdd;
    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shelf, container, false);
        setHasOptionsMenu(true);
        initView(root);
        return root;
    }

    private void initView(View root) {
        mLayCenter = (LinearLayout) root.findViewById(R.id.lay_center);
        mBtnShelfAdd = (Button) root.findViewById(R.id.btn_shelf_add);
        mBtnShelfAdd.setOnClickListener(this);
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_shelf_add:
                startActivity(new Intent(  mActivity, SortActivity.class));
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

}