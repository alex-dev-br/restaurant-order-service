package br.com.fiap.restaurant.order.core.gateway;

import br.com.fiap.restaurant.order.core.domain.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepositoryGateway {

    Order save(Order order);

    Optional<Order> findById(UUID orderId);

    List<Order> findByClientId(UUID clientId);
}
