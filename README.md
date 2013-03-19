conf-doclet
===========

Java Doclet for extracting configuration properties from java code

Usage
======
Add the next code to the <code>build.gradle</code> file:
```groovy
configDocletVersion = "0.1"

libraries = [
configDoclet : "com.epages.doclets:config-doclet:${configDocletVersion}"
]

configurations {
    configDoclet
}

dependencies {
    configDoclet libraries.config_doclet
}

task configDoc(type: Javadoc) {
    classpath = rootProject.configurations.configDoclet
    classpath += files(subprojects.collect { project ->
            project.sourceSets.main.compileClasspath
            })
    options.showAll()
    destinationDir = new File(buildDir, 'docs/configdoc')
    options.docletpath = configurations.configDoclet.files.asType(List)
    options.doclet = "com.epages.doclets.ConfDoclet"
}
```
