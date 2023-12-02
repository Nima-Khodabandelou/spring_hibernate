package io.nkh.hibernate.domain.joined;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class ElectricGuitar extends Guitar {
    private Integer numberOfPickups;

    public Integer getNumberOfPickups() {
        return numberOfPickups;
    }

    public void setNumberOfPickups(Integer numberOfPickups) {
        this.numberOfPickups = numberOfPickups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectricGuitar)) return false;
        if (!super.equals(o)) return false;
        ElectricGuitar that = (ElectricGuitar) o;
        return Objects.equals(getNumberOfPickups(), that.getNumberOfPickups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfPickups());
    }
}
