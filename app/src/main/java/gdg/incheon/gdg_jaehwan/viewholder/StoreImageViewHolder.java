package gdg.incheon.gdg_jaehwan.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import gdg.incheon.gdg_jaehwan.R;
import gdg.incheon.gdg_jaehwan.data.StoreItem;

/**
 * Created by 01071724654 on 2016-01-19.
 */
public class StoreImageViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView keywordView;
    Context mContext;

    public StoreImageViewHolder(View itemView, Context context) {
        super(itemView);
        mContext=context;
        imageView = (ImageView)itemView.findViewById(R.id.image_store);
        keywordView = (TextView)itemView.findViewById(R.id.text_keyword);
    }

    public void setStoreItem(StoreItem item) {
        keywordView.setText(item.getKeyword());

        Glide.with(mContext)
                .load(item.getImageUrl())
                .crossFade()
                .into(imageView);
    }

}
