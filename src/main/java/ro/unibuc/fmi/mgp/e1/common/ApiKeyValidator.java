package ro.unibuc.fmi.mgp.e1.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class ApiKeyValidator {
    @Value("${app.api-key}")
    private String apiKey;

    public void validateApiKey(String headerKey) {
        IO.println("Header: " + headerKey);
        IO.println("apiKey: " + apiKey);
        if (!Objects.equals(headerKey, apiKey)) {
            throw new PermisionDenied("Permision denied:Invalid API key");
        }
    }
}
