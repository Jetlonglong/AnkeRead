package cn.njcit.ankeread.ui.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.njcit.ankeread.R;
import cn.njcit.ankeread.ui.Fragment.EndFragment;
import cn.njcit.ankeread.ui.Fragment.HotFragment;
import cn.njcit.ankeread.ui.Fragment.RetainFragment;
import cn.njcit.ankeread.ui.Fragment.SerFragment;
import cn.njcit.ankeread.ui.Fragment.SortmanFragment;
import cn.njcit.ankeread.ui.Fragment.SortpublishFragment;
import cn.njcit.ankeread.ui.Fragment.SortwomanFragment;

public class SortBooksActivity extends AppCompatActivity {

    private TextView mMTitle;
    private Toolbar mToolbar;
    private TabLayout mSortBooksTabLayout;
    private ViewPager mSortBooksViewPage;
    private List<String> mTitle = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();
    private String index;
    private String sort;
    private HotFragment hotFragment;
    private RetainFragment retainFragment;
    private SerFragment serFragment;
    private EndFragment endFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_books);
        initView();
        initListener();
    }

    private void initView() {
        Intent intent = getIntent();
        index = intent.getStringExtra("index");
        sort = intent.getStringExtra("sort");
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSortBooksTabLayout = (TabLayout) findViewById(R.id.sortBooks_tabLayout);
        mSortBooksViewPage = (ViewPager) findViewById(R.id.sortBooks_viewPage);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mToolbar.setTitle("");
        mMTitle.setText(sort);
        mTitle = Arrays.asList(getResources().getStringArray(R.array.tab_name2));
        mFragment.add(new HotFragment(index,sort));
        mFragment.add(new RetainFragment(index,sort));
        mFragment.add(new SerFragment(index,sort));
        mFragment.add(new EndFragment(index,sort));
    }
    private void iniData(){

    }
    private void initListener(){
        mSortBooksViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        mSortBooksViewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        mSortBooksTabLayout.setupWithViewPager(mSortBooksViewPage);
        mSortBooksTabLayout.setTabTextColors(Color.parseColor("#AEAEAF"),Color.parseColor("#5DCAFF"));
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
