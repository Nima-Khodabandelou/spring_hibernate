package io.nkh.hibernate.domain.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("car")
public class Car extends Vehicle {
    private String trimLevel;

    public String getTrimLevel() {
        return trimLevel;
    }

    public void setTrimLevel(String trimLevel) {
        this.trimLevel = trimLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return Objects.equals(getTrimLevel(), car.getTrimLevel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTrimLevel());
    }
}
