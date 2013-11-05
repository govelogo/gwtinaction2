package com.manning.gwtia.ch06.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch06.client.bundle.ClientBundleExample;
import com.manning.gwtia.ch06.client.disclosure.DisclosurePanelExample;
import com.manning.gwtia.ch06.client.intro.IntroPanel;
import com.manning.gwtia.ch06.client.login1.LoginDialogBox_v1;
import com.manning.gwtia.ch06.client.login2.LoginDialogBox_v2;
import com.manning.gwtia.ch06.client.logindialog.LoginDialogBox;
import com.manning.gwtia.ch06.client.radio.RadioButtonsExample;
import com.manning.gwtia.ch06.client.split.SplitLayoutPanelExample;
import com.manning.gwtia.ch06.client.stack.StackPanelExample;


public class ExamplePanel extends Composite
{
    private static ExamplePanelUiBinder uiBinder = GWT.create(ExamplePanelUiBinder.class);
    interface ExamplePanelUiBinder extends UiBinder<Widget, ExamplePanel> {}
    interface Resources extends ClientBundle {
        @Source("gwtia.jpg") public ImageResource logo();
    }

    private LoginDialogBox_v1 loginDialog_v1 = new LoginDialogBox_v1();
    private LoginDialogBox_v2 loginDialog_v2 = new LoginDialogBox_v2();
    private LoginDialogBox loginDialog = new LoginDialogBox();
    private LoginDialogBox altLoginDialog = new LoginDialogBox(true);

    @UiField Panel exampleArea;


    public ExamplePanel ()
    {
        initWidget(uiBinder.createAndBindUi(this));
        setWidgetToMaxWidthAndHeight();
        Window.addResizeHandler(resizeHandler);
        setWidgetAsExample(new IntroPanel());
    }

    @UiHandler("loginEx_v1")
    void showLoginExample_v1 (ClickEvent event)
    {
        setWidgetAsExample(loginDialog_v1);
    }

    @UiHandler("loginEx_v2")
    void showLoginExample_v2 (ClickEvent event)
    {
        setWidgetAsExample(loginDialog_v2);
    }

    @UiHandler("showLoginEx")
    void showLoginExample (ClickEvent event)
    {
        loginDialog.show();
    }

    @UiHandler("hideLoginEx")
    void hideLoginExample (ClickEvent event)
    {
        loginDialog.hide();
    }

    @UiHandler("showLoginExAlt")
    void showLoginExampleAlt (ClickEvent event)
    {
        altLoginDialog.show();
    }

    @UiHandler("hideLoginExAlt")
    void hideLoginExampleAlt (ClickEvent event)
    {
        altLoginDialog.hide();
    }

    @UiHandler("splitLayoutPanelEx")
    void showSplitPanelExample (ClickEvent event)
    {
        Widget widget = new SplitLayoutPanelExample();
        widget.setSize("300px", "300px");
        setWidgetAsExample(widget);
    }

    @UiHandler("disclosurePanelEx")
    void showDisclosurePanelExample (ClickEvent event)
    {
        setWidgetAsExample(new DisclosurePanelExample());
    }

    @UiHandler("stackPanelEx")
    void stackPanelExample (ClickEvent event)
    {
        setWidgetAsExample(new StackPanelExample());
    }

    @UiHandler("radioButtonsEx")
    void radioButtonsExample (ClickEvent event)
    {
        setWidgetAsExample(new RadioButtonsExample());
    }

    @UiHandler("clientBundleEx")
    void clientBundleExample (ClickEvent event)
    {
        setWidgetAsExample(new ClientBundleExample());
    }

    @UiHandler("introPanel")
    void showInstructionsPanel (ClickEvent event)
    {
        setWidgetAsExample(new IntroPanel());
    }

    private void setWidgetAsExample (Widget widget)
    {
        exampleArea.clear();
        exampleArea.add(widget);
    }



    /* *************  WIDGET CENTERING CODE *************** */
    private ResizeHandler resizeHandler = new ResizeHandler()
    {
        public void onResize (ResizeEvent event)
        {
            setWidgetToMaxWidthAndHeight();
        }
    };



    private void setWidgetToMaxWidthAndHeight ()
    {
        setWidth("100%");
        setHeight(Window.getClientHeight() + "px");
    }
}
