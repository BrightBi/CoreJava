package others.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元标签有 @Retention、@Documented、@Target、@Inherited、@Repeatable 5 种。
 * 
 * @Retention 解释说明了这个注解的的存活时间。取值如下：
 * RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
 * RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
 * RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
 * 
 * @Documented 作用是能够将注解中的元素包含到 Javadoc 中去。
 * 
 * @Target 指定了注解运用的地方。取值如下：
 * ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 * ElementType.CONSTRUCTOR 可以给构造方法进行注解
 * ElementType.FIELD 可以给属性进行注解
 * ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 * ElementType.METHOD 可以给方法进行注解
 * ElementType.PACKAGE 可以给一个包进行注解
 * ElementType.PARAMETER 可以给一个方法内的参数进行注解
 * ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
 * 
 * @Inherited 如果一个超类被 @Inherited 注解过的注解进行注解过的话，那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。
 * 
 * @Inherited
 * @Retention(RetentionPolicy.RUNTIME)
 * @interface Test {}
 * 
 * @Test
 * public class A {}
 * 
 * public class B extends A {}
 * 注解 Test 被 @Inherited 修饰，之后类 A 被 Test 注解，类 B 继承 A,类 B 也拥有 Test 这个注解。
 * 
 * @Repeatable 注解的值可以同时取多个
 * 
 * 注解只有 value 属性。所以可以这样应用 @Check("hi")，等价于 @Check(value="hi")。
 * public @interface Check {
 *     String value();
 * }
 * 
 * 一个注解没有任何属性，比如 public @interface Perform {} 可以省略括号 @Perform
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
	public int id();
	public String code();
	public String msg() default "Hi"; // 指定默认值
}
