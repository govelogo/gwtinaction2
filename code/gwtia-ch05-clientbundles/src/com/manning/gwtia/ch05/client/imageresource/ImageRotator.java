package com.manning.gwtia.ch05.client.imageresource;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

public class ImageRotator extends Composite {
    static final int DURATION = 1000;

    private Image icon;

    private int iconIndex = 0;

    private ImageResource[] icons;

    public ImageRotator(ImageResource[] images) {
        icons = images;
        icon = new Image(icons[iconIndex]);
        initWidget(icon);
        Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {
            public boolean execute() {
                if (++iconIndex >= icons.length) {
                    iconIndex = 0;
                }
                icon.setResource(icons[iconIndex]);
                return true;
            }
        }, DURATION);
    }
}
