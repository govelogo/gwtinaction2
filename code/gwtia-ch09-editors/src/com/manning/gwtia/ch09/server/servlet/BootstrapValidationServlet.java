package com.manning.gwtia.ch09.server.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.validation.Validation;

import org.hibernate.validator.HibernateValidator;


public class BootstrapValidationServlet extends HttpServlet
{
    private static final long serialVersionUID = 8768275255030449223L;

    @Override
    public void init () throws ServletException
    {
        super.init();
        Validation.byProvider(HibernateValidator.class).configure();
    }
}
