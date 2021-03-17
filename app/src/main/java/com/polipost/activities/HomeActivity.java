package com.polipost.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.polipost.R;
import com.polipost.Utilities.ApiClient;
import com.polipost.Utilities.ApiInterface;
import com.polipost.Utilities.MyPreferences;
import com.polipost.adapter.AdapterTemplate;
import com.polipost.adapter.AdapterCategory;
import com.polipost.models.FilterModel;
import com.polipost.models.FilterData;
import com.polipost.models.TemplatesData;
import com.polipost.models.TemplatesModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    DrawerLayout myDrawer;
    Toolbar myHomeToolBar;
    RecyclerView recyclerViewTemplete;
    LinearLayoutManager linearLayoutManagerFilter;
    List<FilterData> filterDataList;
    List<TemplatesData> templatesDataList;
    private ConstraintLayout proBn, packegeBn, settingsBn, notificationBn, myAccBn, watchTutorialBn, ratiingBn, shareAppBn;
    ImageView fbBn, instaBn, tweeterBn;
    private MyPreferences myPreferences;
    private TextView nameTx, emailTx;
    private ViewPager2 viewPager2;
    int customPosition = 0;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

//         CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
//        compositePageTransformer.addTransformer(new MarginPageTransformer(20));
//        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                float r = 1 - Math.abs(position);
//                page.setScaleY(0.08f + r + 0.15f);

        setUi();
        setValue();
        setNavigationItems();
        getFilter();
        getTempates();


    }

    private void setNavigationItems() {
        proBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PackageActivity.class));
            }
        });

        packegeBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PackageActivity.class));
            }
        });

        settingsBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
            }
        });

        notificationBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });

        myAccBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyAccountActivity.class));
            }
        });

        watchTutorialBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Development under process", Toast.LENGTH_SHORT).show();
            }
        });

        ratiingBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Development under process", Toast.LENGTH_SHORT).show();
            }
        });

        shareAppBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Development under process", Toast.LENGTH_SHORT).show();
            }
        });

        fbBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Development under process", Toast.LENGTH_SHORT).show();
            }
        });

        instaBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Development under process", Toast.LENGTH_SHORT).show();
            }
        });

        tweeterBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Development under process", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setValue() {
        nameTx.setText(myPreferences.readUserName());
        emailTx.setText(myPreferences.readUserEmail());

        linearLayoutManagerFilter = new LinearLayoutManager(getApplicationContext());
        linearLayoutManagerFilter.setOrientation(LinearLayoutManager.HORIZONTAL);
        //recyclerViewFileter.setLayoutManager(linearLayoutManagerFilter);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerViewTemplete.setLayoutManager(staggeredGridLayoutManager);

        // click listener for navigation icon of toolbar
        myHomeToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDrawer.openDrawer(GravityCompat.START);
            }
        });



        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                sliderHandler.removeCallbacks(sliderRunnable);
////                sliderHandler.postDelayed(sliderRunnable,3000);//slide duration

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                customPosition = position;
                //prePareDots(customPosition++);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

