# InstantAIDebugger

**InstantAIDebugger** is a library that uses artificial intelligence to help developers manage errors and exceptions in Java applications. It provides concise diagnostics, optimization suggestions, and security vulnerability analysis, all integrated directly into the development flow.

## Key Features

- **Error Diagnosis**: Brief explanations indicating where the error occurred and how to fix it.
- **Best Practices Suggestions**: Recommendations based on best programming practices.
- **Code Optimization**: Suggestions for improving code performance.
- **Vulnerability Analysis**: Review of potential security vulnerabilities.
- **Refactoring Suggestions**: Recommendations for improving code maintainability.

## Download the JAR

You can download the latest version of the **InstantAIDebugger** JAR [here](https://github.com/RobertoVetere/InstantAIDebugger/releases/tag/InstantAIDebugger).

## Instructions for Use

1. **Add the Dependency**: Include the `InstantAIDebugger` JAR in your project’s classpath.
2. **Set Up the API Key**: Before using the library, set your OpenAI API key by calling `InstantAIDebuggerConfig.setApiKey("your_api_key")` in your main class or set the `OPENAI_API_KEY` environment variable.
3. **Error Handling**: When an error occurs, simply call `InstantAIDebugger.handleError(e, "English")` within your try-catch block to automatically get diagnostics, suggestions, and analysis.
4. **Run Your Application**: Execute your Java application, and the library will handle error reporting seamlessly.

### Example Usage:

```java
import org.robedev.InstantAIDebugger;
import org.robedev.InstantAIDebuggerConfig;

public class Main {
    public static void main(String[] args) {
        InstantAIDebuggerConfig.setApiKey("your_api_key");

        try {
            // Your application logic here
        } catch (Throwable e) {
            InstantAIDebugger.handleError(e, "English");
        }
    }
}




