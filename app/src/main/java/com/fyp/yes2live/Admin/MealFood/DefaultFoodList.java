package com.fyp.yes2live.Admin.MealFood;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fyp.yes2live.R;
import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.FoodItemResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultFoodList extends Fragment {
    private View view;
    private NonScrollListView listViewFood;
    private ArrayAdapter adapter;
    private SearchView searchBar;

    private APIInterface apiInterface;


    public DefaultFoodList() {
    }

    private void makeList() {
        ArrayList<String> foodList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, foodList);
        listViewFood.setAdapter(adapter);//

        foodList.clear();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<FoodItemResponse> call = apiInterface.getNameList();
        call.enqueue(new Callback<FoodItemResponse>() {
            @Override
            public void onResponse(Call<FoodItemResponse> call, Response<FoodItemResponse> response) {

                if (response.body().getStatus().equalsIgnoreCase("SUCCESS")) {
                    foodList.addAll(response.body().getPayload());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(adapter.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<FoodItemResponse> call, Throwable t) {
                Toast.makeText(adapter.getContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onListItemSelect() {
        listViewFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String foodName = listViewFood.getItemAtPosition(position).toString();

                Intent intent = new Intent(getActivity(), FoodFeature.class);
                intent.putExtra("foodName", foodName);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_default_food_list, container, false);

        Intent intent = getActivity().getIntent();
        listViewFood = view.findViewById(R.id.listViewFood);
        searchBar = view.findViewById(R.id.searchBar);

        makeList();
        onListItemSelect();
//search bar
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                DefaultFoodList.this.adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                DefaultFoodList.this.adapter.getFilter().filter(newText);
                return false;
            }
        });


        return view;
    }

}