//    private void prePareDots(int i) {
//        if (linearLayout.getChildCount() > 0)
//            linearLayout.removeAllViews();
//
//        ImageView[] dots = new ImageView[sliderDataList.size()];
//
//        for (int i = 0; i < sliderDataList.size(); i++) {
//
//            dots[i] = new ImageView(getActivity());
//            if (i == currentPosition)
//                dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dots));
//
//            else
//                dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.deactive_dots));
//
//
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//
//            layoutParams.setMargins(4, 0, 4, 0);
//            linearLayout.addView(dots[i], layoutParams);
//
//        }
//    }

    private void setUi() {
        myHomeToolBar = findViewById(R.id.toolbar);
        //  recyclerViewFileter = findViewById(R.id.recycler_filter);
        recyclerViewTemplete = findViewById(R.id.recycler_temp);
        myDrawer = findViewById(R.id.drawerLayout);
        myPreferences = new MyPreferences(this);
        myHomeToolBar.setNavigationIcon(R.drawable.nav);
        myHomeToolBar.setTitleTextColor(getResources().getColor(R.color.white_color));
        myHomeToolBar.setTitleTextAppearance(this, R.style.myFont);

        //proBn, packegeBn, settingsBn, notificationBn, myAccBn, watchTutorialBn, ratiingBn, shareAppBn, fbBn, instaBn, tweeterBn;

        viewPager2 = findViewById(R.id.viewpager);
        proBn = findViewById(R.id.pcons);
        packegeBn = findViewById(R.id.opcons);
        settingsBn = findViewById(R.id.scons);
        notificationBn = findViewById(R.id.ncons);
        myAccBn = findViewById(R.id.ppcons);
        watchTutorialBn = findViewById(R.id.vcons);
        ratiingBn = findViewById(R.id.rcons);
        shareAppBn = findViewById(R.id.shcons);
        fbBn = findViewById(R.id.fb_id);
        instaBn = findViewById(R.id.insta_id);
        tweeterBn = findViewById(R.id.tw_id);
        nameTx = findViewById(R.id.name);
        emailTx = findViewById(R.id.email);

    }

    private void getTempates() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<TemplatesModel> call = apiInterface.getTemplats();
        call.enqueue(new Callback<TemplatesModel>() {
            @Override
            public void onResponse(Call<TemplatesModel> call, Response<TemplatesModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("ok")) {
//                        Toast.makeText(HomeActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                        templatesDataList = response.body().getData();
                        // Toast.makeText(HomeActivity.this, "Datas "+templatesDataList, Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onResponsetemplist: " + templatesDataList);
                        String jsonImge = String.valueOf(response.body().getData().get(0).getJson_img());
                        sendJsonData(jsonImge);

                        AdapterTemplate adapterTemplate = new AdapterTemplate(templatesDataList, getApplicationContext());
                        // recyclerViewTemplete.setAdapter(adapterTemplate);
                        adapterTemplate.notifyDataSetChanged();

                    } else {
                        Toast.makeText(HomeActivity.this, "No templates", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TemplatesModel> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailureerr: " + t.getMessage());
            }
        });
    }

    private void sendJsonData(String jsonImge) {

    }

    private void getFilter() {
        Toast.makeText(this, "inside method", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onBindViewHolderfiltername: insidemethod");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<FilterModel> filterCall = apiInterface.getFilters();
        filterCall.enqueue(new Callback<FilterModel>() {
            @Override
            public void onResponse(Call<FilterModel> call, Response<FilterModel> response) {
                //  Toast.makeText(HomeActivity.this, "code "+response.code(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onBindViewHolderfiltername: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("ok")) {

                        Toast.makeText(HomeActivity.this, "code " + response.code(), Toast.LENGTH_SHORT).show();
                        filterDataList = response.body().getData();
                        Log.d(TAG, "datafilter " + filterDataList);
                        Toast.makeText(HomeActivity.this, "data: " + filterDataList, Toast.LENGTH_SHORT).show();
                        AdapterCategory adpaterFilter = new AdapterCategory(filterDataList, getApplicationContext());
                        //recyclerViewFileter.setAdapter(adpaterFilter);
                        viewPager2.setAdapter(adpaterFilter);
                        adpaterFilter.notifyDataSetChanged();
                        //createSlider();

                    } else {
                        Toast.makeText(HomeActivity.this, "Unable to fetch data", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<FilterModel> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Server down, please try after sometime", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onBindViewHolderfiltername: errr " + t.getMessage());
            }
        });
    }

    private void createSlider() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int currentPosition = viewPager2.getCurrentItem();
                if (currentPosition == filterDataList.size() - 1) {
                    currentPosition = -1;
                }
                viewPager2.setCurrentItem(++currentPosition, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 3000, 3000);
    }
}
