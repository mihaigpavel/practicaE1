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
        PositionResponse task = positionService.getPositionById(id);
        return ResponseEntity.ok(task);
    }
}
