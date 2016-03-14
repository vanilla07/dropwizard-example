package com.passerelle.admin.core;

public enum RoomEnum {
	 ROOM1 (0, "Premier Cru", 96.0),
	 ROOM2 (1, "Grand Cru", 96.0),
	 ROOM3 (2, "Corton Charlemagne", 110.0);
	 
	 private final int id; 
	 private final String name;
	 private final double price;
	 
	 RoomEnum (int id, String name, double price) {
		 this.id = id;
		 this.name = name;
		 this.price = price;
	 }
	 
	 public int getId(){
		 return this.id;
	 }
	 public String getName(){
		 return this.name;
	 }
	 public double getPrice(){
		 return this.price;
	 }
}
