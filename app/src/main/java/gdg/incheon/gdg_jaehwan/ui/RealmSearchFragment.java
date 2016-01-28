package gdg.incheon.gdg_jaehwan.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.moonmonkeylabs.realmsearchview.RealmSearchView;
import gdg.incheon.gdg_jaehwan.R;
import gdg.incheon.gdg_jaehwan.adapter.RealmItemAdpater;
import io.realm.Realm;


public class RealmSearchFragment extends Fragment {

    RealmSearchView realmSearchView;
    RealmItemAdpater adpater;
    Realm realm;


    public static RealmSearchFragment newInstance() {
        RealmSearchFragment fragment = new RealmSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_realm, container, false);

        realm = Realm.getDefaultInstance();

        realmSearchView=(RealmSearchView)v.findViewById(R.id.search_view);
        adpater = new RealmItemAdpater(getContext(), realm, "keyword");
        realmSearchView.setAdapter(adpater);

        return v;
    }


}
