package ro.unibuc.fmi.mgp.e1.position;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class PositionService {
    private final PositionRepository positionRepository;
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Transactional
    public void createPosition(@RequestBody PositionRequest positionRequest) {
            Position position = new Position();
            position.setName(positionRequest.getName());

            positionRepository.save(position);
    }
}

