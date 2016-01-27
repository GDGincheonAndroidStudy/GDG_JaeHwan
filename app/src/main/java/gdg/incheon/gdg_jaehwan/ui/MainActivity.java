package gdg.incheon.gdg_jaehwan.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import gdg.incheon.gdg_jaehwan.R;
import gdg.incheon.gdg_jaehwan.adapter.TabsAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    TabsAdapter mAdapter;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost)findViewById(R.id.tabHost);
        pager = (ViewPager)findViewById(R.id.pager);
        tabHost.setup();

        mAdapter = new TabsAdapter(this, getSupportFragmentManager(), tabHost, pager);

        mAdapter.addTab(tabHost.newTabSpec("tab1").setIndicator("TAB1"), SearchFragment.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab2").setIndicator("TAB2"), StoreFragment.class, null);
    }
}
