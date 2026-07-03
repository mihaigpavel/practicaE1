package ro.unibuc.fmi.mgp.e1.employee;

import java.time.LocalDate;

public class EmployeeRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate employmentDate;
    private Long fkPosition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", employmentDate=" + employmentDate +
                ", fkPosition=" + fkPosition +
                '}';
    }
}
