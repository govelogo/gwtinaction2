package com.manning.gwtia.ch09.client.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.manning.gwtia.ch09.client.ContactFactory;
import com.manning.gwtia.ch09.client.PhoneProxy;
import com.manning.gwtia.ch09.client.ContactFactory.PhoneRequest;
import com.manning.gwtia.ch09.client.widgets.PhoneEditor;

public class PhoneListWidget extends Composite {
	Logger log = Logger.getLogger("");

	private static int NUMBER_OF_PHONES = 4;

	interface Binder extends UiBinder<Widget, PhoneListWidget> {
	}

	/**
	 * A driver that accepts a List of PhoneProxy objects, controlled by a
	 * ListEditor of PhoneProxy instances, displayed using NameLabels.
	 */
	interface Driver extends RequestFactoryEditorDriver<List<PhoneProxy>, //
			ListEditor<PhoneProxy, PhoneEditor>> {
	}

	/**
	 * This is used by the ListEditor to control the state of the UI.
	 */
	private class PhoneEditorSource extends EditorSource<PhoneEditor> {
		@Override
		public PhoneEditor create(int index) {

			PhoneEditor editor = new PhoneEditor(factory, request, proxy);

			editor.setValue(proxy);
			container.insert(editor, index);

			return editor;
		}

		@Override
		public void dispose(PhoneEditor subEditor) {
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(PhoneEditor editor, int index) {
			container.insert(editor, index);
		}
	}
	
	 private static Comparator<PhoneProxy> COMPARATOR = new Comparator<PhoneProxy>(){
	// This is where the sorting happens.
		 public int compare(PhoneProxy o1, PhoneProxy o2)
		 {
			 
			   if(!(o1.equals(null))&&!(o2.equals(null))){
				   String s1="";
				   String s2="";
				   if (!(o1.getType()==null)){
					   s1= o1.getType();
				   }
				   if (!(o2.getType()==null)){
					   s2= o2.getType();
				   }
				   //if both empty strings
				   if((s1.length()==0)&&(s2.length()==0)){
					   return 0;   
				   }else if(s1.length()==0){
					   return 1;   
				   }else if(s2.length()==0){
						return -1;   
					}


				   return s1.toLowerCase().compareTo(s2.toLowerCase());
			   }
			   return 0;
		 }
	};


	@UiField
	FlowPanel container;

	/**
	 * This list is a facade provided by the ListEditor. Structural
	 * modifications to this list (e.g. add(), set(), remove()) will trigger UI
	 * update events.
	 */
	private List<PhoneProxy> displayedList;
	private EventBus eventBus;
	private PhoneProxy proxy;

	private ContactFactory factory;
	private Driver driver;
	private PhoneRequest request;
	ListEditor<PhoneProxy, PhoneEditor> editor;

	public PhoneListWidget() {

		eventBus = new SimpleEventBus();
		factory = GWT.create(ContactFactory.class);

		initWidget(GWT.<Binder> create(Binder.class).createAndBindUi(this));
		// Create the driver which manages the data-bound widgets
		driver = GWT.<Driver> create(Driver.class);

		// Use a ListEditor that uses PhoneEditorSource
		editor = ListEditor.of(new PhoneEditorSource());
		driver.initialize(eventBus, factory, editor);
		request = factory.createPhoneRequest();
		driver.display(new ArrayList<PhoneProxy>());
		// Modifying this list triggers widget creation and destruction
		displayedList = editor.getList();

		// Create max number of phones
		for (int i = 0; i < NUMBER_OF_PHONES; i++) {
			proxy = request.create(PhoneProxy.class);
			displayedList.add(proxy);

		}
		Collections.sort(displayedList, COMPARATOR);

	}

	public List<PhoneProxy> getListOfPhones() {

		return displayedList;

	}

	public void setListOfPhones(List<PhoneProxy> list) {
		displayedList = new ArrayList<PhoneProxy>();
		Collections.sort(list, COMPARATOR);

		//driver.display(displayedList);
		for (PhoneProxy phoneProxy : list) {
			proxy = request.create(PhoneProxy.class);
			proxy.setContactId(phoneProxy.getContactId());
			proxy.setId(phoneProxy.getId());
			proxy.setNumber(phoneProxy.getNumber());
			proxy.setType(phoneProxy.getType());
			displayedList.add(proxy);

		}
		driver.display(displayedList);
	}

	public void clearList() {

		driver.display(new ArrayList<PhoneProxy>());
		displayedList = this.editor.getList();
		// Create max number of phones

		for (int i = 0; i < NUMBER_OF_PHONES; i++) {
			proxy = request.create(PhoneProxy.class);
			displayedList.add(proxy);
		}

	}

	public PhoneRequest getRequest() {
		return request;

	}

	public void setRequest(PhoneRequest phoneRequest) {
		request = phoneRequest;

	}

}
