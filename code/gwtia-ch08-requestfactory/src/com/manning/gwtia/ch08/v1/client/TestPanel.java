package com.manning.gwtia.ch08.v1.client;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.manning.gwtia.ch08.v1.client.ContactProxy.PhoneProxy;
import com.manning.gwtia.ch08.v1.client.Factory.ContactRequest;

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
        String rand = "" + (int) (Math.random() * 99999);

        ContactRequest context = factory.createContactRequest();

        PhoneProxy phone = context.create(PhoneProxy.class);
        phone.setType("Home");
        phone.setNumber("555-" + rand);

        ContactProxy contact = context.create(ContactProxy.class);
        contact.setEmail(rand + "@example.com");
        contact.setName(rand);
        contact.setPhones(Arrays.asList(phone));
        contact.setNotes("Random notes for " + rand);
    
        context.persist()
            .using(contact)
            .fire();
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
            public void onSuccess (Void response)
            {
                log.info("We passed validation");
            }

            public void onFailure (ServerFailure error)
            {
                log.info("Server failure: " + error.getMessage());
            }

            public void onConstraintViolation(Set<ConstraintViolation<?>> violations)
            {
                for (ConstraintViolation<?> err : violations)
                    log.info(err.getPropertyPath() + " : " + err.getMessage());
            }
        };
        
        context.persist()
            .using(contact)
            .fire(receiver);
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
        Receiver<List<ContactProxy>> rec = new Receiver<List<ContactProxy>>() {
            public void onSuccess (List<ContactProxy> contacts) {
                for (ContactProxy c : contacts)
                    log.info("Contact: " + c.getId() + "=" + c.getEmail());
            }
    
            public void onFailure (ServerFailure error) {
                Window.alert(error.getMessage());
            }
        };
    
        factory.createContactRequest()
            .findAllContacts()
            .fire(rec);
    }


    @UiHandler("btnFetch")
    public void fetch (ClickEvent event)
    {
        Receiver<ContactProxy> rec = new Receiver<ContactProxy>() {
            @Override
            public void onSuccess (ContactProxy contact) {
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
            .find(((Long) Long.parseLong(txtInput.getText())))
            .with("phones")
            .fire(rec);
    }

    private Long txtInputAsLong ()
    {
        return (Long) Long.parseLong(txtInput.getText());
    }

    @UiHandler("btnUpdate")
    public void update (ClickEvent event)
    {
        Receiver<ContactProxy> rec = new Receiver<ContactProxy>() {
            public void onSuccess (ContactProxy contact) {
                ContactRequest ctx = factory.createContactRequest();
                ContactProxy editableContact = ctx.edit(contact);
                editableContact.setNotes("Last updated " + new Date());
                ctx.persist().using(editableContact).fire();
            }
        };

        factory.createContactRequest()
            .find(txtInputAsLong()).fire(rec);
    }

    @UiHandler("btnDelete")
    public void delete (ClickEvent event)
    {
        Receiver<ContactProxy> rec = new Receiver<ContactProxy>()
        {
            public void onSuccess (ContactProxy contact)
            {
                ContactRequest ctx = factory.createContactRequest();
                ContactProxy editableContact = ctx.edit(contact);
                ctx.remove().using(editableContact).fire();
            }
        };
      
        factory.createContactRequest()
            .find(txtInputAsLong()).fire(rec);
    }

    @UiHandler("btnDeleteAll")
    public void deleteAll (ClickEvent event)
    {
        Receiver<List<ContactProxy>> rec;
        rec = new Receiver<List<ContactProxy>>()
        {
            public void onSuccess (List<ContactProxy> contacts)
            {
                ContactRequest ctx = factory.createContactRequest();
                for (ContactProxy contact : contacts) {
                    ctx.remove().using(ctx.edit(contact));
                }
                ctx.fire();
            }
        };
        
        factory.createContactRequest()
            .findAllContacts().fire(rec);
    }
}
