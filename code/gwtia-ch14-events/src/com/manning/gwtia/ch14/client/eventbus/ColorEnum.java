package com.manning.gwtia.ch14.client.eventbus;

import java.util.Map;


public final class ColorEnum extends Color {
  private ColorEnum(String color) {
    super(color);
  }

  public static final ColorEnum red =
    new ColorEnum(RED);
  public static final ColorEnum blue =
    new ColorEnum(BLUE);
  public static final ColorEnum green =
		    new ColorEnum(GREEN);
  public static final ColorEnum yellow =
		    new ColorEnum(YELLOW);
  
  
  private static Map<String, Color> values =
		    makeValuesMap(new Color[] { red, blue,green,yellow });

  public static ColorEnum valueOf(String color) {
		    return (ColorEnum) values.get(color);
 }


}