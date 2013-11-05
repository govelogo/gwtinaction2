package com.manning.gwtia.ch08.v2.client;



import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Violation;
import com.manning.gwtia.ch08.v2.client.Factory.ContactRequest;

public class TestPanel extends Composite
{
    interface Binder extends UiBinder<Widget, TestPanel> {}
    private static Binder uiBinder = GWT.create(Binder.class);

    Logger log = Logger.getLogger("");
    Factory factory;

    @UiField TextBox txtInput;
    
    
    public TestPanel ()
    {
        factory = GWT.create(Factory.class);
        factory.initialize(new SimpleEventBus());

        initWidget(uiBinder.createAndBindUi(this));
    }

    
    @UiHandler("btnPersist")
    public void persist (ClickEvent event)
    {
        ContactRequest context = factory.createContactRequest();
        String rand = genRandomString();

        PhoneProxy phone = context.create(PhoneProxy.class);
        phone.setType("Home");
        phone.setNumber("555-" + rand);

        ContactProxy contact = context.create(ContactProxy.class);
        contact.setEmail(rand + "@example.com");
        contact.setName(rand);
        contact.setPhones(Arrays.asList(phone));
        contact.setNotes("Random notes for " + rand);

        context.persist(contact).fire();
    }

    @UiHandler("btnPersistInvalid")
    public void persistInvalid (ClickEvent event)
    {
        ContactRequest context = factory.createContactRequest();

        ContactProxy contact = context.create(ContactProxy.class);
        contact.setEmail("invalid email");
        contact.setName("");
        
        String notes = "";
        for (int i = 0; i < 20; i++) { notes += "too-long"; }
        contact.setNotes(notes);
        
        Receiver<Void> receiver = new Receiver<Void>()
        {
            public void onSuccess (Void response) { }
            
            public void onViolation (Set<Violation> errors)
            {
                for (Violation err : errors)
                    log.info(err.getPath() + " : " + err.getMessage());
            }
        };
        
        context.persist(contact).to(receiver).fire();
    }


    private String genRandomString ()
    {
        return Integer.toString((int) (Math.random() * 99999));
    }
    
    @UiHandler("btnCount")
    public void count (ClickEvent event)
    {
        Receiver<Integer> rec = new Receiver<Integer>()
        {
            public void onSuccess (Integer count)
            {
                log.info(count.toString());
            }
        };
        
        factory.createContactRequest()
            .count()
            .fire(rec);
    }

    @UiHandler("btnList")
    public void list (ClickEvent event)
    {
        Receiver<List<ContactProxy>> rec = new Receiver<List<ContactProxy>>()
        {
            public void onSuccess (List<ContactProxy> contacts)
            {
                for (ContactProxy c : contacts)
                    log.info("Contact: " + c.getId() + "=" + c.getEmail());
            }
        };
        
        factory.createContactRequest()
            .findAllContacts()
            .fire(rec);
    }

    @UiHandler("btnUpdate")
    public void update (ClickEvent event)
    {
        final ContactRequest context = factory.createContactRequest();

        Receiver<ContactProxy> rec = new Receiver<ContactProxy>()
        {
            @Override
            public void onSuccess (ContactProxy contact)
            {
                ContactRequest context2 = factory.createContactRequest();

                ContactProxy editableContact = context2.edit(contact);
                editableContact.setNotes("Last updated " + new Date());
                context2.persist(editableContact).fire();
            }
        };

        context
            .find(txtInputAsLong())
            .fire(rec);
    }

    @UiHandler("btnFetch")
    public void fetch (ClickEvent event)
    {
        Receiver<ContactProxy> rec = new Receiver<ContactProxy>()
        {
            @Override
            public void onSuccess (ContactProxy contact)
            {
                log.info("id: " + contact.getId());
                log.info("name: " + contact.getName());
                log.info("email: " + contact.getEmail());

                if (contact.getPhones() != null) {
                    for (PhoneProxy p : contact.getPhones())
                        log.info("phone: " + p.getType() + "/" + p.getNumber());
                }
                else {
                    log.info("phone: null");
                }
                
                log.info("notes: " + contact.getNotes());
            }
        };

        factory.createContactRequest()
            .find(txtInputAsLong())
            .with("phones")
            .fire(rec);
    }

    private Long txtInputAsLong ()
    {
        return (Long) Long.parseLong(txtInput.getText());
    }

    @UiHandler("btnDelete")
    public void delete (ClickEvent event)
    {
        Receiver<ContactProxy> rec = new Receiver<ContactProxy>()
        {
            @Override
            public void onSuccess (ContactProxy contact)
            {
                ContactRequest context = factory.createContactRequest();
                ContactProxy editableContact = context.edit(contact);

                context.remove(editableContact).fire();
            }
        };

        factory.createContactRequest()
            .find(txtInputAsLong())
            .fire(rec);
    }


    @UiHandler("btnDeleteAll")
    public void deleteAll (ClickEvent event)
    {
        Receiver<List<ContactProxy>> receiver = new Receiver<List<ContactProxy>>()
        {
            public void onSuccess (List<ContactProxy> contacts)
            {
                ContactRequest context = factory.createContactRequest();
                for (ContactProxy contact : contacts) {
                    context.remove(context.edit(contact));
                }
                context.fire();
            }
        };
        
        factory.createContactRequest()
            .findAllContacts()
            .to(receiver)
            .fire();
    }
}
