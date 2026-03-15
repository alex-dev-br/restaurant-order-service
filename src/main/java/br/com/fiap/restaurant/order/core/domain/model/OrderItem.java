package br.com.fiap.restaurant.order.core.domain.model;

import br.com.fiap.restaurant.order.core.domain.exception.OrderValidationException;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {

    private final Long menuItemId;
    private final String name;
    private final BigDecimal unitPrice;
    private final Integer quantity;
    private final BigDecimal lineTotal;

    public OrderItem(Long menuItemId, String name, BigDecimal unitPrice, Integer quantity) {
        validate(menuItemId, name, unitPrice, quantity);

        this.menuItemId = menuItemId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    private void validate(Long menuItemId, String name, BigDecimal unitPrice, Integer quantity) {
        if (menuItemId == null) {
            throw new OrderValidationException("menuItemId is required");
        }

        if (name == null || name.isBlank()) {
            throw new OrderValidationException("name is required");
        }

        if (unitPrice == null) {
            throw new OrderValidationException("unitPrice is required");
        }

        if (unitPrice.signum() < 0) {
            throw new OrderValidationException("unitPrice must be greater than or equal to zero");
        }

        if (quantity == null) {
            throw new OrderValidationException("quantity is required");
        }

        if (quantity <= 0) {
            throw new OrderValidationException("quantity must be greater than zero");
        }
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem that)) return false;
        return Objects.equals(menuItemId, that.menuItemId)
                && Objects.equals(name, that.name)
                && Objects.equals(unitPrice, that.unitPrice)
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(lineTotal, that.lineTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItemId, name, unitPrice, quantity, lineTotal);
    }
}
