package com.example.grocersapp.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.grocersapp.Controller.WalkthroughAdapter;
import com.example.grocersapp.Model.WalkthroughModel;
import com.example.grocersapp.R;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WalkthroughActivity extends AppCompatActivity {

    private ViewPager screenPager;
    WalkthroughAdapter walkthroughAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;
    TextView tvSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on full screen

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_walkthrough);




        // when this activity is about to be launch we need to check if its openened before or not

        if (restorePrefData()) {

            Log.d("walkthrough", "in here ");
            Intent mainActivity = new Intent(getApplicationContext(), Login.class );
            startActivity(mainActivity);
            finish();
        }

        //int views
        tabIndicator = findViewById(R.id.tab_indicator);
        btnGetStarted= findViewById((R.id.btn_getstarted));
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);


        //:todo Add your walkthrough images here
        final List<WalkthroughModel> mList = new ArrayList<>();
        mList.add(new WalkthroughModel("Fresh Food","Amazing Deals with the amazing you" , R.drawable.basket));
        mList.add(new WalkthroughModel("Fast Delivery", "Sign up with a mobile app and shop the products to purchase", R.drawable.delivery));
        mList.add(new WalkthroughModel("Easy Payment", "Enjoy assured lowest prices at our store", R.drawable.payment));

        // setup viewpager
        screenPager = findViewById(R.id.screen_viewpager);
        walkthroughAdapter = new WalkthroughAdapter(this, mList);
        screenPager.setAdapter(walkthroughAdapter);

       tabIndicator.setupWithViewPager(screenPager);


        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1) {

                    loaddLastScreen();

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        // Get Started button click listener

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //open main activity

                Intent mainActivity = new Intent(getApplicationContext(),Login.class);
                startActivity(mainActivity);
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process
                savePrefsData();
                finish();

            }
        });




/*
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);


                }
                if (position == mList.size()-1) { // when we rech to the last screen


                    loaddLastScreen();


                }




            }
        });*/


    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

       // btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
        // setup animation

        btnGetStarted.setAnimation(btnAnim);




    }

    private boolean restorePrefData() {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;



    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();


    }
}


