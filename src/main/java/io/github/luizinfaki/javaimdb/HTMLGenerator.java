package io.github.luizinfaki.javaimdb;

import java.io.*;
import java.util.List;

public class HTMLGenerator {
    private final Writer writer;

    public HTMLGenerator(Writer w) {
        this.writer = w;
    }

    public boolean generate(List<Movie> movies) throws IOException {
        String head =
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"\n" +
                "        + \"integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" +
                "</head>\n";

        String divTemplate =
                "<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">\n" +
                "    <h4 class=\"card-header\">%d. %s</h4>\n" +
                "    <div class=\"card-body\">\n" +
                "        <img class=\"card-img\" src=\"%s\" alt=\"%s\">\n" +
                "        <p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>\n" +
                "    </div>\n" +
                "</div>\n";

        writer.write(head);

        int c = 1;
        for (Movie movie : movies) {
            try {
                writer.write(String.format(divTemplate, c, movie.getTitle(), movie.getUrlImage(), movie.getTitle(), movie.getImDbRating(), movie.getYear()));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            c++;
        }
        return true;
    }
}
