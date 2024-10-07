package org.robedev;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final String language;
    private final String analysisPurpose;

    public GlobalExceptionHandler(String language, String analysisPurpose) {
        this.language = language;
        this.analysisPurpose = analysisPurpose;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // Imprimir el stack trace
        e.printStackTrace();

        // Manejar el error con InstantAIDebugger
        InstantAIDebugger.handleError(e, language);
    }

    public static void register(String language, String analysisPurpose) {
        // Crear una instancia del manejador con los parámetros configurables
        GlobalExceptionHandler handler = new GlobalExceptionHandler(language, analysisPurpose);

        // Registrar el manejador global de excepciones para todos los hilos
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }
}
