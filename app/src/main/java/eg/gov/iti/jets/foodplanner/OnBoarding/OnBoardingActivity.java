package eg.gov.iti.jets.foodplanner.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.authentication.view.LoginActivity;

public class OnBoardingActivity extends AppCompatActivity {

    private static final String TAG = "OnBoardingActivity";

    private ViewPager screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabLayout;
    private Button btnNext;
    private int position = 0;
    private Button btnGetStarted;
    private Animation btnAnim;
    private TextView tvSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        getSupportActionBar().hide();

        btnNext = findViewById(R.id.intro_btn_next);
        btnGetStarted = findViewById(R.id.intro_btn_get_started);
        tabLayout = findViewById(R.id.intro_tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        tvSkip = findViewById(R.id.intro_tv_skip);

        // fill list screen
        final List<ScreenBoardingItem> mList = new ArrayList<>();
        mList.add(new ScreenBoardingItem("Mlab Application", "Has a lot of helpful features ,and provide easy ways for both Doctors and Patients.", R.drawable.food1));
        mList.add(new ScreenBoardingItem("Communication", "It allows permanent communication with doctors, as it allows the possibility of chatting and medical consultations.", R.drawable.food4));
        mList.add(new ScreenBoardingItem("Home Visit", "It allows home visits to obtain medical analyzes as well as to deliver results in order to serve users and make it easier for patients.", R.drawable.food1));

        // setup viewpager
        screenPager = findViewById(R.id.intro_screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);
        tabLayout.setupWithViewPager(screenPager);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position == mList.size() - 1) { // when we rech to the last screen

                    loadLastScreen();
                }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(mainActivity);
                finish();
            }
        });

        // skip button click listener
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
            }
        });


    }


    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
        btnGetStarted.setAnimation(btnAnim);
    }
}