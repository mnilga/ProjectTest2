package marianilga.projecttest2;

import java.util.ArrayList;
import java.util.Locale;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {


    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0984CA"));
        actionBar.setBackgroundDrawable(colorDrawable);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        int [] icons = {R.drawable.menu_top1_pass, R.drawable.menu_top2_pass, R.drawable.menu_top3_pass};

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setIcon(icons[i])
                            .setTabListener(this));
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.search:
                showDialog("Clicked On", " Search");
                return true;
            case R.id.messages:
                showDialog("Clicked On", " Messages");
                return true;
            case R.id.add_contact:
                showDialog("Clicked On", " Add contact");
                return true;
            case R.id.settings:
                showDialog("Clicked On", " Settings");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        switch (tab.getPosition()) {
            case 0:
                tab.setIcon(R.drawable.menu_top1);
                break;
            case 1:
                tab.setIcon(R.drawable.menu_top2);
                break;
            case 2:
                tab.setIcon(R.drawable.menu_top3);
                break;
        }

        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        switch (tab.getPosition()) {
            case 0:
                tab.setIcon(R.drawable.menu_top1_pass);
                break;
            case 1:
                tab.setIcon(R.drawable.menu_top2_pass);
                break;
            case 2:
                tab.setIcon(R.drawable.menu_top3_pass);
                break;
        }

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment1().newInstance(position + 1);
                case 1:
                    return new Fragment2().newInstance(position + 1);
                case 2:
                    return new Fragment3().newInstance(position + 1);
            }
            return new Fragment1().newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            /*Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }*/
            return null;
        }
    }

    // Create an AlertDialog
    public void showDialog(String title,String message){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(title);
        adb.setMessage(message);
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setPositiveButton("OK", null);
        AlertDialog alert = adb.create();
        alert.show();
    }


}
