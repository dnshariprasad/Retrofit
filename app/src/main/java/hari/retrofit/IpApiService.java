package hari.retrofit;


import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by kvanadev5 on 06/01/16.
 */
public interface IpApiService {
    @GET("/json")
    Call<IpApiResponseModel> getLocationInfor();
}
