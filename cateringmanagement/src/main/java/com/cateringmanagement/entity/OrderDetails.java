package com.cateringmanagement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The OrderDetails class represents the details of an order, including the food items and their quantities.
 * It is linked to both the Order and MenuList entities, specifying the food item and the associated order.
 */

@Entity
@Table(name = "orderdetails")  // Escaping the table name

public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private MenuList menu;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public MenuList getMenu() {
		return menu;
	}

	public void setMenu(MenuList menu) {
		this.menu = menu;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Double getTotalPrice() {
        return price * quantity;
    }

	@Override
	public String toString() {
		return "OrderDetails [orderDetailId=" + orderDetailId + ", order=" + order + ", menu=" + menu + ", price="
				+ price + ", quantity=" + quantity +  "]";
	}

    
}
