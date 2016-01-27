package gdg.incheon.gdg_jaehwan.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;

import java.util.ArrayList;
import java.util.List;

import gdg.incheon.gdg_jaehwan.R;
import gdg.incheon.gdg_jaehwan.data.SearchItem;
import gdg.incheon.gdg_jaehwan.data.StoreItem;
import gdg.incheon.gdg_jaehwan.viewholder.SearchImageViewHolder;
import io.realm.Realm;

public class SearchImageAdapter extends RecyclerView.Adapter<SearchImageViewHolder> {

    List<SearchItem> items = new ArrayList<SearchItem>();

    String keyword;
    int totalCount;
    Context mContext;

    public SearchImageAdapter(Context context) {
        mContext=context;
    }

    @Override
    public SearchImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.view_image_item, parent, false);
        return new SearchImageViewHolder(v,mContext);
    }

    @Override
    public void onBindViewHolder(SearchImageViewHolder holder, int position) {
        holder.setImageItem(items.get(position));
        holder.setOnItemClickListener(new SearchImageViewHolder.OnItemClickListener() {
            @Override
            public void onLinkClick(View v, int position) {
                Toast.makeText(mContext,"제목:"+ Html.fromHtml(Html.fromHtml(items.get(position).title).toString()).toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(items.get(position).link));
                mContext.startActivity(intent);
            }

            @Override
            public void onImageClick(View v, final int position) {


                new AlertDialogWrapper.Builder(mContext)
                        .setTitle("이미지 저장")
                        .setMessage("저장하시겠습니까?")
                        .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                Realm realm = Realm.getInstance(mContext);
                                realm.beginTransaction();

                                StoreItem storeItem = realm.createObject(StoreItem.class);
                                storeItem.setKeyword(keyword);
                                storeItem.setImageUrl(items.get(position).image);

                                realm.commitTransaction();
                                Toast.makeText(mContext,"저장",Toast.LENGTH_SHORT).show();

                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getKeyword() {
        return keyword;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getTotalCount() {
        return totalCount;
    }

    public int getStartIndex() {
        if (items.size() < totalCount) {
            return items.size() + 1;
        }
        return -1;
    }

    public void add(SearchItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return items.get(position);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }




}
