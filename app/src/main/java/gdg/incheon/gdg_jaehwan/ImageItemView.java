package gdg.incheon.gdg_jaehwan;

import android.content.Context;
import android.text.Html;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class ImageItemView extends FrameLayout {
    public ImageItemView(Context context) {
        super(context);
        init();
    }

    ImageView iconView;
    TextView titleView, linkView;
    ImageItem mItem;

    private void init() {
        inflate(getContext(), R.layout.view_image_item, this);
        iconView = (ImageView)findViewById(R.id.image_icon);
        titleView = (TextView)findViewById(R.id.text_title);
        linkView = (TextView)findViewById(R.id.text_link);
    }

    public void setMovieItem(ImageItem item) {
        titleView.setText(Html.fromHtml(Html.fromHtml(item.title).toString()));
        linkView.setText(item.link);
        Glide.with(getContext())
                .load(item.image)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(iconView);
    }
}
