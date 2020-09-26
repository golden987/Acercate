package co.com.acercate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import co.com.acercate.ui.assignDate.AssignDateFragment;
import co.com.acercate.ui.assignedDatesList.AssignedDatesFragment;
import co.com.acercate.ui.createDate.CreateDateFragment;
import co.com.acercate.ui.myDates.MyDateItemFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    NavigationView navigationView;
    TextView tvUserName;
    TextView tvRazonSocial;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        setNavigationViewListener();
        SharedPreferences prefs = getSharedPreferences("shared_login_data", Context.MODE_PRIVATE);
        String cod = prefs.getString("cod", "");
        String usuario = prefs.getString("usuario", "");
        String razon_social = prefs.getString("razon_social", "");

        tvUserName = navigationView.getHeaderView(0).findViewById(R.id.tvUserName);
        tvUserName.setText(usuario);
        tvRazonSocial = navigationView.getHeaderView(0).findViewById(R.id.tvRazonSocial);
        tvRazonSocial.setText(razon_social + " - " + cod);

        displayView(R.id.nav_create_date);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            getBaseContext().getSharedPreferences("shared_login_data", 0).edit().clear().apply();
            finish();
            Intent login = new Intent(MainActivity.this, Login.class);
            startActivity(login);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (viewId) {
            case R.id.nav_create_date:
                fragment = new CreateDateFragment();
                title = getString(R.string.create_date);
                break;
            case R.id.nav_assing_date:
                fragment = new AssignDateFragment();
                title = getString(R.string.assign_date);
                break;
            case R.id.nav_my_dates:
                fragment = new MyDateItemFragment();
                title = getString(R.string.my_dates);
                break;
            case R.id.nav_assigned_dates:
                fragment = new AssignedDatesFragment();
                title = getString(R.string.assigned_date);
                break;
            case R.id.nav_close:
                finish();
                System.exit(0);
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displayView(item.getItemId());
        return true;
    }
}