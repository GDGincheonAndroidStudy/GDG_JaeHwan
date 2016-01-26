package gdg.incheon.gdg_jaehwan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gdg.incheon.gdg_jaehwan.ui.SearchFragment;
import gdg.incheon.gdg_jaehwan.ui.StoreFragment;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    public ImagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment resultFragment = null;

        switch (position) {
            case 0:
                resultFragment = SearchFragment.newInstance();
                break;
            case 1:
                resultFragment = StoreFragment.newInstance();
                break;
        }
        return resultFragment;
    }



    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab " + position;
    }

}
