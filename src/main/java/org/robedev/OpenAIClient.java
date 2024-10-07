package org.robedev;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class OpenAIClient {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String MODEL = "gpt-3.5-turbo"; // Usa un modelo válido

    // Crear el HttpClient nativo
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static String getGPTResponse(String prompt, String language, String analysisPurpose) throws IOException, InterruptedException {
        // Intentar obtener la API key del entorno
        String apiKey = System.getenv("OPENAI_API_KEY");

        // Si no está en el entorno, obtenerla de la configuración de InstantAIDebuggerConfig
        if (apiKey == null || apiKey.isEmpty()) {
            apiKey = InstantAIDebuggerConfig.getApiKey();
        }

        // Verificar si la API key sigue siendo nula o vacía
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("API key no encontrada. Por favor, establece la variable de entorno OPENAI_API_KEY o configúrala mediante InstantAIDebuggerConfig.");
        }

        // Crear el mensaje del sistema para establecer el idioma
        JsonObject systemMessageObj = new JsonObject();
        systemMessageObj.addProperty("role", "system");
        systemMessageObj.addProperty("content", "Please respond in " + language + ".");

        // Crear el mensaje del usuario con el error y el propósito del análisis
        JsonObject userMessageObj = new JsonObject();
        userMessageObj.addProperty("role", "user");
        userMessageObj.addProperty("content", analysisPurpose + "\n" + prompt);

        // Añadir ambos mensajes al array de mensajes
        JsonArray messagesArray = new JsonArray();
        messagesArray.add(systemMessageObj);
        messagesArray.add(userMessageObj);

        // Crear el cuerpo de la solicitud
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("model", MODEL);
        jsonBody.add("messages", messagesArray);
        jsonBody.addProperty("max_tokens", 150);

        // Convertir el cuerpo de la solicitud a String
        String requestBody = jsonBody.toString();

        // Crear la solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

        // Enviar la solicitud y obtener la respuesta
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("La solicitud falló con el código de estado: " + response.statusCode() + " y mensaje: " + response.body());
        }

        // Obtener el cuerpo de la respuesta
        String responseBody = response.body();
        if (responseBody.isEmpty()) {
            throw new IOException("Cuerpo de respuesta vacío.");
        }

        // Parsear el JSON de la respuesta
        JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();

        // Obtener el texto generado por GPT en la respuesta
        String content = jsonResponse.getAsJsonArray("choices")
                .get(0).getAsJsonObject()
                .getAsJsonObject("message")
                .get("content").getAsString().trim();

        return content;
    }
}
