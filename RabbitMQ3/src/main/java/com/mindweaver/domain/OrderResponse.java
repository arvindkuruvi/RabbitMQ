package com.mindweaver.domain;

public class OrderResponse 
{
	private boolean oderPlaced = true ;
	private String message;
	
	  

	public OrderResponse(boolean oderPlaced, String message) 
	{
		this.oderPlaced = oderPlaced;
		this.message = message;
	}
	 
	public OrderResponse() {}

	public boolean isOderPlaced() {
		return oderPlaced;
	}

	public void setOderPlaced(boolean oderPlaced) {
		this.oderPlaced = oderPlaced;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "OrderResponse [oderPlaced=" + oderPlaced + ", message=" + message + "]";
	}
	  
	  
	
}