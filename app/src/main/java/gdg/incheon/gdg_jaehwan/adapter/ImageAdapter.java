package gdg.incheon.gdg_jaehwan.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gdg.incheon.gdg_jaehwan.R;
import gdg.incheon.gdg_jaehwan.data.ImageItem;
import gdg.incheon.gdg_jaehwan.viewholder.ImageViewHolder;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    List<ImageItem> items = new ArrayList<ImageItem>();

    String keyword;
    int totalCount;
    Context mContext;

    public ImageAdapter(Context context) {
        mContext=context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.view_image_item, parent, false);
        return new ImageViewHolder(v,mContext);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.setImageItem(items.get(position));
        holder.setOnItemClickListener(new ImageViewHolder.OnItemClickListener() {
            @Override
            public void onImageClick(View v, int position) {
                Toast.makeText(mContext,"제목:"+ Html.fromHtml(Html.fromHtml(items.get(position).title).toString()).toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(items.get(position).link));
                mContext.startActivity(intent);
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

    public void add(ImageItem item) {
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
