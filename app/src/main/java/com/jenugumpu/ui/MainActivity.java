package com.jenugumpu.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jenugumpu.R;
import com.jenugumpu.ui.collective.CollectiveFragment;
import com.jenugumpu.ui.grading.GradingFragment;
import com.jenugumpu.ui.harvest.HarvestFragment;
import com.jenugumpu.ui.price.PriceFragment;
import com.jenugumpu.ui.tracker.TrackerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bottom_nav);

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(new HarvestFragment());
        }

        nav.setOnItemSelectedListener(item -> {
            Fragment f;
            int id = item.getItemId();
            if      (id == R.id.nav_harvest)    f = new HarvestFragment();
            else if (id == R.id.nav_grading)    f = new GradingFragment();
            else if (id == R.id.nav_price)      f = new PriceFragment();
            else if (id == R.id.nav_collective) f = new CollectiveFragment();
            else                                f = new TrackerFragment();
            loadFragment(f);
            return true;
        });
    }

    private void loadFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, f)
                .commit();
    }
}
