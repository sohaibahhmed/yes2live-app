package com.fyp.yes2live;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.BaseResponse;
import com.fyp.yes2live.response.SearchItemBaseResponse;
import com.fyp.yes2live.response.SearchItemResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<SearchItemResponse> searchItemResponses;
    private APIInterface apiInterface;
    ProgressBar progressBar;
    private ItemAdapter itemAdapter;
    TextView res;
    CardView cardView;
    SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);
        recyclerView = findViewById(R.id.list_item);
        searchView = findViewById(R.id.search_view1);
        progressBar = findViewById(R.id.progress);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemViewCacheSize(5);
        //getSupportActionBar().hide();
        cardView = findViewById(R.id.foodCard);
        //searchView =findViewById(R.id.searchMeal);
        fetchItems(""); //without keyword


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }

            //.contains->filter all items that contains specific characters
            private void filter(String newText) {
                List<SearchItemResponse> filteredList=new ArrayList<>();
                for (SearchItemResponse items:searchItemResponses){
                    //I have to check this
                    if(items.getItems_name().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(items);
                    }
                }
                itemAdapter.filterList(filteredList);
            }
        });
    }

    public void fetchItems(String key) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<SearchItemBaseResponse> call = apiInterface.getList1("");
        call.enqueue(new Callback<SearchItemBaseResponse>() {
            @Override
            public void onResponse(Call<SearchItemBaseResponse> call, Response<SearchItemBaseResponse> response) {

                SearchItemBaseResponse itemResponse = response.body();
                if (itemResponse.getStatus().equals("SUCCESS")) {
                    progressBar.setVisibility(View.GONE);
                    searchItemResponses = itemResponse.getPayload();
                    itemAdapter = new ItemAdapter(searchItemResponses,SearchItemActivity.this);
                    recyclerView.setAdapter(itemAdapter);
                    itemAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(SearchItemActivity.this, itemResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SearchItemBaseResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SearchItemActivity.this,  t.toString()+" Not Found in Databses ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}