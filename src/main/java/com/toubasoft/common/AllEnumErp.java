package com.toubasoft.common;

public enum AllEnumErp {
	PREM_CONNEXION("Prem_Connexion");

	private String name = "";

	private AllEnumErp(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
