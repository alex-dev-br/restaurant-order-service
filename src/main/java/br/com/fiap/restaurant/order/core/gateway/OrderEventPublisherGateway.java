package br.com.fiap.restaurant.order.core.gateway;

import java.math.BigDecimal;
import java.util.UUID;

public interface OrderEventPublisherGateway {

    void publishOrderCreated(UUID orderId, UUID clientId, BigDecimal amount);
}