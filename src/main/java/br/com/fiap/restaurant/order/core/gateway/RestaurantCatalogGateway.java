package br.com.fiap.restaurant.order.core.gateway;

import br.com.fiap.restaurant.order.core.domain.model.RestaurantMenuSnapshot;

public interface RestaurantCatalogGateway {

    RestaurantMenuSnapshot findRestaurantMenuById(Long restaurantId);
}
