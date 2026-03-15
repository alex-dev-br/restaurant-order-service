package br.com.fiap.restaurant.order.core.domain.model;

import br.com.fiap.restaurant.order.core.domain.exception.MenuItemNotFoundException;
import br.com.fiap.restaurant.order.core.domain.exception.OrderValidationException;

import java.math.BigDecimal;
import java.util.List;

public class RestaurantMenuSnapshot {

    private final Long restaurantId;
    private final String restaurantName;
    private final List<MenuItemSnapshot> menuItems;

    public RestaurantMenuSnapshot(Long restaurantId, String restaurantName, List<MenuItemSnapshot> menuItems) {
        if (restaurantId == null) {
            throw new OrderValidationException("restaurantId is required");
        }

        if (restaurantName == null || restaurantName.isBlank()) {
            throw new OrderValidationException("restaurantName is required");
        }

        if (menuItems == null || menuItems.isEmpty()) {
            throw new OrderValidationException("menuItems are required");
        }

        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.menuItems = List.copyOf(menuItems);
    }

    public MenuItemSnapshot findMenuItem(Long menuItemId) {
        return menuItems.stream()
                .filter(item -> item.id().equals(menuItemId))
                .findFirst()
                .orElseThrow(() -> new MenuItemNotFoundException(menuItemId));
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public List<MenuItemSnapshot> getMenuItems() {
        return menuItems;
    }

    public record MenuItemSnapshot(
            Long id,
            String name,
            BigDecimal price
    ) {
        public MenuItemSnapshot {
            if (id == null) {
                throw new OrderValidationException("menu item id is required");
            }

            if (name == null || name.isBlank()) {
                throw new OrderValidationException("menu item name is required");
            }

            if (price == null) {
                throw new OrderValidationException("menu item price is required");
            }

            if (price.signum() < 0) {
                throw new OrderValidationException("menu item price must be greater than or equal to zero");
            }
        }
    }
}
