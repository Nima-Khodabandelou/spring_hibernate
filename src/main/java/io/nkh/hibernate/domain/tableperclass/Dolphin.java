package io.nkh.hibernate.domain.tableperclass;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Dolphin extends Mammal {
    private Boolean hasSpots;

    public Boolean getHasSpots() {
        return hasSpots;
    }

    public void setHasSpots(Boolean hasSpots) {
        this.hasSpots = hasSpots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dolphin)) return false;
        if (!super.equals(o)) return false;
        Dolphin dolphin = (Dolphin) o;
        return Objects.equals(getHasSpots(), dolphin.getHasSpots());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHasSpots());
    }
}
