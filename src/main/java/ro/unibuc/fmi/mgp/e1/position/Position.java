package ro.unibuc.fmi.mgp.e1.position;

import jakarta.persistence.*;
import ro.unibuc.fmi.mgp.e1.employee.Employee;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "positionGen")
    @SequenceGenerator(name = "positionGen", sequenceName = "position_seq", allocationSize = 1)
    @Column(name = "id_position")
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "position")
    private List<Employee> employeeList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
