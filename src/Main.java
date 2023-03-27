import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        var uri = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).GET().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> movies = parser.parse(body);

        for (Map<String, String> movie: movies) {
            System.out.println("Título: \u001b[1m"+ movie.get("fullTitle") + "\u001b[m");
            System.out.println("\u001b[30m \u001b[45m Classificação: \u001b[m " + printClassificationStars(movie.get("imDbRating")));
        }

    }

    private static String printClassificationStars(String imDbRating) {
        Double imdb = Double.parseDouble(imDbRating);
        long imdbRounded = Math.round(imdb);
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < imdbRounded; i++) {
            stars.append("\u2B50 ");
        }
        return stars.toString();
    }
}