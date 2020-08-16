package com.artimanton.fluxstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TextView;

import com.artimanton.fluxstore.fragments.PageFragment1;
import com.artimanton.fluxstore.fragments.PageFragment2;
import com.artimanton.fluxstore.fragments.PageFragment3;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeStatusBarColor();
        initFragments();

        textView = (TextView) findViewById(R.id.skip);



    }

    private void initFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragment1());
        list.add(new PageFragment2());
        list.add(new PageFragment3());

        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePageAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);

    }

    private void changeStatusBarColor() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.white));
        }
    }
}