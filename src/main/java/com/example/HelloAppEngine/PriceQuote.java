package com.example.HelloAppEngine;

public class PriceQuote {
	String ticker;
	String name;
	float price;

	public PriceQuote(String ticker, String name, float price) {
		this.ticker = ticker;
		this.name = name;
		this.price = price;
	}
	public String getTicker() {
		return ticker;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

}
