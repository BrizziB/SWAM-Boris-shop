package it.unifi.ing.swam.brizzi.model;

public enum OrderStatus {

	REQUESTED,
	PAYED, 
	IN_PREPARATION, 
	SENT, 
	RECEIVED {
		@Override
		public OrderStatus next(){
			return values()[0];
		}
	};
	
	public OrderStatus next(){
		return values()[ordinal() + 1];
	}
	
}
