package ro.unibuc.fmi.mgp.e1.position;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.mgp.e1.common.ApiKeyValidator;

import java.util.List;

@RestController
@RequestMapping("/v1/positions")
public class PositionController {

    private final PositionService positionService;
    private final ApiKeyValidator apiKeyValidator;

    public PositionController(PositionService positionService, ApiKeyValidator apiKeyValidator) {
        this.positionService = positionService;
        this.apiKeyValidator = apiKeyValidator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPosition(@Valid @RequestBody PositionRequest request, @RequestHeader("x-api-key") String apiKey) {
        apiKeyValidator.validateApiKey(apiKey);
        IO.println(request.toString());
        positionService.createPosition(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionResponse> getPositionById(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        apiKeyValidator.validateApiKey(apiKey);
        PositionResponse position = positionService.getPositionById(id);
        return ResponseEntity.ok(position);
    }

    @DeleteMapping("/{id}")
    public void deletePositionById(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        apiKeyValidator.validateApiKey(apiKey);
        positionService.deletePositionById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionResponse> updatePositionById(@PathVariable Long id, @Valid @RequestBody PositionRequest request, @RequestHeader("x-api-key") String apiKey) {
        apiKeyValidator.validateApiKey(apiKey);
        PositionResponse position = positionService.updatePositionById(id, request);
        return ResponseEntity.ok(position);
    }

    @GetMapping
    public ResponseEntity<List<PositionResponse>> getAllPositions(@RequestHeader("x-api-key") String apiKey) {
        apiKeyValidator.validateApiKey(apiKey);
        List<PositionResponse> positions = positionService.getAllPositions();
        return ResponseEntity.ok(positions);
    }
}
