
IMPORTING THIS PROJECT INTO ECLIPSE:

1. Install the "Google Plugin for Eclipse" - http://code.google.com/webtoolkit/download.html
2. Import this project into Eclipse (tested with Eclipse 3.5).
3. Turn on annotation processing for RequestFactory validation
   see: http://code.google.com/p/google-web-toolkit/wiki/RequestFactoryInterfaceValidation#IDE_configuration

Notes: The Eclipse .project and .classpath files reference both the Google Plugin for Eclipse.
If you don't want to install this plugin or use an alternate IDE, like NetBeans, you will need 
to do some extra work.

!!IMPORTANT!! You must turn on Annotation Processing.
See: http://code.google.com/p/google-web-toolkit/wiki/RequestFactoryInterfaceValidation

RUNNING WITHOUT IMPORTING:

You can build the project with Maven and drop the entire directory target/gwtia-ch08-requestfactory-1.0
onto your Java server (e.g. Tomcat) and run it there.


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

As of GWT 2.4 the RequestFactory interfaces need to be validated.  There are three ways to do this;
(1) enable and configure annotation processing in Eclipse, (2) use plugins when compiling using Maven,
or (3) use a validation tool.  The three methods are defined in the document,
http://code.google.com/p/google-web-toolkit/wiki/RequestFactoryInterfaceValidation.

 