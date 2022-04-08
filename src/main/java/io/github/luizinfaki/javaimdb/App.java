package io.github.luizinfaki.javaimdb;

import java.io.PrintWriter;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        List<Movie> movieList = new ImdbApiClient().getMovieList();

        PrintWriter pw = new PrintWriter("test.html");
        boolean created = new HTMLGenerator(pw).generate(movieList);

        if (created) System.out.println("html ok");

        pw.flush();
        pw.close();
    }
}