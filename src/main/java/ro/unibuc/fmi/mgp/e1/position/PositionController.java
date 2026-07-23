package ro.unibuc.fmi.mgp.e1.position;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/positions")
@Tag(name = "Positions API", description = "Endpoints for managing job positions within the company")
public class PositionController {

    private final PositionService positionService;
    private final Logger logger = LoggerFactory.getLogger(PositionController.class);

    public PositionController(PositionService positionService) {
        this.positionService = positionService;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new position", description = "Saves a new job position to the database based on the provided request.")
    public void createPosition(@Valid @RequestBody PositionRequest request) {
        logger.debug("Am primit requestul: {}", request);
        positionService.createPosition(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a position by ID", description = "Fetches the details of a specific position using its unique identifier.")
    public ResponseEntity<PositionResponse> getPositionById(@PathVariable Long id) {
        PositionResponse position = positionService.getPositionById(id);
        return ResponseEntity.ok(position);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a position", description = "Deletes a position from the system by its ID.")
    public void deletePositionById(@PathVariable Long id) {
        positionService.deletePositionById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a position", description = "Updates the details of an existing position.")
    public ResponseEntity<PositionResponse> updatePositionById(@PathVariable Long id, @Valid @RequestBody PositionRequest request) {
        PositionResponse position = positionService.updatePositionById(id, request);
        return ResponseEntity.ok(position);
    }

    @GetMapping
    @Operation(summary = "Get all positions", description = "Retrieves a list of all positions, sorted alphabetically by name.")
    public ResponseEntity<List<PositionResponse>> getAllPositions() {
        List<PositionResponse> positions = positionService.getAllPositions();
        return ResponseEntity.ok(positions);
    }
}
