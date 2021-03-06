package com.example.stockcryptoalerter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ChipNavigationBar bottomNav;
    FragmentManager fragmentmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//

        //bottom nav
        bottomNav = findViewById(R.id.bottom_nav);

        //Show home fragment when app is first opened
        if (savedInstanceState == null) {
            bottomNav.setItemSelected(R.id.home, true);
            fragmentmanager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();

            fragmentmanager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment, "home_fragment")
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.home:
                        fragmentmanager = getSupportFragmentManager();
                        fragment = new HomeFragment();
                        fragmentmanager.beginTransaction()
                                .replace(R.id.fragment_container, fragment, "home_fragment")
                                .commit();
                        break;
                    case R.id.alerts:
                        fragment = new AlertsFragment();
                        break;
                    case R.id.saved:
                        fragment = new SavedFragment();
                        break;
                    case R.id.news:
                        fragment = new NewsFragment();
                        break;
                }
                if (fragment != null){
                    fragmentmanager = getSupportFragmentManager();
                    fragmentmanager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }
                else {
                    Log.e(TAG, "Error in creating fragment");
                }
            }
        });


    }
}