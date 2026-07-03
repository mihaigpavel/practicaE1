package ro.unibuc.fmi.mgp.e1.employee;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    @PostMapping
    public void createEmployee(@RequestBody EmployeeRequest request) {
        IO.println(request.toString());
    }
}
