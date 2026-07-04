package ro.unibuc.fmi.mgp.e1.position;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PositionRequest {
    private Long id;
    @NotBlank
    @Size(min = 1, max = 255)
    private String name;

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

    @Override
    public String toString() {
        return "PositionRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
