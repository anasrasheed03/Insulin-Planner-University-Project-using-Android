package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.FoodViewHolder> {
    private final Context mCtx;
    private final List<Food> foodList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
    public FoodsAdapter(Context mCtx, List<Food> foodList) {
        this.mCtx = mCtx;
        this.foodList = foodList;
    }


    @NonNull
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.foods_list, null);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        Food food = foodList.get(position);

        //loading the image
        Glide.with(mCtx);


        holder.textViewName.setText(food.getfood_name());
        holder.textQuantity.setText(food.getFoodQuantity());
        holder.textCalorie.setText(food.getFoodCalorie());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textQuantity;
        TextView textCalorie;



        public FoodViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewTitle);
            textQuantity = itemView.findViewById(R.id.textViewdate);
            textCalorie = itemView.findViewById(R.id.textViewquestion);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
