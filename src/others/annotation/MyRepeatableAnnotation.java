package others.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Repeatable 注解了 MyRepeatableAnnotation。
 * 而 @Repeatable 后面括号中的类相当于一个容器注解。容器注解是用来存放其它注解的地方。它本身也是一个注解。
 * 容器注解里面必须要有一个 value 的属性，属性类型是一个被 @Repeatable 注解过的注解数组，注意它是数组。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Persons.class)
public @interface MyRepeatableAnnotation {
	public String role() default "";
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Persons {
	public MyRepeatableAnnotation[] value();
}
