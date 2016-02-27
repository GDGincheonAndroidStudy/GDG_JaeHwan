package gdg.incheon.gdg_jaehwan.ui

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import gdg.incheon.gdg_jaehwan.R
import gdg.incheon.gdg_jaehwan.adapter.ImagePagerAdapter

/**
 * Created by 01071724654 on 2016-02-27.
 */


open class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)



        var tabLayout = findViewById(R.id.tabs) as TabLayout
        var viewPager = findViewById(R.id.viewpager) as ViewPager
        var adapter = ImagePagerAdapter(getSupportFragmentManager())
        viewPager.setAdapter(adapter)

        tabLayout.setupWithViewPager(viewPager)
    }
}
