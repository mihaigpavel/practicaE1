package ro.unibuc.fmi.mgp.e1.position;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/positions")
public class PositionController {

    private final PositionService positionService;
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public void createPosition(@RequestBody PositionRequest request) {
        IO.println(request.toString());
        positionService.createPosition(request);
    }
}
