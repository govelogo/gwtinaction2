package com.manning.gwtia.ch18.rebind;

import java.io.PrintWriter;
import java.util.List;

import com.google.gwt.core.ext.BadPropertyValueException;
import com.google.gwt.core.ext.ConfigurationProperty;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.PropertyOracle;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.TreeLogger.Type;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JConstructor;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * Class that will be called by the compiler in order to create a replacement
 * class to be used in the code that displays some debug information per widget.
 * 
 * It checks to see if the GWT configuration property <b>gwtia.widgetDebug</b>
 * is set to the value <b>true</b>, and if so creates the new class (if not, the
 * original class is used). This property is set in the example in the
 * Generator.gwt.xml module file in the com.manning.gwtia.ch17.client.generator
 * package.
 * 
 * If we are creating a new class, then the generator creates a new constructor
 * as well as copying every non static method in the class. Each generated
 * method will log information about execution to the current logger as well as
 * calling it's parent method to ensure the functionality presented to the user
 * is not altered.
 * 
 */
public class WidgetDebugGenerator extends Generator {

	/**
	 * Name of the logger that will be used in the generated type
	 */
	String LOGGER_VAR_NAME = "logger";
	
	
	/**
	 * Helper method that creates a string of a methods parameter names from the 
	 * JParameter array passed in to it. This can later be appended to a method name
	 * to create a method call (in this generators case this allows us to create the
	 * calls to the parents methods).
	 * @param params An array of JParamters
	 * @return A String representing the parameters together with the brackets
	 */
	private String determineParams(JParameter[] params) {
		// Create a StringBuffer we'll use for creating the return string of parameters
		StringBuffer paramStringBuffer = new StringBuffer();
		
		// Start the return string of parameters with a left bracket
		paramStringBuffer.append("(");
		
		// For each parameter in the JParameter array......
		for (JParameter param : params) {
			// Add the parameter name, followed by a comma
			paramStringBuffer.append(param.getName());
			paramStringBuffer.append(", ");
		}
		
		// Now we have a StringBuffer that holds something like (a, b, c,
		// we have to get rid of the last comma, and add a right bracket.
		// Or we have simply a ( and we need to add the right bracket.
		String paramString = "";
		// Let's make sure the 
		if (paramStringBuffer.length() > 1)
			paramString = paramStringBuffer.substring(0,
					paramStringBuffer.length() - 2);
		else
			paramString = paramStringBuffer.toString();
		paramString += ");";
		
		// Now we're left with a String such as (a,b,c) or ().		
		return paramString;
	}

