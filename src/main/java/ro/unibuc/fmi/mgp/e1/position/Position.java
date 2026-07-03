package ro.unibuc.fmi.mgp.e1.position;

import jakarta.persistence.*;

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
}
