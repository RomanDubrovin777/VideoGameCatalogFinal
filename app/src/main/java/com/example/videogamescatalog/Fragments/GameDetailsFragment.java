package com.example.videogamescatalog.Fragments;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.videogamescatalog.R;

public class GameDetailsFragment extends Fragment
{
    private String imageUrl;
    private String gameName;
    private String details;
    private String platform;
    private String developer;
    private String launchDate;
    private String Genre;
    private  String GameUrl;

    public static GameDetailsFragment newInstance(String imageUrl, String gameName, String details,
                                                  String platform, String developer, String launchDate,
                                                  String GameUrl,String genre)
    {
        GameDetailsFragment fragment = new GameDetailsFragment();
        Bundle args = new Bundle();
        args.putString("imageUrl", imageUrl);
        args.putString("gameName", gameName);
        args.putString("details", details);
        args.putString("platform", platform);
        args.putString("developer", developer);
        args.putString("launchDate", launchDate);
        args.putString("game_url", GameUrl);
        args.putString("genre", genre);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_details, container, false);
        if (getArguments() != null)
        {
            imageUrl   = getArguments().getString("imageUrl");
            gameName   = getArguments().getString("gameName");
            details    = getArguments().getString("details");
            platform   = getArguments().getString("platform");
            developer  = getArguments().getString("developer");
            launchDate = getArguments().getString("launchDate");
            Genre =getArguments().getString("genre");
            GameUrl = getArguments().getString("game_url");
            ImageView imageViewLarge = view.findViewById(R.id.gamePicutre);
            TextView textViewName = view.findViewById(R.id.TextViewName);
            TextView textViewPlatform   = view.findViewById(R.id.textViewPlatform);
            TextView textViewDeveloper  = view.findViewById(R.id.textViewDeveloper);
            TextView textViewLaunchDate = view.findViewById(R.id.textViewLaunchDate);
            TextView textViewGameUrl = view.findViewById(R.id.TextViewGameUrl);
            TextView textViewGener =view.findViewById(R.id.TextViewGenre);
            TextView textViewDescription = view.findViewById(R.id.TextViewDescription);
            textViewGameUrl.setText("Click Now To Play " + GameUrl);
            textViewGameUrl.setPaintFlags(textViewGameUrl.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            textViewGameUrl.setOnClickListener(v ->
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(GameUrl));
                startActivity(intent);
            });
            Glide.with(this).load(imageUrl).into(imageViewLarge);
            textViewName.setText(gameName);
            textViewDescription.setText("Description: "+details);
            textViewPlatform.setText("Platform: " + platform);
            textViewDeveloper.setText("Developer: " + developer);
            textViewLaunchDate.setText("Launch Date: " + launchDate);
            textViewGameUrl.setText("Click Now To Play ");
            textViewGener.setText("Genre: "+Genre);
        }

        return view;
    }

}
