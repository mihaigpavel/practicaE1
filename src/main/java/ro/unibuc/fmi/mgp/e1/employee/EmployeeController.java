package ro.unibuc.fmi.mgp.e1.employee;

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
@RequestMapping("/v1/employees")
@Tag(name = "Employee API", description = "Endpoints for managing employees within the company")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new employee", description = "Saves a new job employee to the database based on the provided request.")
    public void createEmployee(@Valid @RequestBody EmployeeRequest request) {
        logger.debug("Am primit requestul: {}", request);
        employeeService.createEmployee(request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a employee", description = "Deletes a employee from the system by its ID.")
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a employee by ID", description = "Fetches the details of a specific employee using its unique identifier.")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a employee", description = "Updates the details of an existing employee.")
    public ResponseEntity<EmployeeResponse> updateEmployee(@Valid @RequestBody EmployeeRequest request, @PathVariable Long id) {
        EmployeeResponse employee = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    @Operation(summary = "Get all employees", description = "Retrieves a list of all employees, sorted alphabetically by name.")
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}
