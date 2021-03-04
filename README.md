# Annotation-processor for JAVA SCORE

## InterfaceScoreProcessor

### Gradle
Add dependency to build.gradle
````
dependencies {
    compileOnly 'foundation.icon:javaee-api:0.8.7'
    
    compileOnly 'foundation.icon:javaee-annotation-processor:0.1.0-SNAPSHOT'
    annotationProcessor 'foundation.icon:javaee-annotation-processor:0.1.0-SNAPSHOT'
}
````

### Usage
Annotate `@InterfaceScore` to interface. and annotate `@score.annotation.Payable` to payable method.
````java
@InterfaceScore
public interface Xxx {
    void externalMethod(String param);
    @score.annotation.Payable void payableMethod(String param);
}
````

When java compile, implement class will be generated which has `@InterfaceScore.suffix()`.
Then you can use generated class as follows.
````java
import score.Address;
import score.annotation.External;

public class Score {
    @External
    public void intercall(Address address, String param) {
        XxxImpl xxx = new XxxImpl(address);
        // intercall method
        xxx.externalMethod(param);
        // intercall payable method
        xxx._payable(1L).payableMethod(param);
    }
}
````

### JsonObjectProcessor

### Gradle
Add dependency to build.gradle
````
dependencies {
    compileOnly 'foundation.icon:javaee-api:0.8.7'
    implementation 'com.github.sink772:javaee-scorex:0.5.1'
    implementation 'com.github.sink772:minimal-json:0.9.6'
    
    compileOnly 'foundation.icon:javaee-annotation-processor:0.1.0-SNAPSHOT'
    annotationProcessor 'foundation.icon:javaee-annotation-processor:0.1.0-SNAPSHOT'
}
````

### Usage
Annotate `@JsonObject` to class. also you can annotate `@JsonProperty` to field.
````java
@JsonObject
public class Xxx {
    private String value;
    
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
````

When java compile, JSON convertable class will be generated which has `@JsonObject.suffix()`.  
Then you can use generated class as follows.
````java
import score.annotation.External;
import com.eclipsesource.json.JsonObject;

public class Score {
    @External(readonly = true)
    public String json(String jsonString) {
        // parse
        Xxx xxx = XxxJson.parse(jsonString);
        // toJsonObject
        JsonObject jsonObject = XxxJson.toJsonObject(xxx);
        // toJsonString 
        return jsonObject.toString();
    }
}
````

### ScoreDataObjectProcessor

### Gradle
Add dependency to build.gradle
````
dependencies {
    compileOnly 'foundation.icon:javaee-api:0.8.7'
    implementation 'com.github.sink772:javaee-scorex:0.5.1'
    
    compileOnly 'foundation.icon:javaee-annotation-processor:0.1.0-SNAPSHOT'
    annotationProcessor 'foundation.icon:javaee-annotation-processor:0.1.0-SNAPSHOT'
}
````

### Usage
Annotate `@ScoreDataObject` to class. also you can annotate `@ScoreDataProperty` to field.
````java
@ScoreDataObject
public class Xxx {
    private String value;
    
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
````

When java compile, serializable class will be generated which has `@ScoreDataObject.suffix()`.
Then you can use generated class as follows.
````java
import score.annotation.External;

public class Score {
    final VarDB<XxxSdo> db = Context.newVarDB("db", XxxSdo.class);
    
    @External
    public void set(Xxx xxx) {
        db.set(new XxxSdo(xxx));
    }

    @External(readonly = true)
    public Xxx get() {
        return db.get();
    }
}
````
