package ro.unibuc.fmi.mgp.e1.position;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.unibuc.fmi.mgp.e1.common.ResourceNotFoundException;


@Service
public class PositionService {
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Transactional
    public void createPosition( PositionRequest positionRequest) {
        Position position = new Position();
        position.setName(positionRequest.getName());

        positionRepository.save(position);
    }

    @Transactional(readOnly = true)
    public PositionResponse getPositionById(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id));

        return convertToResponse(position);

    }

    private PositionResponse convertToResponse(Position position) {
        PositionResponse response = new PositionResponse();
        response.setId(position.getId());
        response.setName(position.getName());
        return response;
    }
}

