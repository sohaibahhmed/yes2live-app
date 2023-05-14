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
import com.fyp.yes2live.response.DeleteExerciseItem;
import com.fyp.yes2live.response.GetExerciseListResponse;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseLogAdapter extends  RecyclerView.Adapter<ExerciseLogAdapter.MyViewHolder> {
    List<GetExerciseListResponse> getExerciseListResponses;
    //newly-created objects understand what has been going on
    private Context context;
    private int selectedPath = -1;
    CardView cardView;

    APIInterface apiInterface;


    public ExerciseLogAdapter(List<GetExerciseListResponse> getExerciseListResponses, Context context) {
        this.getExerciseListResponses = getExerciseListResponses;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_log_items,parent,false);
        return new MyViewHolder(view) ;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.exercise_name.setText(getExerciseListResponses.get(position).exercise_name);
            holder.time.setText(getExerciseListResponses.get(position).time_in_mins);
            holder.burnes_calories.setText(getExerciseListResponses.get(position).calories_burned);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context.getApplicationContext(), Workout.class);
                    i.putExtra("exercise_name",getExerciseListResponses.get(position).exercise_name);
                    i.putExtra("time",getExerciseListResponses.get(position).time_in_mins);
                    i.putExtra("burnes_calories",getExerciseListResponses.get(position).calories_burned);
                    i.putExtra("exercise_done_id",getExerciseListResponses.get(position).exercise_done_id);
                    Log.d("Magic", "exercise_done_id: " + getExerciseListResponses.get(position).exercise_done_id);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(i);
                }
            });

        LocalDate date;
        date= LocalDate.now();
        Integer exercise_id=getExerciseListResponses.get(position).exercise_done_id;
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context.getApplicationContext());
        long userId=sharedPreferenceManager.getUser().getId();


        holder.deleteExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Api->saveMe(Food)

                Log.d("Magic", "user id: " + userId);
                Log.d("Magic", "intake Id: " + exercise_id);
                Log.d("Magic", "Date: " + date);

                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<DeleteExerciseItem> call7 = apiInterface.deleteExDone(userId, date, exercise_id);
                call7.enqueue(new Callback<DeleteExerciseItem>() {
                    @Override
                    public void onResponse(Call<DeleteExerciseItem> call, Response<DeleteExerciseItem> response) {
                        DeleteExerciseItem deleteExerciseItem = response.body();
                        if (response.isSuccessful()) {

                            if (deleteExerciseItem.getStatus().equals("Success")) {
                                Log.d("Magic", "deleteExerciseItem: " + deleteExerciseItem.getMessage());
                                Toast.makeText(context.getApplicationContext(), deleteExerciseItem.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context.getApplicationContext(), Track.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.getApplicationContext().startActivity(intent);

                            } else if (deleteExerciseItem.getStatus().equals("Fail!")) {
                                Toast.makeText(context.getApplicationContext(), deleteExerciseItem.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context.getApplicationContext(), deleteExerciseItem.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteExerciseItem> call, Throwable t) {
                        Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    @Override
    public int getItemCount()
    {
        return getExerciseListResponses.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView exercise_name,burnes_calories,time;
        Button deleteExercise;

        public MyViewHolder(View itemView) {
            super(itemView);
            exercise_name = itemView.findViewById(R.id.txt_exercise_name);
            burnes_calories = itemView.findViewById(R.id.txt_burned_cal);
            time = itemView.findViewById(R.id.txt_time);
            deleteExercise=itemView.findViewById(R.id.deleteExercise);

            deleteExercise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
