package com.foodhub.domain.entitites.restaurant;

public class RestaurantFactory {

    public static Restaurant createRestaurant(RestaurantType type) {
        switch (type) {
            case DELIVERY:
                return new DeliveryRestaurant();
            case FAST_FOOD:
                return new FastFoodRestaurant();
            case FORMAL:
                return new FastFoodRestaurant();
            default:
                throw new IllegalArgumentException("Tipo de restaurante no soportado: " + type);
        }
    }
}
