import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";

        var http = new ClientHttp();
        var json = http.getData(url);

        var parser = new JsonParser();
        List<Content> contentList = parser.parse(json);

        StickersGenerator stickersGenerator = new StickersGenerator();

        for (Content content: contentList) {
            InputStream inputStream = new URL(content.getImageUrl()).openStream();
            var movieName = content.getTitle();
            var fileName = "sticker" + content.getTitle() +".png";
            stickersGenerator.generate(inputStream, fileName, movieName);
        }

    }

}