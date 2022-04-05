package io.github.luizinfaki.javaimdb;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        Dotenv dotenv = Dotenv.load();
        final String MY_API_KEY = dotenv.get("MY_IMDB_API_KEY", "");

        List<Movie> movieList = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + MY_API_KEY))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        JSONObject root = new JSONObject(json);
        JSONArray items = root.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            String imageUrl = items.getJSONObject(i).getString("image");
            String title = items.getJSONObject(i).getString("title");
            String year = items.getJSONObject(i).getString("year");
            String imDbRating = items.getJSONObject(i).getString("imDbRating");

            movieList.add(new Movie(title, year, imageUrl, imDbRating));
        }

        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            System.out.printf("%d. %s%n", i+1, movie.toString());
        }
    }
}