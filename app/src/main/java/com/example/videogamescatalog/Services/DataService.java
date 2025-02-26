package com.example.videogamescatalog.Services;

import android.os.StrictMode;
import com.example.videogamescatalog.Models.GameDataModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DataService {
    public ArrayList<GameDataModel> arrayGames;

    public DataService() {
        arrayGames = new ArrayList<>();
    }
    public ArrayList<GameDataModel> GetAllGames()
    {
        URL url;
        String sUrl = "https://www.freetogame.com/api/games";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try
        {
            url = new URL(sUrl);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonParser jsonParser = new JsonParser();
            JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getContent()));
            if (root != null && root.isJsonArray())
            {
                JsonArray rootArray = root.getAsJsonArray();
                for (JsonElement jsonElement : rootArray)
                {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    JsonElement titleElement = jsonObject.get("title");
                    JsonElement thumbnailElement = jsonObject.get("thumbnail");
                    JsonElement releaseDateElement = jsonObject.get("release_date");
                    JsonElement gameUrlElement = jsonObject.get("game_url");
                    JsonElement platformElement = jsonObject.get("platform");
                    JsonElement developerElement = jsonObject.get("developer");
                    JsonElement descriptionElement = jsonObject.get("short_description");
                    JsonElement GenreElement = jsonObject.get("genre");
                    String title = (titleElement != null ? titleElement.getAsString() : "No name available").trim();
                    String thumbnail = (thumbnailElement != null ? thumbnailElement.getAsString() : "No picture available").trim();
                    String releaseDate = (releaseDateElement != null ? releaseDateElement.getAsString() : "No date available").trim();
                    String gameUrl = (gameUrlElement != null ? gameUrlElement.getAsString() : "No URL available").trim();
                    String platform = (platformElement != null ? platformElement.getAsString() : "No platform available").trim();
                    String developer = (developerElement != null ? developerElement.getAsString() : "No developer available").trim();
                    String description = (descriptionElement != null ? descriptionElement.getAsString() : "No description available").trim();
                    String Genre =  (GenreElement != null ? GenreElement.getAsString() : "No Genre available").trim();
                    arrayGames.add(new GameDataModel(title, releaseDate, thumbnail,
                            platform, developer, description, gameUrl,Genre));
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return arrayGames;
    }
}