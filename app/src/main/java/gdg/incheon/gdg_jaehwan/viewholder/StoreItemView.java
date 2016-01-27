package gdg.incheon.gdg_jaehwan.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import gdg.incheon.gdg_jaehwan.R;
import gdg.incheon.gdg_jaehwan.data.MyApplication;
import gdg.incheon.gdg_jaehwan.data.StoreItem;

/**
 * Created by 01071724654 on 2016-01-27.
 */
public class StoreItemView extends RelativeLayout {
    ImageView imageView;
    TextView keywordView;
    Context mContext;

    public StoreItemView(Context context) {
        super(context);
        View v = inflate(context,R.layout.view_store_item,this);
        imageView = (ImageView)v.findViewById(R.id.image_store);
        keywordView = (TextView)v.findViewById(R.id.text_keyword);
    }

    public void setStoreItem(StoreItem item) {
        keywordView.setText(item.getKeyword());

        Glide.with(MyApplication.getmContext())
                .load(item.getImageUrl())
                .crossFade()
                .into(imageView);
    }

}
