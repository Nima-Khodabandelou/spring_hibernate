package io.nkh.hibernate.domain.joined;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Guitar extends Instrument {
    private Integer numberOfStrings;

    public Integer getNumberOfStrings() {
        return numberOfStrings;
    }

    public void setNumberOfStrings(Integer numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guitar)) return false;
        if (!super.equals(o)) return false;
        Guitar guitar = (Guitar) o;
        return Objects.equals(getNumberOfStrings(), guitar.getNumberOfStrings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfStrings());
    }
}
