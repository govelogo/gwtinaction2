
IMPORTING THIS PROJECT INTO ECLIPSE:

1. Install the "Google Plugin for Eclipse" - http://code.google.com/webtoolkit/download.html
2. Import this project into Eclipse (tested with Eclipse 3.5).

Notes: The Eclipse .project and .classpath files reference both the Google Plugin for Eclipse.
If you don't want to install this plugin or use an alternate IDE, like NetBeans, you will need 
to do some extra work.


RUNNING WITHOUT IMPORTING:

You can drop the entire directory target/gwtia-ch07-gwtrpc-xsrf-1.0 onto your Java server (e.g. Tomcat)
and run it there.


RUNNING WITH MAVEN:

mvn eclipse:eclipse

	This is set up to make use of the GWT SDK that comes with the Google Plugin for Eclipse,
	so you will need to have the plug-in installed.

mvn package

	Compiles the application and creates a war file.  This uses the Codehaus Maven plug-in 
	for GWT in order to compile the GWT code.
	Codehaus plug-in documentation: http://mojo.codehaus.org/gwt-maven-plugin/index.html


NOTES:

The decision to rely on the GWT SDK that ships with the Google Plugin for Eclipse was done to make
it as easy as possible to import this project into your Eclipse environment.  By relying on the plug-in
we didn't need to provide additional steps for non-Maven users, where you would need to download the
GWT SDK, set up the Eclipse classpath, etc.

We are knowingly using an old version of Twitter4J, only because that is what was available when this
chapter was written.  As of March 2012 this older version still works just fine for the example and we
have included the jar in the source download just in case it becomes publicly unavailable at a later date.
