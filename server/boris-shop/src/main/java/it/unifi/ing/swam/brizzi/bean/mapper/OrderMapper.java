package it.unifi.ing.swam.brizzi.bean.mapper;

import it.unifi.ing.swam.brizzi.dto.OrderDto;
import it.unifi.ing.swam.brizzi.dto.ProductDto;
import it.unifi.ing.swam.brizzi.model.Order;
import it.unifi.ing.swam.brizzi.model.BasicProduct;

public class OrderMapper {

	public OrderDto generateOrderTO(Order ord){	
		OrderDto orderDTO = new OrderDto();
		orderDTO.setBuyer(ord.getBuyer().getUsername());
		orderDTO.setOrderID(ord.getOrderID());
		orderDTO.setItem(ord.getItem().getDescription());
		orderDTO.setStatus(ord.getStatus());
		return orderDTO;
	}


}
