package ro.unibuc.fmi.mgp.e1.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(name = "Employee request",
        description = "Informations about the employee")
public class EmployeeRequest {
    @NotBlank
    @Schema(example = "Mihai")
    private String firstName;
    @NotBlank
    @Schema(example = "Pavel")
    private String lastName;
    @Email
    @NotBlank
    @Schema(example = "mihai@gmail.com")
    private String email;
    @NotNull
    private LocalDate employmentDate;
    @NotNull
    @Schema(example = "1" , description = "The identifier of the associated job position")
    private Long fkPosition;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Long getFkPosition() {
        return fkPosition;
    }

    public void setFkPosition(Long fkPosition) {
        this.fkPosition = fkPosition;
    }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", employmentDate=" + employmentDate +
                ", fkPosition=" + fkPosition +
                '}';
    }
}
