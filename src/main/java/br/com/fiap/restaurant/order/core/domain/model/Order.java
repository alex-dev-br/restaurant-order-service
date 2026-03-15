package br.com.fiap.restaurant.order.core.domain.model;

import br.com.fiap.restaurant.order.core.domain.exception.OrderValidationException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID id;
    private final UUID clientId;
    private final Long restaurantId;
    private final String restaurantName;
    private final List<OrderItem> items;
    private final BigDecimal totalAmount;
    private OrderStatus status;
    private final Instant createdAt;
    private Instant updatedAt;

    public Order(
            UUID id,
            UUID clientId,
            Long restaurantId,
            String restaurantName,
            List<OrderItem> items,
            BigDecimal totalAmount,
            OrderStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        validate(id, clientId, restaurantId, restaurantName, items, totalAmount, status, createdAt, updatedAt);

        this.id = id;
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.items = List.copyOf(items);
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Order createPendingPayment(
            UUID clientId,
            Long restaurantId,
            String restaurantName,
            List<OrderItem> items
    ) {
        if (clientId == null) {
            throw new OrderValidationException("clientId is required");
        }

        if (restaurantId == null) {
            throw new OrderValidationException("restaurantId is required");
        }

        if (restaurantName == null || restaurantName.isBlank()) {
            throw new OrderValidationException("restaurantName is required");
        }

        if (items == null || items.isEmpty()) {
            throw new OrderValidationException("items are required");
        }

        BigDecimal totalAmount = items.stream()
                .map(OrderItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Instant now = Instant.now();

        return new Order(
                UUID.randomUUID(),
                clientId,
                restaurantId,
                restaurantName,
                items,
                totalAmount,
                OrderStatus.PENDING_PAYMENT,
                now,
                now
        );
    }

    public void markAsPaid() {
        this.status = OrderStatus.PAID;
        this.updatedAt = Instant.now();
    }

    private void validate(
            UUID id,
            UUID clientId,
            Long restaurantId,
            String restaurantName,
            List<OrderItem> items,
            BigDecimal totalAmount,
            OrderStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        if (id == null) {
            throw new OrderValidationException("id is required");
        }

        if (clientId == null) {
            throw new OrderValidationException("clientId is required");
        }

        if (restaurantId == null) {
            throw new OrderValidationException("restaurantId is required");
        }

        if (restaurantName == null || restaurantName.isBlank()) {
            throw new OrderValidationException("restaurantName is required");
        }

        if (items == null || items.isEmpty()) {
            throw new OrderValidationException("items are required");
        }

        if (totalAmount == null) {
            throw new OrderValidationException("totalAmount is required");
        }

        if (totalAmount.signum() <= 0) {
            throw new OrderValidationException("totalAmount must be greater than zero");
        }

        if (status == null) {
            throw new OrderValidationException("status is required");
        }

        if (createdAt == null) {
            throw new OrderValidationException("createdAt is required");
        }

        if (updatedAt == null) {
            throw new OrderValidationException("updatedAt is required");
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
