package ro.unibuc.fmi.mgp.e1.position;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.unibuc.fmi.mgp.e1.common.EntityReferencedException;
import ro.unibuc.fmi.mgp.e1.common.ResourceNotFoundException;

import java.util.List;


@Service
public class PositionService {
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Transactional public void createPosition(PositionRequest positionRequest) {
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

    @Transactional
    public void deletePositionById(Long id) {
        if (positionRepository.existsById(id)) {
            try {
                positionRepository.deleteById(id);
                positionRepository.flush();
            } catch (DataIntegrityViolationException _) {
                throw new EntityReferencedException("Cannot delete position with id " + id + " because it is referenced by other entities.");
            }
        }
    }

    @Transactional
    public PositionResponse updatePositionById(Long id, PositionRequest positionRequest) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id));

        position.setName(positionRequest.getName());
        return convertToResponse(position);
    }

    @Transactional(readOnly = true)
    public List<PositionResponse> getAllPositions() {
        return positionRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    private PositionResponse convertToResponse(Position position) {
        PositionResponse response = new PositionResponse();
        response.setId(position.getId());
        response.setName(position.getName());
        return response;
    }
}

