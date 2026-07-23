package ro.unibuc.fmi.mgp.e1.position;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PositionRequest {
    @NotBlank
    @Size(min = 1, max = 255)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PositionRequest{" +
                ", name='" + name + '\'' +
                '}';
    }
}