	/**
	 * The generate method is called by the compiler, when the conditions in the
	 * generate-with tag defined in a module file are met.
	 * 
	 * The compiler passes in the logger it is currently using, the context of
	 * compilation (which will allow us access to all the types, resources and
	 * properties valid in the current compilation permutation), as well as the
	 * type name currently being compiled (and which triggered the execution of
	 * this generator).
	 * 
	 * In our generator example, we check if we should run, by looking for the
	 * gwtia.debugWidgets configuration parameter (set in the module file) has
	 * value true - if not, the generator will terminate and the compiler will
	 * use the original type.
	 * 
	 * Assuming we are generating, we create a new SourceWriter in which the new
	 * type will be created before being committed and returned to the compiler
	 * to use instead of the original type.
	 * 
	 * The generator is relatively simple, and will walk through all methods in
	 * the input class creating a overloaded method in the new class, that
	 * writes a call to the logger and then a call to the parents method.
	 * 
	 */
	public String generate(TreeLogger logger, GeneratorContext context,
			String typeName) throws UnableToCompleteException {
		// Try and perform the generation of the new class, and if fail, throw
		// an UnableToCompleteException.
		try {
			// Write to the compiler's logger that we're starting
			logger.log(TreeLogger.INFO, "Starting Generator");

			// try and get the value of the gwtia.debugWidgets configuration
			// property
			// from a helper method we have defined later
			ConfigurationProperty useGenerator = getConfigurationProperty(logger, context,"gwtia.debugWidgets");

			// If we have value of true in gwtia.debugWidgets then continue
			// generation of the
			// new class.
			if (useGenerator != null){
				List<String> values = useGenerator.getValues();
				if(values.contains("true")){
	
					// Get the TypeOracle
					TypeOracle types = context.getTypeOracle();
					logger.log(TreeLogger.INFO, "Got Oracle");
	
					// Now we get some information about the current type under
					// compilation....
					JClassType type = types.getType(typeName);
					String packageName = type.getPackage().getName();
					String simpleClassName = type.getSimpleSourceName();
	
					// And create the name of the new type that we wil construct in
					// this generation
					String proxyName = simpleClassName + "GWTiAProxy";
					String qualifiedName = packageName + "." + proxyName;
	
					// We call a helper method to get the SourceWriter that we will
					// use to
					// write the generated class to.
					// Most information passed we have either had passed to us
					// already, or have found
					// from an Oracle. The last two arguments are the new imports
					// that are needed in
					// the new class to support logging.
					SourceWriter srcWriter = getSourceWriter(logger, context,
							packageName, proxyName, simpleClassName,
							"java.util.logging.Level", "java.util.logging.Logger");
	
					// If we were successful in getting a Source Writer then we can
					// continue
					if (srcWriter != null) {
						// Write on the compiler's logger thar we're starting to
						// generate code
						logger.log(TreeLogger.INFO, "Starting to generate code");
	
						// Call helper method to set up logging in the new class
						writeLoggerCode(logger, srcWriter);
	
						// For each constructor in the source type, call the
						// writeUpdatedConstructor helper method
						// to create a new constructor that logs the construction of
						// a widget to log when
						// the code is executing
						for (JConstructor constructor : type.getConstructors()) {
							writeUpdatedConstructor(logger, srcWriter, constructor,
									simpleClassName, proxyName);
						}
	
						// For each non static method in the source type, call the
						// writeUpdatedAddMethod helper method
						// to create a new method that logs the execution of that
						// method in a widget to the
						// execution log when the code is executing
						for (JMethod method : type.getMethods()) {
							if (!method.isStatic())
								writeUpdatedAddMethod(logger, srcWriter,
										proxyName, method);
						}
	
						// Now we have completed our generation, we need to commit
						// the SourceWriter object to
						// the compilation resources so it is available for
						// compilation into JavaScript
						srcWriter.commit(logger);
	
						// We write to the compilation logger that we're finished
						// with the generation.
						logger.log(TreeLogger.INFO, "Finished generating code");
					}
					// Now we've created the new class, we return it's qualified
					// name (which
					// tells the compiler to use this new class in place of the
					// original
					// class)
					return qualifiedName;
				}
			}
			// Otherwise we've not done anything, so just return null (which
			// tells the
			// compiler to use the original class).
			return null;
		} catch (NotFoundException e) {
			logger.log(TreeLogger.ERROR, "Class '" + typeName + "' Not Found",
					e);
			throw new UnableToCompleteException();
		} 
	}


	/**
	 * Helper method to retrieve a particular configuration property (or null if not found).
	 * @param logger The compiler's logger
	 * @param context The compiler's context
	 * @param configurationProperty The configurationProperty to retrieve
	 * @return
	 */
	ConfigurationProperty getConfigurationProperty(TreeLogger logger, GeneratorContext context, String configurationProperty) {

		// Get the PropertyOracle
		if(properties==null) properties = context.getPropertyOracle();
	
		// try and get the property requested; return null if unsuccessful.
		try {
			return properties.getConfigurationProperty(configurationProperty);
		} catch (BadPropertyValueException e) {
			return null;
		}
	}
	
	/**
	 * The Oracle of properties in the current compiler context.
	 */
	PropertyOracle properties;

	/**
	 * Helper method to create a SourceWriter object that will be used to write the new type to
	 * @param logger The compiler's logger
	 * @param context The compiler's current context
	 * @param packageName The package name for the new type
	 * @param className The class name for the new type
	 * @param superclassName The class name the type extends
	 * @param imports Zero or more imports that the new class needs to import beyond those in the super class
	 * @return
	 */
	SourceWriter getSourceWriter(TreeLogger logger, GeneratorContext context,
			String packageName, String className, String superclassName,
			String... imports) {

		// Create a PrintWriter object
		PrintWriter printWriter = context.tryCreate(logger, packageName,
				className);

		// If the PrintWriter object is null then the assumption is that the class has
		// already been created before, so return null to indicate that.
		if (printWriter == null)
			return null;

		// Create a factory that we will use to create the basis of our new class
		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(
				packageName, className);

		// Add any new imports that we require 
		for (String imprt : imports)
			composerFactory.addImport(imprt);

		// Set the super class name of the new class
		composerFactory.setSuperclass(superclassName);

		// Create and return the SourceWrite from the factory
		return composerFactory.createSourceWriter(context, printWriter);
	}

	
	/**
	 * Helper method to write the code that will instantiate the Logger
	 * in the generated class.
	 * @param logger The compiler's logger (note this is different to the logger the executing code will use)
	 * @param writer The SourceWriter that holds our generated class
	 */
	public void writeLoggerCode(TreeLogger logger, SourceWriter writer) {
		// Start a JavaDoc comment
		writer.beginJavaDocComment();
		// Write our JavaDoc comment
		writer.println("Logger that is used in class");
		// End a JavaDoc comment
		writer.endJavaDocComment();
		// Write a variable into our generated class to get the executing logger called Widget
		writer.println("Logger "+ LOGGER_VAR_NAME +" = Logger.getLogger(\"Widget\");");
	}

