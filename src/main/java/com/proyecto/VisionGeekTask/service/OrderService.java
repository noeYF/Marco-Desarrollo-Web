package com.proyecto.VisionGeekTask.service;

import com.proyecto.VisionGeekTask.model.*;
import com.proyecto.VisionGeekTask.repository.OrderRepository;
import com.proyecto.VisionGeekTask.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        CartService cartService) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartService = cartService;
    }

    // Crear una orden desde el carrito
    public Order createOrderFromCart(Cart cart) {

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        // Calcular total
        double total = cart.getItems().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();

        // Crear orden
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PENDIENTE");
        order.setTotal(total);

        order = orderRepository.save(order);

        // Crear sus items
        for (CartItem item : cart.getItems()) {

            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(item.getProduct());
            oi.setQuantity(item.getQuantity());
            oi.setUnitPrice(item.getProduct().getPrice());

            orderItemRepository.save(oi);
        }

        return order;
    }

    // Obtener orden por Id
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }
}
