package br.com.fiap.restaurant.order.core.domain.exception;

public class MenuItemNotFoundException extends RuntimeException {

    public MenuItemNotFoundException(Long menuItemId) {
        super("Menu item not found: " + menuItemId);
    }
}