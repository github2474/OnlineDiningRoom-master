package com.hxy.OnlineDiningRoom.controller;

import com.hxy.OnlineDiningRoom.pojo.Category;
import com.hxy.OnlineDiningRoom.pojo.Product;
import com.hxy.OnlineDiningRoom.pojo.User;
import com.hxy.OnlineDiningRoom.util.Page;
import com.hxy.OnlineDiningRoom.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Model model;
    private Page page;

    @BeforeEach
    public void setUp() {
        model = new ExtendedModelMap();
        page = new Page(0, 5); // Assuming 0 is the start index and 5 is the count per page
    }

    @Test
    public void testList() {
        // Arrange
        List<Product> dummyProducts = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Product product = getProduct(i);
            dummyProducts.add(product);
        }
        when(productService.list()).thenReturn(dummyProducts);

        // Since we can't mock PageInfo directly, we'll create a real PageInfo instance with the dummyProducts
        PageInfo<Product> pageInfo = new PageInfo<>(dummyProducts);


        // Act
        String viewName = productController.list(model, page);

        // Assert
        verify(productService, times(1)).list();
        assertEquals("productmodule/product-list", viewName);
        assertTrue(model.containsAttribute("list"));
        assertTrue(model.containsAttribute("total"));
        assertTrue(model.containsAttribute("page"));
        assertEquals(dummyProducts, model.asMap().get("list"));
        assertEquals((int)pageInfo.getTotal(), model.asMap().get("total"));
        System.out.println("TotalPage: " + (int)pageInfo.getTotal() + "model.asMap().get(\"total\")" + model.asMap().get("total"));
        assertEquals(page, model.asMap().get("page"));
        assertEquals(pageInfo.getTotal(), page.getTotal());
        System.out.println("pageInfo.getTotal()" + pageInfo.getTotal() +"page.getTotal():"+ page.getTotal());
    }

    private static Product getProduct(int i) {
        Product product = new Product();
        product.setName("Product " + i);
        product.setId(i);
        product.setCategory(new Category());
        product.setPrice(i * 10.0f);
        product.setNumber(i * 2);
        product.setStatus(i % 2 == 0 ? 1 : 0);
        product.setImageurl("imageurl" + i);
        product.setMiaoshu("Description " + i);
        product.setUser(new User());
        product.setZan(i * 3);
        product.setCid(i * 10);
        product.setBid(i * 100L);
        return product;
    }
}
