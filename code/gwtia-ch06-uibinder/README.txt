
IMPORTING THIS PROJECT INTO ECLIPSE:

1. Install the "Google Plugin for Eclipse" - http://code.google.com/webtoolkit/download.html
2. Import this project into Eclipse (tested with Eclipse 3.5).


Notes: The Eclipse .project and .classpath files reference both the Google Plugin for Eclipse.
If you don't want to install these plugins or use an alternate IDE, like NetBeans, you will 
need to do some extra work.


RUNNING WITHOUT IMPORTING:

This application has been precompiled using GWT 2.4.  You should be able to run the project by
dropping the file target/gwtia-ch06-uibinder-1.0/uibinder-examples.html right into your browser,
even without deploying it to a web server.

Alternatively you can drop the entire directory target/gwtia-ch06-uibinder-1.0 onto your web server
and run it there.  If you deploy the application to a non-Java container (e.g. Apache httpd), then
you will want to NOT deploy either the WEB-INF or META-INF directories (they aren't needed anyway).


RUNNING WITH MAVEN:

mvn eclipse:eclipse

	This is set up to make use of the GWT SDK that comes with the Google Plugin for Eclipse,
	so you will need to have the plug-in installed.

mvn package

	Compiles the GWT application and creates a war file.  This makes use of the Codehaus Maven 
	plug-in for GWT in order to compile the GWT code.
	
mvn gwt:compile

	This is the same as the package command, except that it won't create the war file or copy
	the contents of the /war project directory.  Again, this uses the Codehaus Maven plug-in for GWT.
	Codehaus plug-in documentation: http://mojo.codehaus.org/gwt-maven-plugin/index.html


NOTES:

The decision to rely on the GWT SDK that ships with the Google Plugin for Eclipse was done to make
it as easy as possible to import this project into your Eclipse environment.  By relying on the plug-in
we didn't need to provide additional steps for non-Maven users, where you would need to download the
GWT SDK, set up the Eclipse classpath, etc.
