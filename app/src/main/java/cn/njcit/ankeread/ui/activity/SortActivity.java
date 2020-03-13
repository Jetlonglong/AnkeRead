package cn.njcit.ankeread.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.njcit.ankeread.R;
import cn.njcit.ankeread.ui.fragment.SortmanFragment;
import cn.njcit.ankeread.ui.fragment.SortpublishFragment;
import cn.njcit.ankeread.ui.fragment.SortwomanFragment;

public class SortActivity extends AppCompatActivity {

    private TextView mMTitle;
    private Toolbar mToolbar;
    private TabLayout mSortTabLayout;
    private ViewPager mSortViewPage;
    private List<String> mTitle = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        initView();
        initListener();
    }

    private void initView() {
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSortTabLayout = (TabLayout) findViewById(R.id.sort_tabLayout);
        mSortViewPage = (ViewPager) findViewById(R.id.sort_viewPage);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mToolbar.setTitle("");
        mMTitle.setText("分类");
        mTitle = Arrays.asList(getResources().getStringArray(R.array.tab_name));
        mFragment.add(new SortmanFragment());
        mFragment.add(new SortwomanFragment());
        mFragment.add(new SortpublishFragment());

    }
    private void initData(){

    }

    private void initListener(){
        mSortViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mSortViewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        mSortTabLayout.setupWithViewPager(mSortViewPage);
        mSortTabLayout.setTabTextColors(Color.parseColor("#AEAEAF"),Color.parseColor("#5DCAFF"));
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
