package it.unifi.ing.swam.brizzi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;

import it.unifi.ing.swam.brizzi.bean.mapper.OrderMapper;
import it.unifi.ing.swam.brizzi.dao.OrderDao;
import it.unifi.ing.swam.brizzi.dto.OrderDto;
import it.unifi.ing.swam.brizzi.dto.ProductDto;
import it.unifi.ing.swam.brizzi.model.Order;
import it.unifi.ing.swam.brizzi.model.OrderStatus;
import it.unifi.ing.swam.brizzi.model.Product;

public class OrderController{

	@Inject
	private OrderMapper orderMapper;
	@Inject
	private OrderDao orderDao;


	public String retrieveAllOrders() {
		List<OrderDto> orderDTOList = new ArrayList<>();
		List<Order> orderList = orderDao.retrieveAllOrders();
		for(Order ord:orderList){
			orderDTOList.add(orderMapper.generateOrderTO(ord));
		}
		Gson jsonSerializer = new Gson();
		return jsonSerializer.toJson(orderDTOList);
	}

	public void deleteOrder(long id) {
		int numDeleted = orderDao.deleteOrderByID(id);
		if(numDeleted != 1)
			System.out.println("error in deleting single order");
	}

	public String addOrder(String requestBody) {
		Gson gson = new Gson();
		Order newOrder = gson.fromJson(requestBody, Order.class);
		newOrder.setStatus(OrderStatus.REQUESTED);
		orderDao.addOrder(newOrder);
		OrderDto orderDto = orderMapper.generateOrderTO(newOrder);
		return gson.toJson(orderDto);
	}

	public String updateOrder(String requestBody) {
		Gson gson = new Gson();
		Order updatedOrder = gson.fromJson(requestBody, Order.class);
		long orderID = updatedOrder.getOrderID();
		orderDao.updateOrder(updatedOrder, orderID);
		OrderDto orderDto = orderMapper.generateOrderTO(updatedOrder);
		return gson.toJson(orderDto);
	}

}
