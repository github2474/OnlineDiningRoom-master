package com.hxy.OnlineDiningRoom.controller;

import com.hxy.OnlineDiningRoom.pojo.Order;
import com.hxy.OnlineDiningRoom.pojo.OrderItem;
import com.hxy.OnlineDiningRoom.pojo.Product;
import com.hxy.OnlineDiningRoom.service.OrderItemService;
import com.hxy.OnlineDiningRoom.service.OrderService;
import com.hxy.OnlineDiningRoom.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderItemService orderItemService;

    @InjectMocks
    private OrderController orderController;

    private ExtendedModelMap model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        model = new ExtendedModelMap();
    }

    @Test
    void list() {
        // Arrange
        int start = 0;
        int count = 10;
        Page page = new Page(start, count);
        List<Order> orders = new ArrayList<>();
        when(orderService.list()).thenReturn(orders);
        doAnswer(invocation -> {
            Order order = (Order) invocation.getArguments()[0];
            order.getOrderItems().addAll(mockOrderItems()); // Add mocked order items to the order
            return null; // Void method
        }).when(orderItemService).fill(any(Order.class));

        // Act
        String viewName = orderController.list(model, page);

        // Assert
        verify(orderService).list();
        verify(orderItemService).fill(any(Order.class));
        assert viewName.equals("ordermodule/order-list");
        assert model.containsAttribute("os");
        assert model.containsAttribute("page");
        assert model.containsAttribute("totals");
    }

    private List<OrderItem> mockOrderItems() {
        // Create and return a list of mocked OrderItems with mocked Products for testing purposes
        // You can adjust this as per your requirements
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            OrderItem orderItem = mock(OrderItem.class);
            Product product = mock(Product.class);
            when(product.getPrice()).thenReturn(Float.valueOf(10.10f) ); // Mock some properties if needed
            when(orderItem.getProduct()).thenReturn(product);
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    @Test
    void orderDelivery() {
        // Arrange
        Order order = mock(Order.class);
        when(orderService.get(anyInt())).thenReturn(order);

        // Act
        String viewName = orderController.delivery(order);

        // Assert
        verify(orderService).update(order);
        assert viewName.equals("redirect:list");
    }

    @Test
    void seeOrderItem() {
    // Arrange
    int oid = 1;
    Order order = mock(Order.class);
    when(orderService.get(oid)).thenReturn(order);

    // Mock orderItems for the given order
    List<OrderItem> orderItems = mockOrderItems();
    doAnswer(invocation -> {
        Order receivedOrder = (Order) invocation.getArguments()[0];
        receivedOrder.setOrderItems(orderItems); // Set the mocked order items
        return null; // Void method
    }).when(orderItemService).fill(any(Order.class));

    // Act
    String viewName = orderController.seeOrderItem(oid, model);

    // Assert
    verify(orderService).get(oid);
    verify(orderItemService).fill(order);
    assert viewName.equals("ordermodule/orderItem-list");
    assert model.containsAttribute("orderItems");
    assert model.containsAttribute("total");
    assert model.containsAttribute("totalPrice");
    }

}
