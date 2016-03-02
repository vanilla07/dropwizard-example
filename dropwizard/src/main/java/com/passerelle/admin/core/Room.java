package com.passerelle.admin.core;

public enum Room {

	PREMIER_CRU(0), GRAND_CRU(1), CORTON_CHARLEMAGNE(2);

	private int numVal;

	Room(int numVal) {
		this.numVal = numVal;
	}

	public int getNumVal() {
		return numVal;
	}

}
