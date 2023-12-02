package io.nkh.hibernate.domain.tableperclass;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Mammal {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Integer bodyTemp;

    private String species;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBodyTemp() {
        return bodyTemp;
    }

    public void setBodyTemp(Integer bodyTemp) {
        this.bodyTemp = bodyTemp;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mammal)) return false;
        Mammal mammal = (Mammal) o;
        return Objects.equals(getId(), mammal.getId()) && Objects.equals(getBodyTemp(), mammal.getBodyTemp()) && Objects.equals(getSpecies(), mammal.getSpecies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBodyTemp(), getSpecies());
    }
}
