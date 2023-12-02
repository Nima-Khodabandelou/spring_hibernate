package io.nkh.hibernate.domain.joined;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Piano extends Instrument {
    private Integer numberOfKeys;

    public Integer getNumberOfKeys() {
        return numberOfKeys;
    }

    public void setNumberOfKeys(Integer numberOfKeys) {
        this.numberOfKeys = numberOfKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piano)) return false;
        if (!super.equals(o)) return false;
        Piano piano = (Piano) o;
        return Objects.equals(getNumberOfKeys(), piano.getNumberOfKeys());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfKeys());
    }
}
