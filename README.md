# recess-maven-plugin
Compile and compress less/css files with [recess](https://github.com/twitter/recess).

# Build

    mvn clean install

# Usage

Add the following snippet inside build -> plugins:

```xml
<plugin>
    <groupId>org.mule.tools.javascript</groupId>
    <artifactId>recess-maven-plugin</artifactId>
    <version>1.0-SNAPSHOT</version>
    <executions>
        <execution>
            <phase>generate-sources</phase>
            <goals>
                <goal>recess</goal>
            </goals>
            <configuration>
                <compress>true</compress>
                <outputFile>${project.build.directory}/${project.build.finalName}/result.css</outputFile>
            </configuration>
        </execution>
    </executions>
</plugin>
```

# Authors
Alberto Pose (@thepose)

# License
Copyright 2012 MuleSoft, Inc.

Licensed under the Common Public Attribution License (CPAL), Version 1.0.
    
### Happy hacking!
