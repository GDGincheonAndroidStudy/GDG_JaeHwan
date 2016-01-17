package gdg.incheon.gdg_jaehwan;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

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
                .build();

        return retrofit.create(aa);
    }

}