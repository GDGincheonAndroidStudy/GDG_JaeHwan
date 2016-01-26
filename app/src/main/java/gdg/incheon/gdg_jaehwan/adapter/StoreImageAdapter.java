package gdg.incheon.gdg_jaehwan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import gdg.incheon.gdg_jaehwan.R;
import gdg.incheon.gdg_jaehwan.data.StoreItem;
import gdg.incheon.gdg_jaehwan.viewholder.StoreImageViewHolder;

public class StoreImageAdapter extends RecyclerView.Adapter<StoreImageViewHolder> {

    List<StoreItem> items = new ArrayList<StoreItem>();

    String keyword;
    int totalCount;
    Context mContext;

    public StoreImageAdapter(Context context) {
        mContext=context;
    }

    @Override
    public StoreImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.view_store_item, parent, false);
        return new StoreImageViewHolder(v,mContext);
    }

    @Override
    public void onBindViewHolder(StoreImageViewHolder holder, int position) {
        holder.setStoreItem(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(StoreItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
