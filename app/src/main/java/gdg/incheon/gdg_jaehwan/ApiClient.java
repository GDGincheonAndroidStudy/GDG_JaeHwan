package gdg.incheon.gdg_jaehwan;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiClient {

    @GET("search/image")
    Call<SearchResult> searchMovieList(@Query("apikey") String apikey,
                                                @Query("q") String q,
                                                @Query("result") int result,
                                                @Query("pageno") int pageno,
                                                @Query("output") String output);
}
