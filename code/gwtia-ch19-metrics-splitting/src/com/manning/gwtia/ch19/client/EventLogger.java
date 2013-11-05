package com.manning.gwtia.ch19.client;

import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.GWT;

public class EventLogger
{
    public static void logEvent (String subsys, String grp, String type)
    {
        logEvent(GWT.getModuleName(), subsys, grp, Duration.currentTimeMillis(), type);
    }

    public static native void logEvent (String module, String subsys,
            String grp, double millis, String type)
    /*-{
        if ($wnd.__gwtStatsEvent) {
          $wnd.__gwtStatsEvent({
            'moduleName' : module,
            'subSystem' : subsys,
            'evtGroup' : grp,
            'millis' : millis,
            'type' : type
          });
        }
    }-*/;
}
