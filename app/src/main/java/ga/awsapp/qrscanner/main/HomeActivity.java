package ga.awsapp.qrscanner.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.create.CreateFragment;
import ga.awsapp.qrscanner.details.DetailsActivity;
import ga.awsapp.qrscanner.history.HistoryFragment;
import ga.awsapp.qrscanner.setting.SettingsActivity;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final long NAVDRAWER_LAUNCH_DELAY = 250;
    private Handler mHandler;
    private Fragment fragment;
    public static final String QR_STRING =  "QR_STRING";
    public static final String QR_TYPE = "QR_TYPE";
    public static final String ADD_TO_HISTORY = "ADD_TO_HISTORY";
    public static final String ADD_TO_CLIP = "ADD_TO_CLIP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Scan");
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerSlideAnimationEnabled(false);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
            CreateFragment.newInstance()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
         fragment = null;

        switch (item.getItemId())
        {
            case R.id.nav_home:
                getSupportActionBar().setTitle(R.string.scan_fragment_tittle);
                fragment = CreateFragment.newInstance();
                break;

            case R.id.nav_history:
                getSupportActionBar().setTitle(R.string.history_fragment_tittle);
                fragment = HistoryFragment.newInstance();
                break;

            case R.id.nav_setting:
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));break;

        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (fragment != null)
                { FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                }
            }
        }, NAVDRAWER_LAUNCH_DELAY);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START, true);
        return true;
    }


}
