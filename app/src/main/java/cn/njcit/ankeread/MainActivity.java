package cn.njcit.ankeread;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cn.njcit.ankeread.ui.Fragment.LibraryFragment;
import cn.njcit.ankeread.ui.Fragment.MeFragment;
import cn.njcit.ankeread.ui.Fragment.ShelfFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private BottomNavigationView bottom_navigation;
    private TextView mMTitle;
    private ShelfFragment mShelfFragment;
    private LibraryFragment mLibraryFragment;
    private MeFragment mMeFragment;
    private Button mBtnShelfAdd;

    private long mExitTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        bottom_navigation = findViewById(R.id.nav_bottom);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mToolbar.setTitle("");
        showFragment(1);
    }

    private void initData(){
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        showFragment(1);
                        break;
                    case R.id.navigation_dashboard:
                        showFragment(2);
                        break;
                    case R.id.navigation_notifications:
                        showFragment(3);
                        break;
                }
                return true;
            }
        });
    }
    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index){
            case 1:
                mMTitle.setText("书架");
                if (mShelfFragment==null){
                    mShelfFragment = new ShelfFragment();
                    ft.add(R.id.nav_host_fragment,mShelfFragment,ShelfFragment.class.getName());
                }else {
                    ft.show(mShelfFragment);
                }
                break;
            case 2:
                mMTitle.setText("书库");
                if (mLibraryFragment==null){
                    mLibraryFragment = new LibraryFragment();
                    ft.add(R.id.nav_host_fragment,mLibraryFragment,LibraryFragment.class.getName());
                }else {
                    ft.show(mLibraryFragment);
                }
                break;
            case 3:
                mMTitle.setText("我的");
                if (mMeFragment==null){
                    mMeFragment = new MeFragment();
                    ft.add(R.id.nav_host_fragment,mMeFragment,MeFragment.class.getName());
                }else {
                    ft.show(mMeFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (mShelfFragment != null) {
            ft.hide(mShelfFragment);
        }
        if (mLibraryFragment != null) {
            ft.hide(mLibraryFragment);
        }
        if (mMeFragment!=null){
            ft.hide(mMeFragment);
        }
    }

    /**
     * 点击两次返回退出app
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出APP", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {

    }
}
