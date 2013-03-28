conf-doclet [![Build Status](https://travis-ci.org/mircea-pop/conf-doclet.png?branch=v0.2)](https://travis-ci.org/mircea-pop/conf-doclet)
===========

Java Doclet for extracting configuration properties and their default values from java code.

Usage
======

<h4>Configuration</h4>
Add the next code to the <code>build.gradle</code> file:
```groovy

configurations {
    confDoclet
}

dependencies {
    confDoclet "com.epages.doclets:conf-doclet:0.2"
}

task confDoc(type: Javadoc) {
    classpath = rootProject.configurations.confDoclet
    classpath += files(subprojects.collect { project ->
            project.sourceSets.main.compileClasspath
            })
    options.showAll()
    destinationDir = new File(buildDir, 'docs/confdoc')
    options.docletpath = configurations.confDoclet.files.asType(List)
    options.doclet = "com.epages.doclets.ConfDoclet"
    options.addStringOption("outputFile", "sample.conf")
    options.addBooleanOption("excludePropWithUndefinedDefaults", true)
}
```
<h5>Additional Options</h5>
Beside the javadoc options, there are some other additional ones, specific to this Doclet:

* <b>outputFile</b> - the name of the output file
* <b>excludePropWithUndefinedDefaults</b> - the detected properties which have no default values are added to the generated file by default. If this property is set, these properties will be excluded from the generated file.

<h4>Example</h4>

To actually extract the configuration properties from the java source code, you have to follow some conventions
in your configuration classes: <br>
* the <code>@ConfigurationSection</code> javadoc taglet in the class comment
* the defined properties (can even be PRIVATE) with their corresponding DEFAULT values (propertyName + "_DEFAULT").<br>
* the defined properties need to be <b>STATIC</b> and <b>FINAL</b>

```java
   /**
     * section comment
     * @ConfigurationSection PropertySection
     */
    public class ConfigClass {
        /**
    	 * comment 1
    	 */
    	private static final String PROPERTY_1 = "property1";
    	/**
    	 * comment 2
    	 */
    	private static final String PROPERTY_2 = "property2";
    	
    	
    	private static final String PROPERTY1_DEFAULT = "default value of property1";
    	private static final String PROPERTY2_DEFAULT = "default value of property2";
    }
```
<h4>Output</h4>

With all of the above configured, the output will be a <code>sample.conf</code> file containing
```java
;;section comment
[PropertySection]
;;comment 1
;PropertySection.property1 = default value of property1

;;comment 2
;PropertySection.property2 = default value of property2
```

The number of classes with the <code>@ConfigurationSection</code> taglet, will be reflected in the number of 
<code>[Section]</code>s in the output file.

<h4>Gradle Command Line</h4>
```groovy
 > gradle confDoc
```

<h4>Logging</h4>

The logging is integrated in javadoc logging. That means it can be controlled throught the command line options: quiet or verbose.
In Gradle, Javadoc task is QUIET by default. For verbose, you can call <code>verbose()</code> on the options object:
```groovy

task confDoc(type: Javadoc) {
    //...
    options.verbose()
    //...
}
```

<h4>Limitations</h4>

The default values can only be primitives. No objects supported. If an object is encountered for a default value, the default value is set to an empty string and a warning message will be printed.
