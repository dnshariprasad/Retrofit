package hari.retrofit;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

public interface TrueVaultApiService {
    @FormUrlEncoded
    @POST(" /v1/vaults/{vault_id}/documents")
    Call<CreateDocumentResponse> createDocument(@Header("Authorization") String authorization, @Path("vault_id") String vaultId, @Field("document") String document);
}
