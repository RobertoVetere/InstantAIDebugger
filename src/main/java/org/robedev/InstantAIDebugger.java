package org.robedev;

import java.io.IOException;
import java.util.Arrays;

public class InstantAIDebugger {

    /**
     * Maneja el error proporcionando un diagnóstico breve que indica
     * en qué línea ocurrió el error y cómo solucionarlo.
     *
     * @param e        La excepción o error capturado.
     * @param language El idioma en el que se desea la respuesta.
     */
    public static void handleError(Throwable e, String language) {
        String stackTrace = getStackTraceAsString(e, 10);
        String analysisPurpose = "Provide a brief explanation of the error, indicating on which line it occurred and how to fix it concisely.";
        sendToOpenAI(stackTrace, language, analysisPurpose, "Error Diagnosis");
    }

    public static void suggestBestPractices(Throwable e, String language) {
        String analysisPurpose = "Provide best programming practices related to this error, be concisely.";
        sendToOpenAI(getStackTraceAsString(e, 10), language, analysisPurpose, "Best Practices Suggestions, be concisely");
    }

    public static void suggestCodeOptimizations(Throwable e, String language) {
        String analysisPurpose = "Suggest code optimizations associated with this error.Be concisely";
        sendToOpenAI(getStackTraceAsString(e, 10), language, analysisPurpose, "Code Optimization Suggestions");
    }

    public static void checkForSecurityVulnerabilities(Throwable e, String language) {
        String analysisPurpose = "Analyze if this error can lead to security vulnerabilities, be concisely";
        sendToOpenAI(getStackTraceAsString(e, 10), language, analysisPurpose, "Security Vulnerability Analysis");
    }

    public static void suggestRefactoring(Throwable e, String language) {
        String analysisPurpose = "How could I refactor this code to make it more maintainable?Be concisely";
        sendToOpenAI(getStackTraceAsString(e, 10), language, analysisPurpose, "Refactoring Suggestions");
    }

    private static String getStackTraceAsString(Throwable e, int maxLines) {
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        return Arrays.stream(stackTraceElements)
                .limit(maxLines)
                .map(StackTraceElement::toString)
                .reduce("", (acc, line) -> acc + line + "\n");
    }

    /**
     * Envía el stack trace y el propósito del análisis a OpenAI y muestra la respuesta.
     *
     * @param stackTrace     El stack trace del error.
     * @param language       El idioma en el que se desea la respuesta.
     * @param analysisPurpose El propósito del análisis que se le solicitará a OpenAI.
     * @param header          Encabezado para mostrar en la consola.
     */
    private static void sendToOpenAI(String stackTrace, String language, String analysisPurpose, String header) {
        try {
            String response = OpenAIClient.getGPTResponse(stackTrace, language, analysisPurpose);
            System.out.println("\u001B[32m" + "InstantAIDebugger - " + header + ":\n" + response + "\u001B[0m");
        } catch (IOException ioException) {
            System.err.println("Error consulting InstantAIDebugger.");
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
