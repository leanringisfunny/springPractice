package hello.core.scan.filter;

import java.lang.annotation.*;

@Target({ElementType.TYPE})//class레벨에 붙을 것이다.
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface MyIncludeComponent{

}
