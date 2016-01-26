package gdg.incheon.gdg_jaehwan.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import gdg.incheon.gdg_jaehwan.R;
import gdg.incheon.gdg_jaehwan.data.SearchItem;

/**
 * Created by 01071724654 on 2016-01-19.
 */
public class SearchImageViewHolder extends RecyclerView.ViewHolder{

    ImageView iconView;
    TextView titleView, linkView;
    SearchItem mItem;
    Context mContext;

    public SearchImageViewHolder(View itemView, Context context) {
        super(itemView);
        mContext=context;
        iconView = (ImageView)itemView.findViewById(R.id.image_icon);
        titleView = (TextView)itemView.findViewById(R.id.text_title);
        linkView = (TextView)itemView.findViewById(R.id.text_link);
    }

    public void setImageItem(SearchItem item) {
        titleView.setText(Html.fromHtml(Html.fromHtml(item.title).toString()));
        linkView.setText(item.link);
        linkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onLinkClick(v, getAdapterPosition());
                }
            }
        });
        Glide.with(mContext)
                .load(item.image)
                .crossFade()
                .into(iconView);
        iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null) {
                    mListener.onImageClick(v, getAdapterPosition());
                }
            }
        });
    }



    public interface OnItemClickListener {
        public void onLinkClick(View v, int position);
        public void onImageClick(View v, int position);
    }
    OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener=listener;
    }
}
