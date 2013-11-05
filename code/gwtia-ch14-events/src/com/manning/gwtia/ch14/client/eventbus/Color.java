package com.manning.gwtia.ch14.client.eventbus;

import java.util.HashMap;
import java.util.Map;


public class Color {
	static String RED = "red";
	static String GREEN = "green";
	static String YELLOW = "yellow";
	static String BLUE = "blue";

	
	private String color;
	
	public Color(String color){
		this.color=color;
	}

	 public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null) return false;

	        Color aColor = (Color) o;

	        if (!color.equals(aColor.color)) {
	          return false;
	        }

	        return true;
	    }

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public int hashCode() {
	        return 0;
	}

	protected static Map<String, Color> makeValuesMap(Color[] colors) {
	     Map<String, Color> result = new HashMap<String, Color>(colors.length);
	     for (int i = 0; i < colors.length; i++) {
	            Color aColor = colors[i];
	            result.put(aColor.getColor(), aColor);
	     }

	     return result;
	}

}
