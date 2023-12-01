package io.nkh.hibernate.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Product extends BaseEntity {

    private String description;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    private Integer quantityAtHand = 0;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Integer getQuantityAtHand() {
        return quantityAtHand;
    }

    public void setQuantityAtHand(Integer quantityAtHand) {
        this.quantityAtHand = quantityAtHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(getDescription(), product.getDescription()) && getProductStatus() == product.getProductStatus() && Objects.equals(getCategories(), product.getCategories()) && Objects.equals(getQuantityAtHand(), product.getQuantityAtHand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDescription(), getProductStatus(), getCategories(), getQuantityAtHand());
    }
}
