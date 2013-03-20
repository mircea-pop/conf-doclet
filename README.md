conf-doclet
===========

Java Doclet for extracting configuration properties and their default values from java code.

Usage
======

<h4>Configuration</h4>
Add the next code to the <code>build.gradle</code> file:
```groovy
confDocletVersion = "0.1"

libraries = [
confDoclet : "com.epages.doclets:conf-doclet:${confDocletVersion}"
]

configurations {
    conf_doclet
}

dependencies {
    confDoclet libraries.conf_doclet
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
    title = "sample.conf"
}
```

<h4>Example</h4>

To actually extract the configuration properties from the java source code, you have to follow some conventions
in your configuration classes. The configuration class needs to have the <code>@ConfigurationSection</code>
javadoc taglet and the defined properties (can even be PRIVATE) with their corresponding DEFAULT values (propertyName + "_DEFAULT").<br>

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
