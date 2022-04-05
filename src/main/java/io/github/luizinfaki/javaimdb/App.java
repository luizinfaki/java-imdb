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

        List<String> urlImages = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<String> years = new ArrayList<>();
        List<String> imDbRatings = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + MY_API_KEY))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        JSONObject obj = new JSONObject(json);
        JSONArray arrItems = obj.getJSONArray("items");

        for (int i = 0; i < arrItems.length(); i++) {
            urlImages.add(arrItems.getJSONObject(i).getString("image"));
            titles.add(arrItems.getJSONObject(i).getString("title"));
            years.add(arrItems.getJSONObject(i).getString("year"));
            imDbRatings.add(arrItems.getJSONObject(i).getString("imDbRating"));
        }

        for (int i = 0; i < titles.size(); i++) {
            System.out.printf("%d. %s (%s) - %s%n", i + 1, titles.get(i), years.get(i), imDbRatings.get(i));
        }
    }
}