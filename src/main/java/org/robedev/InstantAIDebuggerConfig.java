package org.robedev;


public class InstantAIDebuggerConfig {

    private static String apiKey;

    // Método para establecer la API key de forma programática
    public static void setApiKey(String key) {
        apiKey = key;
    }

    // Método para obtener la API key desde la configuración
    public static String getApiKey() {
        return apiKey;
    }
}


