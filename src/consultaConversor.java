import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class consultaConversor {

    public JsonObject conversor(String moneda) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/d141aee33667862f939d57cd/latest/" +moneda);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        HttpResponse<String> response = null;

        JsonObject jsonobj = null;

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonParser jp = new JsonParser();
            jsonobj = jp.parse(response.body()).getAsJsonObject();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }



        return jsonobj;
    }



}
