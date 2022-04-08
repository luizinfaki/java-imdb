package io.github.luizinfaki.javaimdb;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ImdbApiClient {
    private final String MY_API_KEY;

    public ImdbApiClient() {
        Dotenv dotenv = Dotenv.load();
        this.MY_API_KEY = dotenv.get("MY_IMDB_API_KEY", "");
    }


    private JSONArray getJSONArray() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + MY_API_KEY))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            JSONObject root = new JSONObject(json);

            return root.getJSONArray("items");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Movie> getMovieList() {
        List<Movie> movieList = new ArrayList<>();
        JSONArray items = getJSONArray();

        for (int i = 0; i < items.length(); i++) {
            String imageUrl = items.getJSONObject(i).getString("image");
            String title = items.getJSONObject(i).getString("title");
            String year = items.getJSONObject(i).getString("year");
            String imDbRating = items.getJSONObject(i).getString("imDbRating");

            movieList.add(new Movie(title, year, imageUrl, imDbRating));
        }

        return movieList;
    }
}
