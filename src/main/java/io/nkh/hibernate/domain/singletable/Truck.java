package io.nkh.hibernate.domain.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("truck")
public class Truck extends Vehicle{
    private String payLoad;

    public String getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(String payLoad) {
        this.payLoad = payLoad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Truck)) return false;
        if (!super.equals(o)) return false;
        Truck truck = (Truck) o;
        return Objects.equals(getPayLoad(), truck.getPayLoad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPayLoad());
    }
}
