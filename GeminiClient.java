package com.mycompany.library;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GeminiClient {
    
    
    private static final String API_KEY = System.getenv("GEMINI_API_KEY");   // ✅ هذا الصحيح
    private static final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";

    public static String generateBookDescription(String title, String author) {    
        if (API_KEY == null || API_KEY.isEmpty()) {
            System.out.println("Warning: GEMINI_API_KEY not found in environment.");
            return "Description unavailable.";
        }
    
    
    
    
    
        try {
            String prompt = "Give me a one-sentence description of the book '" + title + "' by " + author + ".";
            
            String jsonBody = String.format("""
                {
                  "contents": [{
                    "parts":[{"text": "%s"}]
                  }]
                }
                """, prompt);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .header("x-goog-api-key", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return "Description unavailable.";
            }

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            return json.getAsJsonArray("candidates")
                        .get(0).getAsJsonObject()
                        .getAsJsonObject("content")
                        .getAsJsonArray("parts")
                        .get(0).getAsJsonObject()
                        .get("text").getAsString();

        } catch (Exception e) {
            System.out.println("AI description failed: " + e.getMessage());
            return "Description unavailable.";
        }
    }
}