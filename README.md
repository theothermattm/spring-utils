spring-utils
============
Some handy extensions or additions to the [Spring Framework](http://www.springsource.org/) that I've found useful in the past.

[SystemPropertyProfileContextInitializer](https://github.com/theothermattm/spring-utils/blob/master/src/main/java/com/theothermattm/spring/utils/SystemPropertyProfileContextInitializer.java)

Spring ApplicationContextInitializer which can be used to activate different
Spring environment profiles based on a system property instead of the default
"spring.profiles.active" property.
 
This can be useful if you don't have much control over your deployed
environments and want to use existing, standardized system properties to
activate spring profiles.

For example, if you already have a standard system property defined on all
your deployed environments named <i>com.mycompany.environment</i> which gets
set to values like "test" / "staging" / "production"

You can use this approach to activate Spring environment profiles based on
those existing properties instead of having to define <em>another</em> system
property.

See javadoc for usage details.
