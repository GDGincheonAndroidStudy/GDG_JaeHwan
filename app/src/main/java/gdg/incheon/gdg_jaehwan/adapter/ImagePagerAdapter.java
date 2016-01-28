package gdg.incheon.gdg_jaehwan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gdg.incheon.gdg_jaehwan.ui.RealmSearchFragment;
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
            case 2:
                resultFragment = RealmSearchFragment.newInstance();
                break;
        }
        return resultFragment;
    }



    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position) {
            case 0:
                title="이미지검색";
                break;
            case 1:
                title="저장데이터";
                break;
            case 2:
                title="데이터검색";
                break;
        }
        return title;
    }

}