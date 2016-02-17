package gdg.incheon.gdg_jaehwan.network;

import gdg.incheon.gdg_jaehwan.data.Define;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class NetworkManager {
    private static final NetworkManager networkManager = new NetworkManager();


    private NetworkManager(){}

    public static NetworkManager getIntance(){
        return networkManager;
    }

    public <T> T getRetrofit(Class<T> aa){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Define.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(aa);
    }

}