package grupo_go_ra_ri.dam.isi.frsf.codeforces;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.Contest;

public class MenuSlideActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , MenuListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_slide);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.escenario, new inicio()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();

        if (id == R.id.inicio) {
            fm.beginTransaction().replace(R.id.escenario, new inicio()).commit();
        } else if (id == R.id.profile) {
            fm.beginTransaction().replace(R.id.escenario, new Profile()).commit();
        } else if (id == R.id.competencias) {
            fm.beginTransaction().replace(R.id.escenario, new ContestList()).commit();
        }
//        else if (id == R.id.usuarios) {
//            fm.beginTransaction().replace(R.id.escenario, new users_list()).commit();
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onShowUserData(String json) {
        FragmentManager fm = getSupportFragmentManager();
        Profile pfrg = new Profile();
        Bundle bundle = new Bundle();
        bundle.putString("json",json);
        pfrg.setArguments(bundle);
        fm.beginTransaction().replace(R.id.escenario,pfrg).commit();
    }
}
