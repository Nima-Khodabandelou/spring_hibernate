package io.nkh.hibernate.domain;

import jakarta.persistence.*;
import org.springframework.data.domain.Sort;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    private String customerName;
    private String phone;
    private String email;

    @Version
    private Integer version;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer")
    private Set<OrderHeader> orders = new LinkedHashSet<>();

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<OrderHeader> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderHeader> orders) {
        this.orders = orders;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
