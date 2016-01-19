package gdg.incheon.gdg_jaehwan.network;

import gdg.incheon.gdg_jaehwan.data.SearchResult;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiClient {

    @GET("search/image")
    Call<SearchResult> searchImageList(@Query("apikey") String apikey,
                                       @Query("q") String q,
                                       @Query("result") int result,
                                       @Query("pageno") int pageno,
                                       @Query("output") String output);
}
