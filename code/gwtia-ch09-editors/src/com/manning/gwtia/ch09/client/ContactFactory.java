package com.manning.gwtia.ch09.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.manning.gwtia.ch09.server.ContactLocator;
import com.manning.gwtia.ch09.server.ContactService;
import com.manning.gwtia.ch09.server.Contact.Phone;

public interface ContactFactory extends RequestFactory {
	ContactRequest createContactRequest();

	PhoneRequest createPhoneRequest();

	@Service(value = ContactService.class, locator = ContactLocator.class)
	public interface ContactRequest extends RequestContext {
		Request<Integer> count();

		Request<ContactProxy> find(Long id);

		Request<List<ContactProxy>> findAllContacts();

		Request<ContactProxy> persist(ContactProxy contact);

		Request<Void> remove(ContactProxy contact);
	}

	@Service(value = Phone.class)
	interface PhoneRequest extends RequestContext {
		InstanceRequest<PhoneProxy, Void> persist();

		Request<List<PhoneProxy>> phoneList(Long contactId);

	}

}
