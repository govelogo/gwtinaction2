package com.manning.gwtia.ch08.v2.server;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Contact
{
    private Long id;
    private Integer version;
    @Size(min = 2, max = 20) private String name;
    @Pattern(regexp = "[^@\\s]+@[^@\\s]+") private String email;
    private List<Phone> phone;
    @Size(max = 100) private String notes;


    public Long getId ()
    {
        return id;
    }

    public Integer getVersion ()
    {
        return version;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public void setVersion (Integer version)
    {
        this.version = version;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public List<Phone> getPhones ()
    {
        return phone;
    }

    public void setPhones (List<Phone> phone)
    {
        this.phone = phone;
    }

    public String getNotes ()
    {
        return notes;
    }

    public void setNotes (String notes)
    {
        this.notes = notes;
    }

    public static class Phone
    {
      private String type;
      private String number;

      public String getType () {
        return type;
      }

      public void setType (String type) {
        this.type = type;
      }

      public String getNumber () {
        return number;
      }

      public void setNumber (String number) {
        this.number = number;
      }
    }
}
