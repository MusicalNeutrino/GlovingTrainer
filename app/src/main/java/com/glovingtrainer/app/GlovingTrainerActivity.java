package com.glovingtrainer.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class GlovingTrainerActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks
{
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence             mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_moves);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
            getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.container, getFragmentForPosition(position))
            .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if (!mNavigationDrawerFragment.isDrawerOpen())
        {
            getMenuInflater().inflate(R.menu.random_moves, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    private void restoreActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    private Fragment getFragmentForPosition(int position)
    {
        switch(position)
        {
        case 0:
            return new MovesGeneratorFragment();
        case 1:
        default:
            return new ListMovesFragment();
        }
    }

    public void onSectionAttached(int number)
    {
        switch (number)
        {
        case 0:
            mTitle = getString(R.string.title_moves_generator);
            break;
        case 1:
            mTitle = getString(R.string.title_list_moves);
            break;
        case 2:
            mTitle = getString(R.string.title_section3);
            break;
        }
    }
}
