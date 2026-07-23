package ro.unibuc.fmi.mgp.e1.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;
import java.util.List;


@Component
public class ApiKeyFilter extends OncePerRequestFilter {


    private final ApiKeyValidator apiKeyValidator;

    private final ObjectMapper objectMapper;

    public ApiKeyFilter(ApiKeyValidator apiKeyValidator, ObjectMapper objectMapper) {
        this.apiKeyValidator = apiKeyValidator;
        this.objectMapper = objectMapper;
    }

    private static final List<String> SWAGGER_PATHS = List.of(
            "/v3/api-docs",
            "/swagger-ui/"
    );


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader("x-api-key");
        logger.debug("Cererea a trecut prin filtru");
        try {
            apiKeyValidator.validateApiKey(apiKey);
        } catch (PermissionDenied ex) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), Instant.now());
            response.setStatus(errorResponse.status());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            response.getWriter().flush();
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return SWAGGER_PATHS.stream().anyMatch(path::startsWith);
    }
}
