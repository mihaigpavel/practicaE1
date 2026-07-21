package ro.unibuc.fmi.mgp.e1.employee;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.unibuc.fmi.mgp.e1.common.EntityReferencedException;
import ro.unibuc.fmi.mgp.e1.common.InvalidDataException;
import ro.unibuc.fmi.mgp.e1.common.ResourceNotFoundException;
import ro.unibuc.fmi.mgp.e1.position.PositionRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    @Transactional
    public void createEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setEmploymentDate(request.getEmploymentDate());
        employee.setPositionId(request.getFkPosition());
        employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployeeById(Long id) {

        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        try {
            employeeRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityReferencedException("Cannot delete employee with id " + id + " because it is referenced by other entities.");

        }
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return convertToResponse(employee);
    }

    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        if (!positionRepository.existsById(request.getFkPosition())) {
            throw new InvalidDataException("Position not found with id: " + request.getFkPosition());
        }
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setEmploymentDate(request.getEmploymentDate());
        employee.setPositionId(request.getFkPosition());

        return convertToResponse(employee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"))
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    private EmployeeResponse convertToResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setEmploymentDate(employee.getEmploymentDate());
        employeeResponse.setPositionId(employee.getPositionId());
        return employeeResponse;
    }

}
