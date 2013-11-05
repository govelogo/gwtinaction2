package com.manning.gwtia.ch04.client.animation;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class Highlight extends Animation{
	
	final int R = 0;
	final int G = 1;
	final int B = 2;
	
	private Element elementToAnimate;

	public Highlight(Widget w){
		super();
		elementToAnimate = w.getElement();
	}

	int[] start = new int[]{255,255,255};
	int[] end = new int[]{255,255,100};
	int[] curr = start;
	String colour;
	
	@Override
	protected void onUpdate(double progress) {
				
		double factor2 = progress;
	    double factor1 = 1 - factor2;
		
	    // Calculate new colour
	    curr[R] = (int) Math.floor(start[R] * factor1 + end[R] * factor2);
	    curr[G] = (int) Math.floor(start[G] * factor1 + end[G] * factor2);
	    curr[B] = (int) Math.floor(start[B] * factor1 + end[B] * factor2);

	    // Create new colour String
	    colour = "rgb(" + curr[R] + "," + curr[G] + "," + curr[B] + ")";
	    
	    // Manipulate style of Widget's Element to have new Background colour
	    elementToAnimate.getStyle().setBackgroundColor(colour);
	}
}