	/**
	 * Helper method to write the generated classes methods - these are simply overridden source
	 * class methods that use the execution logger to write something to the logger and then 
	 * calls the super classes original method.
	 * @param logger The compiler's logger (not the execution logger)
	 * @param writer The SourceWriter where the generated class is being created
	 * @param typeName The typeName being generated (so we can write it to the executing logger)
	 * @param method The method being generated (so we can write it to the executing logger and generate the call to the super classes method)
	 */
	public void writeUpdatedAddMethod(TreeLogger logger, SourceWriter writer,
			String typeName, JMethod method) {
		// If the method is private we won't bother logging (and so no need to generate an overridden method)
		if (method.isPrivate())
			return;
		
		logger.log(Type.INFO, "Overriding "+method.getReadableDeclaration());

		// Writing a JavaDoc comment
		writer.beginJavaDocComment();
		writer.println("Overides Widget's " + method.getName() + " method");
		writer.endJavaDocComment();

		// Let's declare our method as overiding the super class' method.
		writer.println("@Override");
		// Write down the method declaration in the generated class
		writer.println(method.getReadableDeclaration());
		// Add the left bracket and then indent the cursor position for nice reading.
		writer.println("{");
		writer.indent();
		// Add the code needed to write to the execution logger that we're running a particular method 
		// in a particular class.
		writer.println(LOGGER_VAR_NAME+".log(Level.INFO, \"" + typeName + ":"
				+ method.getName() + "\");");
		// Now we'll create the call to the super class' method.
		// We need to construct it using the word "super" plus all the parameter names in the original method call
		StringBuffer superCall = new StringBuffer();
		superCall.append("super." + method.getName());
		superCall.append(determineParams(method.getParameters()));
		// Almost there - just need to determine if the method is expecting to return something, and
		// if so, insert a "return" at the start of the call to the super class' method, otherwise we
		// don't need to.
		try {
			if (!method.getReturnType().isPrimitive()
					.equals(JPrimitiveType.VOID))
				superCall.insert(0, "return ");
		} catch (Exception e) {
			superCall.insert(0, "return ");
		}
		writer.println(superCall.toString());
		
		
		// OK, the method is almost finished, last thing to do is outdent our cursor and add the necessary
		// right squigly bracket to close the method definition.
		writer.outdent();
		writer.println("}");
		// Nothing for us to return as we've created the necessary method in the SourceWriter as we have 
		// been going.
	}

	/**
	 * Helper method to write the generated classes constructors - these are simply overridden source
	 * class methods that use the execution logger to write something to the logger and then 
	 * calls the super classes original method.
	 * @param logger The compiler's logger (not the execution logger)
	 * @param writer The SourceWriter where the generated class is being created
	 * @param constructor The constructor being manipulated
	 * @param simpleClassName The super classes name
	 * @param proxyName The proxy classes name
	 */
	public void writeUpdatedConstructor(TreeLogger logger, SourceWriter writer,
			JConstructor constructor, String simpleClassName, String proxyName) {
		
		// Write a JavaDoc for the constructor
		writer.beginJavaDocComment();
		writer.println("Overides Widget's " + constructor.getName()
				+ " constructor");
		writer.endJavaDocComment();
		
		logger.log(Type.INFO, "Overriding constructor "+ constructor.getReadableDeclaration());
		
		// Get the readable declaration of the source class constructor....
		String s = constructor.getReadableDeclaration();
		// ..and replace the original class name with the proxy class name (so it becomes 
		// a constructor of our generated class)
		s = s.replace(simpleClassName, proxyName);
		// Write the new constructor name to the SourceWriter, add the opening squigly bracket, and
		// indent the cursor so we write a nice pretty class.
		writer.println(s);
		writer.println("{");
		writer.indent();
		
		// Call the super classes constructor with the parameters from the constructor
		writer.println("super" + determineParams(constructor.getParameters()));
		
		// Write some text that will call the execution logger suring execution indicating that the
		// constructor has been executed.
		writer.println(LOGGER_VAR_NAME+".log(Level.INFO, \"New " + proxyName
				+ " created\");");

		// Close off the constructor by outdenting the cursor and printing the right squigly bracket.
		writer.outdent();
		writer.println("}");
		// No need to return anything as the constructor has been written to the SourceWriter 
		// as we progressed through this method.
	}
}
