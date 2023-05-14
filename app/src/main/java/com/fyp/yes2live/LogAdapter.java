package com.fyp.yes2live;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.yes2live.apiConfig.APIClient;
import com.fyp.yes2live.apiConfig.APIInterface;
import com.fyp.yes2live.response.DeleteFoodItem;
import com.fyp.yes2live.response.GetLogListResponse;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogAdapter extends  RecyclerView.Adapter<LogAdapter.MyViewHolder> {
    List<GetLogListResponse> getLunchListResponses;
    private Context context;
    private int selectedPath = -1;
    public static int categoryType;
    CardView cardView;
    SharedPreferenceManager sharedPreferenceManager;
    APIInterface apiInterface;

  //  public LogAdapter(List<GetLogListResponse> getLunchListResponses, Context context) {
    public LogAdapter(List<GetLogListResponse> getLunchListResponses, Context context) {
        this.getLunchListResponses = getLunchListResponses;
        this.context = context;
    }


    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_log,parent,false);
        //We need to pass it...
        return new MyViewHolder(view) ;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.foodName.setText(getLunchListResponses.get(position).getItemName());
        holder.calories.setText(getLunchListResponses.get(position).getCalories().toString());
        holder.servings.setText(getLunchListResponses.get(position).getServingType());
        holder.quantity.setText(getLunchListResponses.get(position).getQuantity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryType=1;
                int intake_id=getLunchListResponses.get(position).getIntakeId();
                Log.d("Magic", "intake_id: " + intake_id);
                Intent intent = new Intent(context.getApplicationContext(), FoodDescription.class);
                intent.putExtra("foodName", getLunchListResponses.get(position).getItemName());
                intent.putExtra("calories", getLunchListResponses.get(position).getCalories().toString());
                intent.putExtra("quantity", getLunchListResponses.get(position).getQuantity());
                intent.putExtra("proteins",getLunchListResponses.get(position).getProteins().toString());
                intent.putExtra("fats",getLunchListResponses.get(position).getFat().toString());
                intent.putExtra("carbs",getLunchListResponses.get(position).getCarbs().toString());
                intent.putExtra("servings", getLunchListResponses.get(position).getServingType());
                intent.putExtra("intakeId", getLunchListResponses.get(position).getIntakeId());
                intent.putExtra("update",3);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);

            }
        });

        LocalDate date;
        date= LocalDate.now();
        Integer intakeId=getLunchListResponses.get(position).intakeId;
        sharedPreferenceManager = new SharedPreferenceManager(context.getApplicationContext());
        long userId=sharedPreferenceManager.getUser().getId();


        holder.deleteFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Magic", "user id: " + userId);
                Log.d("Magic", "intake Id: " + intakeId);
                Log.d("Magic", "Date: " + date);

                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<DeleteFoodItem> call3 = apiInterface.deleteMe(userId, date, intakeId);
                call3.enqueue(new Callback<DeleteFoodItem>() {
                    @Override
                    public void onResponse(Call<DeleteFoodItem> call, Response<DeleteFoodItem> response) {
                        DeleteFoodItem deleteFoodItem = response.body();
                        if (response.isSuccessful()) {

                            if (deleteFoodItem.getStatus().equals("Success")) {
                                Log.d("Magic", "Date: " + deleteFoodItem.getMessage());
                                Toast.makeText(context.getApplicationContext(), deleteFoodItem.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context.getApplicationContext(), Track.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.getApplicationContext().startActivity(intent);

                            } else if (deleteFoodItem.getStatus().equals("Fail!")) {
                                Toast.makeText(context.getApplicationContext(), deleteFoodItem.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context.getApplicationContext(), deleteFoodItem.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteFoodItem> call, Throwable t) {
                        Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });


    }

    @Override
    public int getItemCount()
    {
        return getLunchListResponses.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView foodName, calories, quantity, servings;
        Button deleteFood;

        // public MyViewHolder(View itemView,onItemClickListener listener) {

        public MyViewHolder(View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.txt_food_name);
            deleteFood=itemView.findViewById(R.id.deleteFood);
            calories = itemView.findViewById(R.id.txt_calories);
            quantity = itemView.findViewById(R.id.txt_quantity);
            servings = itemView.findViewById(R.id.txt_serving);

            deleteFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }



}





