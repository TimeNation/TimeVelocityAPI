package net.timenation.timevelocityapi.manager.backend;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class RequestManager {

    @SneakyThrows
    public void sendHttpRequestPost(String username, UUID uuid, String ip) {
        String urlParameters = "key=nmOGpnvkCrrIug8Hxdvot9VI2Jrfwz4ASI9O9zNQjIRihQwTqF&name=" + username + "&uuid=" + uuid + "&ip=" + ip.replace("/", "");
        System.out.println(urlParameters);

        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://localhost:8080/player?" + urlParameters).openConnection();
        System.out.println(httpURLConnection.getURL().toString());
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();

        String line = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder response = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }
        bufferedReader.close();
        System.out.println(response);
    }

    @SneakyThrows
    public JsonObject getHttpResponse(UUID playerUuid) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://localhost:8080/player?key=nmOGpnvkCrrIug8Hxdvot9VI2Jrfwz4ASI9O9zNQjIRihQwTqF&uuid=" + playerUuid + "&plugin=TimeVelocityAPI").openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();

        String line = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder response = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }
        bufferedReader.close();

        if (response.toString().equals("Wrong key!")) return null;
        return new JsonParser().parse(response.toString()).getAsJsonObject();
    }
}
