package com.mindweaver.domain;


public class OrderRequest 
{
	 private int orderId;
	 private String name;
	 private int qty;
	 private double price;
	 
	public OrderRequest(int orderId, String name, int qty, double price) {
		super();
		this.orderId = orderId;
		this.name = name;
		this.qty = qty;
		this.price = price;
	}
	
	public OrderRequest() {}
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OrderRequest [orderId=" + orderId + ", name=" + name + ", qty=" + qty + ", price=" + price + "]";
	}
	 
	
}