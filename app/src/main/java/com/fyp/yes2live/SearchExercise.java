package com.fyp.yes2live;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.ExerciseBaseResponse;
import com.fyp.yes2live.response.SearchExerciseResponse;
import com.fyp.yes2live.response.SearchItemBaseResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchExercise extends AppCompatActivity {

    CardView cardView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<SearchExerciseResponse> searchExerciseResponses;

    private APIInterface apiInterface;
    ProgressBar progressBar;
    SearchView searchView;
    private ExerciseItemAdapter exerciseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_exercise);
        searchView=findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.items);
        progressBar = findViewById(R.id.progress);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemViewCacheSize(5);
        //getSupportActionBar().setTitle("Search Exercise");
        //getSupportActionBar().hide();
        cardView = findViewById(R.id.exercise_card);
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
                List<SearchExerciseResponse> filteredList=new ArrayList<>();
                for (SearchExerciseResponse item:searchExerciseResponses){
                    if(item.getExerciseName().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(item);
                    }
                }
                exerciseAdapter.filterList(filteredList);
            }
        });

    }

    public void fetchItems(String key) {

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ExerciseBaseResponse> call = apiInterface.viewTrackingExercises(key);
        call.enqueue(new Callback<ExerciseBaseResponse>() {
            @Override
            public void onResponse(Call<ExerciseBaseResponse> call, Response<ExerciseBaseResponse> response) {

                ExerciseBaseResponse exerciseResponse = response.body();
                if (exerciseResponse.getStatus().equals("SUCCESS")) {
                    progressBar.setVisibility(View.GONE);
                    searchExerciseResponses = exerciseResponse.getPayload();
                    exerciseAdapter = new ExerciseItemAdapter(searchExerciseResponses,SearchExercise.this);
                    recyclerView.setAdapter(exerciseAdapter);
                    exerciseAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(SearchExercise.this, exerciseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ExerciseBaseResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SearchExercise.this,  t.toString()+" Not Found in Databses ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


