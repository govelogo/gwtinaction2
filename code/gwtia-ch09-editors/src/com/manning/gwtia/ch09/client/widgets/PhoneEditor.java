package com.manning.gwtia.ch09.client.widgets;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.manning.gwtia.ch09.client.ContactFactory;
import com.manning.gwtia.ch09.client.PhoneProxy;
import com.manning.gwtia.ch09.client.ContactFactory.PhoneRequest;
import com.manning.gwtia.ch09.client.event.EditPhoneEvent;

public class PhoneEditor extends Composite implements
		ValueAwareEditor<PhoneProxy> {
	Logger log = Logger.getLogger("");

	private static PhoneEditorUiBinder uiBinder = GWT
			.create(PhoneEditorUiBinder.class);

	interface PhoneEditorUiBinder extends UiBinder<Widget, PhoneEditor> {
	}

	interface Driver extends
			RequestFactoryEditorDriver<PhoneProxy, PhoneEditor> {
	}

	@Ignore
	private PhoneProxy phoneProxy;
	private Driver driver = GWT.create(Driver.class);

	private HandlerRegistration subscription;
	@UiField
	TextBox type = new TextBox();
	@UiField
	TextBox number = new TextBox();

	private PhoneRequest phoneRequest;
	private EventBus eventBus;

	public PhoneEditor() {
		initWidget(new TextBox());
	}

	public PhoneEditor(ContactFactory factory, PhoneRequest request,
			PhoneProxy proxy) {
		initWidget(uiBinder.createAndBindUi(this));
		eventBus = new SimpleEventBus();
		phoneRequest = request;
		phoneProxy = proxy;
		driver.initialize(eventBus, factory, this);
		driver.edit(phoneProxy, phoneRequest);

		ChangeHandler changeHandler = new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent arg0) {
				driver.flush();
			}

		};

		FocusHandler handler = new FocusHandler() {

			@Override
			public void onFocus(FocusEvent arg0) {
				eventBus.fireEvent(new EditPhoneEvent(phoneProxy, phoneRequest));

			}

		};

		if (eventBus != null) {
			type.addFocusHandler(handler);
			type.addChangeHandler(changeHandler);
			number.addChangeHandler(changeHandler);
		}

	}

	public void flush() {
		// No-op
	}

	/*
	 * called  if the backend supports in-place property updates, otherwise updates will be passed via
	 * setValue.
	 * @see com.google.gwt.editor.client.ValueAwareEditor#onPropertyChange(java.lang.String[])
	 */
	public void onPropertyChange(String... paths) {
		// No-op
	}

	public void setDelegate(EditorDelegate<PhoneProxy> delegate) {
		if (subscription != null) {
			subscription.removeHandler();
		}
		subscription=delegate.subscribe();

		subscription = this.addEditPhoneHandler(new EditPhoneEvent.Handler() {

			@Override
			public void startEdit(PhoneProxy phone,
					RequestContext requestContext) {
				PhoneEditor.this.onEditPhoneEvent(phone, requestContext);

			}
		});

		
	}
	/*
	 * called  if the backend doesn't support in-place property updates, otherwise updates will be passed via
	 * setValue
	 * @see com.google.gwt.editor.client.ValueAwareEditor#setValue(java.lang.Object)
	 */
	public void setValue(PhoneProxy value) {
		phoneProxy = value;

	}

	/**
	 * Unhook event notifications when being permanently disposed of by
	 * FavoritesWidget.
	 */
	protected void cancelSubscription() {
		if (subscription != null) {
			subscription.removeHandler();
		}
	}

	@Override
	protected void onLoad() {

	}

	@Override
	protected void onUnload() {
	}

	public HandlerRegistration addEditPhoneHandler(
			EditPhoneEvent.Handler handler) {
		return eventBus.addHandler(EditPhoneEvent.TYPE, handler);
	}

	private void onEditPhoneEvent(PhoneProxy phone,
			RequestContext requestContext) {
		driver.edit(phone, requestContext);
	}

}
