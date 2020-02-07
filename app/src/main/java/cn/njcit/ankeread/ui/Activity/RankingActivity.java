package cn.njcit.ankeread.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import cn.njcit.ankeread.R;

public class RankingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mMTitle;
    private Toolbar mToolbar;
    private CardView mViewHot;
    private CardView mViewHotSearch;
    private CardView mViewPotential;
    private CardView mViewRetain;
    private CardView mViewEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        initView();
    }

    private void initView() {
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mToolbar.setTitle("");
        mMTitle.setText("排行榜");
        mViewHot = (CardView) findViewById(R.id.viewHot);
        mViewHot.setOnClickListener(this);
        mViewHotSearch = (CardView) findViewById(R.id.viewHotSearch);
        mViewHotSearch.setOnClickListener(this);
        mViewPotential = (CardView) findViewById(R.id.viewPotential);
        mViewPotential.setOnClickListener(this);
        mViewRetain = (CardView) findViewById(R.id.viewRetain);
        mViewRetain.setOnClickListener(this);
        mViewEnd = (CardView) findViewById(R.id.viewEnd);
        mViewEnd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.viewHot:
                Intent intent = new Intent(this,RankTwoActivity.class);
                intent.putExtra("name","最热榜");
                intent.putExtra("index",0);
                startActivity(intent);
                break;
            case R.id.viewHotSearch:
                Intent intent2 = new Intent(this,RankTwoActivity.class);
                intent2.putExtra("name","热搜榜");
                intent2.putExtra("index",1);
                startActivity(intent2);
                break;
            case R.id.viewPotential:
                Intent intent3 = new Intent(this,RankTwoActivity.class);
                intent3.putExtra("name","潜力榜");
                intent3.putExtra("index",2);
                startActivity(intent3);
                break;
            case R.id.viewRetain:
                Intent intent4 = new Intent(this,RankTwoActivity.class);
                intent4.putExtra("name","留存榜");
                intent4.putExtra("index",3);
                startActivity(intent4);
                break;
            case R.id.viewEnd:
                Intent intent5 = new Intent(this,RankTwoActivity.class);
                intent5.putExtra("name","完结榜");
                intent5.putExtra("index",4);
                startActivity(intent5);
                break;
        }
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
