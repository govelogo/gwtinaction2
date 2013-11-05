package com.manning.gwtia.ch17.client.audio;


import com.google.gwt.core.client.GWT;


import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;


public class AudioExample extends Composite{
	
	@UiField FlowPanel center;
	AudioWidget music1;
	AudioWidget music2;
	AudioWidget music3;
	AudioWidget music4;

	/**
	 * For binding the user interface
	 */
    interface AudioExampleUiBinder extends UiBinder<Widget, AudioExample> {}
    private static AudioExampleUiBinder uiBinder = GWT.create(AudioExampleUiBinder.class);

    
    interface Resources extends ClientBundle {
        @Source("Cant_ge-Michael_-1909.wav") public DataResource getBlues();
        @Source("Smoot-calpomat-4673.wav") public DataResource getSmooth();
        @Source("Eriks_E-Erik_Rea-154.wav") public DataResource getElectro();
    }
    
    /**
     * Constructor, does two things:
     *   2.  Bids the user interface to the declarative definition (UIBinder)
     *   3.  Initializes the composite widget to the bound ui.
     */
    public AudioExample(){
        initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");
		music1 = new AudioWidget();
		music2 = new AudioWidget();		
		music3 = new AudioWidget();
		music4 = new AudioWidget();
		center.add(music1);
		center.add(music2);
		center.add(music3);
		center.add(music4);
		music1.setSrc(((Resources)GWT.create(Resources.class)).getBlues().getSafeUri().asString());
		music2.setSrc(((Resources)GWT.create(Resources.class)).getSmooth().getSafeUri().asString());
		music3.setSrc(((Resources)GWT.create(Resources.class)).getElectro().getSafeUri().asString());
		music4.setSrc(((Resources)GWT.create(Resources.class)).getSmooth().getSafeUri().asString());
    }
}
