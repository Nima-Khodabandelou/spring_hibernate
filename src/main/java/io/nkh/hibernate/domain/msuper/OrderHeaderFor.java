package io.nkh.hibernate.domain.msuper;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class OrderHeaderFor extends BaseEntityFor {
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderHeaderFor)) return false;
        if (!super.equals(o)) return false;
        OrderHeaderFor that = (OrderHeaderFor) o;
        return Objects.equals(getCustomerName(), that.getCustomerName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCustomerName());
    }
}
