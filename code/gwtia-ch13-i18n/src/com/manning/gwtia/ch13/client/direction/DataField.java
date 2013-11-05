/**
 * 
 */
package com.manning.gwtia.ch13.client.direction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.AnyRtlDirectionEstimator;
import com.google.gwt.i18n.shared.WordCountDirectionEstimator;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget that demonstrates GWT's Direction Estimators
 * 
 * It consists of a Label (question) and a TextBox (answer) in a HorizontalPanel.
 *
 */
public class DataField extends Composite{

	// UiBinder template code
    interface DataFieldUiBinder extends UiBinder<Widget, DataField> {}
    private static DataFieldUiBinder uiBinder = GWT.create(DataFieldUiBinder.class);
  
	/**
	 * A variable to keep track of the direction, determined from the Locale
	 */
    protected Direction dir = null;
              
    // The UI elements we will manipulate
    @UiField Label theQuestion;
    @UiField TextBox theAnswer;
    @UiField HorizontalPanel panel;
    
    
    /**
     * Construct the widget
     */
    public @UiConstructor DataField(){
    	initWidget(uiBinder.createAndBindUi(this));
    	setUpDirections();        
    	rebuildDisplay();              
    }
   
    /**
     * Adds some GWT Direction Estimators to the Label and TextBox, and determing the direction
     * from the LocaleInfo object (we will then use that to construct the order of the widget in the panel).
     */
    private void setUpDirections(){
    	// Set the direction of the question Label to be determined by the direction of the most number of words
    	// i.e. if 4 words are RTL and 3 are LTR, the question should be shown as RTL
    	// We do that by applying a WordCountDirectionEstimator to the Label
    	theQuestion.setDirectionEstimator(WordCountDirectionEstimator.get());
    	
    	// Set the direction of the answer TextBox to be RTL if any character entered is from a RTL locale
    	// We do that by applying an AnyRtlDirectionEstimator to the TextBox    	
    	theAnswer.setDirectionEstimator(AnyRtlDirectionEstimator.get());
    	
    	// At the time of writing, panels do not have direction capabilities, so we have to build that ourselves
    	// We can determine the direction of a locale by querying the LocalInfo object, and then use this
    	// information later (in our case, in the rebuildDisplay method).
    	if (dir == null) dir = LocaleInfo.getCurrentLocale().isRTL() ? Direction.RTL : Direction.LTR;
    }

    /**
     * Called to rebuild the panel so that in a locale that is:
     * 
     *  a) LTR - the question is on the left of the answer
     *  b) RTL - the answer is on the left of the question
     *  
     */
    public void rebuildDisplay(){
    	panel.clear();
    	if(dir.equals(Direction.RTL)){                            
    		panel.add(theAnswer);                                  
    		panel.add(theQuestion);                                
    	} else {                                                  
    		panel.add(theQuestion);                                
    		panel.add(theAnswer);                                  
    	}                                                         
    }                                                            

    
    // Utility method to get the answer text
    public String getAnswer(){                                  
       String answer = "";
       if (theAnswer!=null) answer = theAnswer.getText();
       return answer;
    }

    
    // Utility method to get the direction of the panel
    public Direction getDirection(){                          
       return dir;
    }
    
    
    // Utility method to get the question text
	public String getQuestion(){                                  
		String question = "";
		if (theQuestion!=null) question = theQuestion.getText();
		return question;
	}

    
    // Utility method to set the answer text
	public void setAnswer(String text){                       
		if (theAnswer!=null) theAnswer.setText(text);
	}

	
	// Utility method to set the direction
	public void setDirection(Direction direction){            
		this.dir = direction;
		rebuildDisplay();
	}

	
	// Utility method to set the question text
	public void setQuestion(String text){                       
		if (theQuestion!=null) theQuestion.setText(text);
	}
}
