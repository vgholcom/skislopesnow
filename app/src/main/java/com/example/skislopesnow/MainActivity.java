package com.example.skislopesnow;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import com.example.skislopesnow.utils.CommonUtils;
import com.example.skislopesnow.utils.DividerItemDecoration;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ResortAdapter.Callback {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    ResortAdapter mResortAdapter;

    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUp();
    }

    private void setUp() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_drawable);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        mResortAdapter = new ResortAdapter(new ArrayList<>());

        prepareDemoContent();
    }

    private void prepareDemoContent() {
        CommonUtils.showLoading(MainActivity.this);
        new Handler().postDelayed(() -> {
            //prepare data and show loading
            CommonUtils.hideLoading();
            ArrayList<Resort> mResorts = new ArrayList<>();
            String[] resortsList = getResources().getStringArray(R.array.resorts_titles);
            String[] resortsInfo = getResources().getStringArray(R.array.resorts_info);
            String[] resortsImage = getResources().getStringArray(R.array.resorts_images);
            for (int i = 0; i < resortsList.length; i++) {
                mResorts.add(new Resort(resortsImage[i], resortsInfo[i], "News", resortsList[i]));
            }
            mResortAdapter.addItems(mResorts);
            mRecyclerView.setAdapter(mResortAdapter);
        }, 2000);


    }

    @Override
    public void onEmptyViewRetryClick() {
        prepareDemoContent();
    }
}
