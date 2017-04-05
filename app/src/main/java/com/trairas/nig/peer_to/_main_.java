package com.trairas.nig.peer_to;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.trairas.nig.peer_to.Fragmentos.AdicionarPalavra;
import com.trairas.nig.peer_to.Fragmentos.MinhasPavras;
import com.trairas.nig.peer_to.Fragmentos.PeerToPeer;
import com.trairas.nig.peer_to.Fragmentos.PesquisarPalavra;
import com.trairas.nig.peer_to.Util.*;


public class _main_ extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Util ut = new Util();

    FragmentManager frag = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null){
           MinhasPavras mp = new MinhasPavras();

            FragmentTransaction ft = frag.beginTransaction();
            ft.add(R.id.conteudo, mp);
            ft.commit();

        }


    }

    @Override
    public void onBackPressed() {

        ut.print("Botao voltar");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu._main_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_camera) {
            frag.beginTransaction().replace(R.id.conteudo, new MinhasPavras()).commit();
        } else if (id == R.id.nav_gallery) {
            frag.beginTransaction().replace(R.id.conteudo, new AdicionarPalavra()).commit();
        } else if (id == R.id.nav_slideshow) {
            frag.beginTransaction().replace(R.id.conteudo, new PesquisarPalavra()).commit();
        } else if (id == R.id.nav_manage) {
            frag.beginTransaction().replace(R.id.conteudo, new PeerToPeer()).commit();
        }

        /* else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
