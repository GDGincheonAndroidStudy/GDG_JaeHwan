package gdg.incheon.gdg_jaehwan.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gdg.incheon.gdg_jaehwan.R;
import gdg.incheon.gdg_jaehwan.adapter.StoreImageAdapter;
import gdg.incheon.gdg_jaehwan.data.StoreItem;
import gdg.incheon.gdg_jaehwan.viewholder.ImageDecoration;
import io.realm.Realm;
import io.realm.RealmResults;


public class StoreFragment extends Fragment {

    RecyclerView recyclerView;
    static StoreImageAdapter mAdapter;
    Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_store, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.view_store);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new ImageDecoration(20));
        mAdapter = new StoreImageAdapter(getContext());
        recyclerView.setAdapter(mAdapter);

        return v;
    }

    public void getRealmData() {
        Log.d("realm!", "realm!");

        RealmResults<StoreItem> result = realm.where(StoreItem.class).findAll();
        mAdapter.clear();
        for(int i=0; i<result.size(); i++) {
            mAdapter.add(result.get(i));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getRealmData();
    }

    @Override
    public void onStart() {
        super.onStart();
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onStop() {
        super.onStop();
        realm.close();
    }

}
