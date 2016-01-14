package gdg.incheon.gdg_jaehwan;

public class MovieItem {
    String title;
    String link;
    String image;
    String director;
    String actor;
    float userRating;

    @Override
    public String toString() {
        return title + "(" + director + ")";
    }
}
