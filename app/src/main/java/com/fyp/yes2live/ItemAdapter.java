package com.fyp.yes2live;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.AddFoodResponse;
import com.fyp.yes2live.response.SearchItemResponse;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{

    private List<SearchItemResponse> searchItemResponses;
    private SharedPreferenceManager sharedPreferenceManager;
    private String type;
    private static int category;
    private Context context;
    private int selectedPath = -1;
    private APIInterface apiInterface;

    public ItemAdapter(List<SearchItemResponse> searchItemResponses, Context context) {
        this.searchItemResponses = searchItemResponses;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String name = searchItemResponses.get(position).getItems_name();
        holder.foodName.setText(searchItemResponses.get(position).getItems_name());
        holder.calories.setText(String.valueOf(searchItemResponses.get(position).getCalories()));
        holder.quantity.setText(String.valueOf(searchItemResponses.get(position).getQuantity()));
        holder.servings.setText(searchItemResponses.get(position).getServing_type());
        //holder.carbs.setText( searchItemResponses.get(position).getCarbs());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category=1;
                Toast.makeText(context, "You select " + holder.foodName.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(), FoodDescription.class);
                intent.putExtra("foodName", searchItemResponses.get(position).getItems_name());
                intent.putExtra("calories", searchItemResponses.get(position).getCalories());
                Log.d("Magic", "q3: " + searchItemResponses.get(position).getQuantity());
                intent.putExtra("quantity", searchItemResponses.get(position).getQuantity());
                intent.putExtra("servings", searchItemResponses.get(position).getServing_type());
                intent.putExtra("carbs", searchItemResponses.get(position).getCarbs());
                intent.putExtra("proteins", searchItemResponses.get(position).getProtein());
                intent.putExtra("fats", searchItemResponses.get(position).getFat());
                intent.putExtra("intake",2);
                intent.putExtra("user_log_id", searchItemResponses.get(position).getLog_food_items_id());
                Log.d("Magic", "user_log_id: " + searchItemResponses.get(position).getLog_food_items_id());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });

        holder.addFood.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                String date;
                sharedPreferenceManager=new SharedPreferenceManager(context.getApplicationContext());
                String dateHistory;

                if(Track.Type==1){
                    type="breakfast";
                }
                else if(Track.Type==2){
                    type="lunch";
                }
                else if(Track.Type==3){
                    type="morning snacks";
                }
                else if(Track.Type==4){
                    type="evening snacks";
                }
                else if(Track.Type==5){
                    type="dinner";
                }
                else if(Track.Type==6){
                    type="exercise";
                }

                dateHistory= sharedPreferenceManager.getDate();
                Log.d("Magic", "date History: "+dateHistory);

                if(dateHistory.equals(LocalDate.now())) {
                    date= String.valueOf(LocalDate.now());
                }
                else{
                    date= (dateHistory);
                }

                long food_log_id = searchItemResponses.get(position).getLog_food_items_id();
                long userId=sharedPreferenceManager.getUser().getId();
                Double c= Double.valueOf(searchItemResponses.get(position).getCalories());
                Double cs= Double.valueOf(searchItemResponses.get(position).getCarbs());
               // Double p= Double.valueOf(searchItemResponses.get(position).getProteins());
                Double f= Double.valueOf(searchItemResponses.get(position).getFat());
                Double quan= Double.valueOf(searchItemResponses.get(position).getQuantity());

                //Api->saveMe(Food)
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<AddFoodResponse> call = apiInterface.saveMeal(userId, Date.valueOf(date), food_log_id, type, quan);
                call.enqueue(new Callback<AddFoodResponse>() {
                    @Override
                    public void onResponse(Call<AddFoodResponse> call, Response<AddFoodResponse> response) {
                        AddFoodResponse addFoodResponse = response.body();
                        if (response.isSuccessful()) {

                            if (addFoodResponse.getStatus().equals("Success")) {
                                Toast.makeText(context.getApplicationContext(), addFoodResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context.getApplicationContext(), Track.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.getApplicationContext().startActivity(intent);
                            } else if (addFoodResponse.getStatus().equals("Fail!")) {
                                Toast.makeText(context.getApplicationContext(), addFoodResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            } else if (addFoodResponse.getStatus().equals("WARNING")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                //Set body message of Dialog
                                builder.setMessage(addFoodResponse.getMessage())
                                .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                                AlertDialog dialog = builder.create();
                                //dialog.closeOptionsMenu();
                                dialog.setTitle("ERROR");
                                dialog.show();
                            }
                            else {
                                Toast.makeText(context.getApplicationContext(), addFoodResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddFoodResponse> call, Throwable t) {
                        Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount()
    {
        return searchItemResponses.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView foodName, calories, quantity, servings, fats, proteins, carbs;
        Button addFood;

        public MyViewHolder(View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            calories = itemView.findViewById(R.id.calories);
            quantity = itemView.findViewById(R.id.quantity);
            servings = itemView.findViewById(R.id.serving);
            fats = itemView.findViewById(R.id.fats_cal);
            proteins = itemView.findViewById(R.id.protein_cal);
            carbs = itemView.findViewById(R.id.carbs_cal);
            addFood=itemView.findViewById(R.id.addFood);
        }
    }

    public void filterList(List<SearchItemResponse> filteredList)
    {
        searchItemResponses = filteredList;
        notifyDataSetChanged();
    }





}
