package io.github.luizinfaki.javaimdb;

public class Movie {
    // why final variables?
    // we dont want to change them later
    // because the data will come from the imdb API
    private final String title;
    private final String year;
    private final String urlImage;
    private final String fullTitle;
    private final String imDbRating;

    public Movie(String title, String year, String urlImage, String imDbRating) {
        this.title = title;
        this.year = year;
        this.urlImage = urlImage;
        this.imDbRating = imDbRating;
        this.fullTitle = title + " (" + year + ')';
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public String getImDbRating() {
        return imDbRating;
    }
}
