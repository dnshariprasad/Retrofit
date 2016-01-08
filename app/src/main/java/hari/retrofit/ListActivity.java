package hari.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ListActivity extends AppCompatActivity {
    private RecyclerView all_docs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        all_docs = (RecyclerView) findViewById(R.id.all_docs);
        // create true vault retrofit
        Retrofit trueVaultRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.truevault.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create true vault APi service
        TrueVaultApiService trueVaultApiService = trueVaultRetrofit.create(TrueVaultApiService.class);
        try {
            //for authorization
            //json string to bytes
            byte[] apiData = new byte[0];

            apiData = "07c50bed-ae20-4ca1-b1a5-af7572b9f833:".toString().getBytes("UTF-8");

            //bytes to base64
            String apiBase64String = Base64.encodeToString(apiData, Base64.DEFAULT);


            trueVaultApiService.readAllDocuments("Basic " + apiBase64String.replace("\n", ""), "cca3c545-8504-46de-ac18-15676860b313", true).enqueue(new Callback<TvListResponse>() {
                @Override
                public void onResponse(Response<TvListResponse> response, Retrofit retrofit) {
                    if (response.isSuccess()) {


                        ArrayList<Document> documents = new ArrayList<Document>();
//
                        for (TvListResponse.Item item : response.body().getData().getItems()) {
                            String text = null;
                            byte[] data = Base64.decode(item.getDocument(), Base64.DEFAULT);
                            try {
                                text = new String(data, "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            if (text != null)
                                documents.add(Document.toPojo(text.toString()));
                        }


                        // Setup layout manager for items
                        LinearLayoutManager layoutManager = new LinearLayoutManager(ListActivity.this);
                        // Control orientation of the items
                        // also supports LinearLayoutManager.HORIZONTAL
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        // Attach layout manager to the RecyclerView
                        all_docs.setLayoutManager(layoutManager);

                        //create adapter
                        DocumentsAdapter adapter = new DocumentsAdapter(ListActivity.this, documents);

                        all_docs.setAdapter(adapter);

                    }

                }

                @Override
                public void onFailure(Throwable t) {
                    Log.d("readAllDocuments", "onFailure");
                }
            });


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
