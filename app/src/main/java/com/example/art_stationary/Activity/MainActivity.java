package com.example.art_stationary.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.art_stationary.Fragments.CartFragment;
import com.example.art_stationary.Fragments.CategoriesFragment;
import com.example.art_stationary.Fragments.HomeFragment;
import com.example.art_stationary.Fragments.ProfileFragment;
import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;
    private long pressedTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gloabal_View.statusbar(MainActivity.this);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.home);

    }


    HomeFragment firstFragment = new HomeFragment();
    CartFragment secondFragment = new CartFragment();
    CategoriesFragment thirdFragment = new CategoriesFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.person:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
                return true;

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, firstFragment).commit();
                return true;

            case R.id.category:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, thirdFragment).commit();
                return true;

            case R.id.cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, secondFragment).commit();
                return true;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flFragment);
        if (fragment == firstFragment){
            if (pressedTime + 2000 > System.currentTimeMillis()){
                super.onBackPressed();;
                finish();

            }else{
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            }
            pressedTime = System.currentTimeMillis();
        }else if (fragment == secondFragment || fragment == thirdFragment || fragment == profileFragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, firstFragment).commit();
            bottomNavigationView.setSelectedItemId(R.id.home);
        }else{
            super.onBackPressed();
        }


    }
}