# LIBRARY PROJECT

## Project Description
The library project allows you to registrate books, users and to create loans between them. Besides, we support reports plugins, so you can add your plugins in this project.

## Requirements
- **Java Development Kit (JDK)** 23 or most current.
- **Maven Apache**.

## Compilation and execution
1. Go to the microkernel directory;
2. Install all archives using: "mvn package"
3. Compile all archives using: "mvn install";
4. Execute the program using: "mvn exec:java -pl app".

## New plugin creation instructions:

1. Create your plugin folder in "plugins"
2. Add you new plugin submodule in main pom.xml:

```
    <modules>
        <module>interfaces</module>
        <module>app</module>
        <module>plugins/myplugin</module>
        ADD IT HERE
    </modules>
```
    
3. Create your new plugin's pom.xml (check myplugin/pom.xml)
4. Remember to use plugin's package conventions:
```
    br/edu/ifba/inf008/plugins/<YourPluginNameInCamelCase>.java
```
    
5. Run "mvn install" and "mvn exec:java -pl app"

## Support
- Java Documentation;
- Sandro Andrade ([LinkedIn](https://www.linkedin.com/in/sandroandrade/)).

## Project Creator
#### Júlio Brito:
Instagram: [@dev.julinho](https://www.instagram.com/dev.julinho) \
LinkedIn: [juliobr1](https://www.linkedin.com/in/juliobr1/)