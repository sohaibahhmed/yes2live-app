package com.fyp.yes2live.Admin.User;

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
import com.fyp.yes2live.response.ExerciseItemResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultUsersList extends Fragment {
    private View view;
    private NonScrollListView listViewUser;
    private ArrayAdapter adapter;
    private SearchView searchBar;

    private APIInterface apiInterface;


    public DefaultUsersList() {
    }

    private void makeList() {
        ArrayList<String> userList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, userList);
        listViewUser.setAdapter(adapter);

        userList.clear();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ExerciseItemResponse> call = apiInterface.getUserList();
        call.enqueue(new Callback<ExerciseItemResponse>() {
            @Override
            public void onResponse(Call<ExerciseItemResponse> call, Response<ExerciseItemResponse> response) {

                if (response.body().getStatus().equalsIgnoreCase("SUCCESS")) {
                    userList.addAll(response.body().getPayload());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(adapter.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ExerciseItemResponse> call, Throwable t) {
                Toast.makeText(adapter.getContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onListUserSelect() {
        listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String usrName = listViewUser.getItemAtPosition(position).toString();

                Intent intent = new Intent(getActivity(), UsersFeature.class);
                intent.putExtra("userName", usrName);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_default_user_list, container, false);

        Intent intent = getActivity().getIntent();
        listViewUser = view.findViewById(R.id.listUserExercise);
        searchBar = view.findViewById(R.id.searchBar);

        makeList();
        onListUserSelect();

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                DefaultUsersList.this.adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                DefaultUsersList.this.adapter.getFilter().filter(newText);
                return false;
            }
        });


        return view;
    }

}