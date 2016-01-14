package gdg.incheon.gdg_jaehwan;

import android.content.Context;
import android.text.Html;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;


public class MovieItemView extends FrameLayout {
    public MovieItemView(Context context) {
        super(context);
        init();
    }

    ImageView iconView;
    TextView titleView, directorView;
    MovieItem mItem;

    private void init() {
        inflate(getContext(), R.layout.view_movie_item, this);
        iconView = (ImageView)findViewById(R.id.image_icon);
        titleView = (TextView)findViewById(R.id.text_title);
        directorView = (TextView)findViewById(R.id.text_director);
    }

    public void setMovieItem(MovieItem item) {
        titleView.setText(Html.fromHtml(item.title));
        directorView.setText(item.director);

        ImageLoader.getInstance().displayImage(item.image, iconView, MyApplication.getSquareOptions());
    }
}
