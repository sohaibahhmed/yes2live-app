package com.fyp.yes2live;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.yes2live.response.SearchExerciseResponse;

import java.util.List;

public class ExerciseItemAdapter extends RecyclerView.Adapter<ExerciseItemAdapter.MyViewHolder> {

    List<SearchExerciseResponse> searchExerciseResponses;
    private Context context;

    public ExerciseItemAdapter(List<SearchExerciseResponse> searchExerciseResponses, Context context) {
        this.searchExerciseResponses = searchExerciseResponses;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise,parent,false);
        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position ) {

       holder.exerciseName.setText(searchExerciseResponses.get(position).getExerciseName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You select " + holder.exerciseName.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(), Workout.class);
                intent.putExtra("exerciseName",searchExerciseResponses.get(position).getExerciseName());
                intent.putExtra("ExerciseTrackingId",searchExerciseResponses.get(position).getExerciseTrackingId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return searchExerciseResponses.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView exerciseName;
        public MyViewHolder(View itemView) {
            super(itemView);
            exerciseName= itemView.findViewById(R.id.exercise_name);

        }
    }

    public void filterList(List<SearchExerciseResponse> filteredList){
        searchExerciseResponses =filteredList;
        notifyDataSetChanged();
    }



}




