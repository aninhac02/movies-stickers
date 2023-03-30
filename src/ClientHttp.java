import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    public String getData(String url) {
         try {
             var uri = URI.create(url);
             var client = java.net.http.HttpClient.newHttpClient();
             var request = HttpRequest.newBuilder(uri).GET().build();
             var response = client.send(request, HttpResponse.BodyHandlers.ofString());
             return response.body();
         } catch (IOException | InterruptedException ex) {
             throw new RuntimeException();

         }
    }
}
