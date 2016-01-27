package gdg.incheon.gdg_jaehwan.adapter;

import android.content.Context;
import android.view.ViewGroup;

import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;
import co.moonmonkeylabs.realmsearchview.RealmSearchViewHolder;
import gdg.incheon.gdg_jaehwan.data.StoreItem;
import gdg.incheon.gdg_jaehwan.viewholder.StoreItemView;
import io.realm.Realm;

/**
 * Created by 01071724654 on 2016-01-27.
 */
public class RealmItemAdpater extends RealmSearchAdapter<StoreItem, RealmItemAdpater.ViewHolder> {

    public RealmItemAdpater(
            Context context,
            Realm realm,
            String filterKey) {
        super(context, realm, filterKey);
    }


    @Override
    public RealmItemAdpater.ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder vh = new ViewHolder(new StoreItemView(viewGroup.getContext()));
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(RealmItemAdpater.ViewHolder viewHolder, int i) {
        final StoreItem storeItem = realmResults.get(i);
        viewHolder.storeItemView.setStoreItem(storeItem);
    }


    public class ViewHolder extends RealmSearchViewHolder {
        private final StoreItemView storeItemView;

        public ViewHolder(StoreItemView storeItemView) {
            super(storeItemView);
            this.storeItemView = storeItemView;
        }
    }
}
