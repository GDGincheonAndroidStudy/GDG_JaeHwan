package gdg.incheon.gdg_jaehwan.data;

import io.realm.RealmObject;

/**
 * Created by 01071724654 on 2016-01-25.
 */
public class StoreItem extends RealmObject{
    private String keyword;
    private String imageUrl;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
