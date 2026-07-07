package ro.unibuc.fmi.mgp.e1.employee;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.mgp.e1.common.ApiKeyValidator;

import java.util.List;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ApiKeyValidator apiKeyValidator;

    public EmployeeController(EmployeeService employeeService, ApiKeyValidator apiKeyValidator) {
        this.employeeService = employeeService;
        this.apiKeyValidator = apiKeyValidator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@Valid @RequestBody EmployeeRequest request, @RequestHeader("x-api-key") String apiKey) {
        apiKeyValidator.validateApiKey(apiKey);
        IO.println(request.toString());
        employeeService.createEmployee(request);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        apiKeyValidator.validateApiKey(apiKey);
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        apiKeyValidator.validateApiKey(apiKey);
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@Valid @RequestBody EmployeeRequest request, @PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        apiKeyValidator.validateApiKey(apiKey);
        EmployeeResponse employee = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployees(@RequestHeader("x-api-key") String apiKey) {
        apiKeyValidator.validateApiKey(apiKey);
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}
