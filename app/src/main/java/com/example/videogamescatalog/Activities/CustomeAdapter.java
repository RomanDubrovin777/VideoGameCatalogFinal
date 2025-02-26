package com.example.videogamescatalog.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.videogamescatalog.Models.GameDataModel;
import com.example.videogamescatalog.R;
import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {
    private OnItemClickListener listener;
    private ArrayList<GameDataModel> arrayOfGames;
    public CustomeAdapter(ArrayList<GameDataModel> dataSet)
    {
        this.arrayOfGames = dataSet;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewName;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewFirst);
            imageView = itemView.findViewById(R.id.imageViewCardView1);
        }
    }

    @NonNull
    @Override
    public CustomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.MyViewHolder holder, int position)
    {
        GameDataModel gamesArray = arrayOfGames.get(position);
        holder.textViewName.setText(gamesArray.getM_GameName());
        String imageUrl = gamesArray.getM_Picture();
        Glide.with(holder.imageView.getContext())
                .load(imageUrl)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(v ->
        {
            if (listener != null) {
                listener.onItemClick(gamesArray);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayOfGames.size();
    }
    public void updateData(ArrayList<GameDataModel> iNewData)
    {
        this.arrayOfGames = iNewData;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener
    {
        void onItemClick(GameDataModel gameDataModel);
    }
}
