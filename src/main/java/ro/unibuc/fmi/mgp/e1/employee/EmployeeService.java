package ro.unibuc.fmi.mgp.e1.employee;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void createEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setEmploymentDate(request.getEmploymentDate());
        employee.setPositionId(request.getFkPosition());
        // TODO: verificare existenta kfPosition
        employeeRepository.save(employee);
    }

}
