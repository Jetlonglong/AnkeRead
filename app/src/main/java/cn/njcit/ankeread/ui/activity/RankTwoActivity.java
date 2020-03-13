package cn.njcit.ankeread.ui.activity;

import android.content.Intent;
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
import cn.njcit.ankeread.ui.fragment.MonthFragment;
import cn.njcit.ankeread.ui.fragment.TotalFragment;
import cn.njcit.ankeread.ui.fragment.WeekFragment;

public class RankTwoActivity extends AppCompatActivity {

    private TextView mMTitle;
    private Toolbar mToolbar;
    private TabLayout mRankTwoTabLayout;
    private ViewPager mRankTwoViewPage;
    private List<String> mTitle = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();
    private int index =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_two);
        initView();
        initListener();
    }

    private void initView() {
        Intent intent = getIntent();
        index = intent.getIntExtra("index",1);
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRankTwoTabLayout = (TabLayout) findViewById(R.id.rankTwo_tabLayout);
        mRankTwoViewPage = (ViewPager) findViewById(R.id.rankTwo_viewPage);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mToolbar.setTitle("");
        mMTitle.setText(intent.getStringExtra("name"));
        mTitle = Arrays.asList(getResources().getStringArray(R.array.tab_name3));
        mFragment.add(new WeekFragment(index));
        mFragment.add(new MonthFragment(index));
        mFragment.add(new TotalFragment(index));
    }

    private void initData(){

    }

    private void initListener(){
        mRankTwoViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        mRankTwoViewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        mRankTwoTabLayout.setupWithViewPager(mRankTwoViewPage);
        mRankTwoTabLayout.setTabTextColors(Color.parseColor("#AEAEAF"),Color.parseColor("#5DCAFF"));
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
