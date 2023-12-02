package io.nkh.hibernate.domain.tableperclass;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Dog extends Mammal {
    private String breed;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return Objects.equals(getBreed(), dog.getBreed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBreed());
    }
}
