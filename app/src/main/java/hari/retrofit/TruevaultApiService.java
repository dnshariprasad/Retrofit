package hari.retrofit;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Hari on 1/20/2016.
 */
public interface TrueVaultApiService {
    @FormUrlEncoded
    @POST("/v1/vaults/{vault_id}/documents")
    Call<CreateDocumentResponse> createDocument(@Header("Authorization") String authorization, @Path("vault_id") String vaultId, @Field("document") String document);

    @GET("/v1/vaults/{vault_id}/documents")
    Call<TvListResponse> readAllDocuments(@Header("Authorization") String authorization, @Path("vault_id") String vaultId, @Query("full") boolean full);
}





