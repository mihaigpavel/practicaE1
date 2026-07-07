package ro.unibuc.fmi.mgp.e1.position;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/positions")
public class PositionController {

    private final PositionService positionService;
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPosition(@Valid @RequestBody PositionRequest request) {
        IO.println(request.toString());
        positionService.createPosition(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionResponse> getPositionById(@PathVariable Long id) {
        PositionResponse position = positionService.getPositionById(id);
        return ResponseEntity.ok(position);
    }

    @DeleteMapping("/{id}")
    public void deletePositionById(@PathVariable Long id) {
        positionService.deletePositionById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionResponse> updatePositionById(@PathVariable Long id, @Valid @RequestBody PositionRequest request) {
        PositionResponse position = positionService.updatePositionById(id, request);
        return ResponseEntity.ok(position);
    }
}
