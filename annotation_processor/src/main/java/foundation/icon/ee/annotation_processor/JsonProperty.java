package foundation.icon.ee.annotation_processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface JsonProperty {
    String value() default "";
    String getter() default "";
    String setter() default "";
    boolean direct() default false;
    boolean ignore() default false;
}
