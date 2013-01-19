package com.theothermattm.spring.utils;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@link ApplicationContextInitializer} which can be used to activate different
 * Spring environment profiles based on a system property instead of the default
 * "spring.profiles.active" property.
 * 
 * This can be useful if you don't have much control over your deployed
 * environments and want to use existing, standardized system properties to
 * activate spring profiles.
 * 
 * For example, if you already have a standard system property defined on all
 * your deployed environments named <i>com.mycompany.environment</i> which gets
 * set to values like "test" / "staging" / "production"
 * 
 * You can use this approach to activate Spring environment profiles based on
 * those existing properties instead of having to define <em>another</em> system
 * property.
 * 
 * <h3>To use</h3>
 * 
 * First set the correct system property name you want to use in this class in
 * the {@value ENVIRONMENT_SYSTEM_PROPERTY_NAME} constant
 * 
 * Then, put an entry in your web.xml like the following:
 * 
 * <pre>
 * 	<context-param>
 * 	  <description>Will load up spring environment profiles based on specified system properties instead
 * 	      of the default "spring.profiles.active" system property. </description>
 * 	  <param-name>contextInitializerClasses</param-name>
 * 	  <param-value>com.theothermattm.spring.utils.SystemPropertyProfileContextInitializer</param-value>
 * 	</context-param>
 * </pre>
 * 
 * And you should be good to go.
 * 
 * @author matt m (http://theothermattm.com)
 */
public class SystemPropertyProfileContextInitializer implements
		ApplicationContextInitializer<ConfigurableApplicationContext> {

	/**
	 * The name of the system property you want to use to activate Spring
	 * profiles.
	 * 
	 * You can swap this to whatever you need. It would be really nice to
	 * specify this in the web.xml as well, however, there's no easy way to do
	 * that without registering ANOTHER ServletListener (I believe).
	 */
	public static final String ENVIRONMENT_SYSTEM_PROPERTY_NAME = "com.theothermattm.environment";

	public void initialize(ConfigurableApplicationContext configurableAppContext) {

		String profiles = System.getProperty(ENVIRONMENT_SYSTEM_PROPERTY_NAME);

		if (profiles == null || "".equals(profiles)) {
			// perhaps eventually plug in a logging framework here
			System.out.println(this.getClass().getName()
					+ ":  No value exists for "
					+ ENVIRONMENT_SYSTEM_PROPERTY_NAME
					+ ", not activating any Spring profiles");
		} else {

			System.out
					.println("Activating Spring environment profiles from system property:{"
							+ ENVIRONMENT_SYSTEM_PROPERTY_NAME + "}");

			configurableAppContext.getEnvironment().setActiveProfiles(profiles);

			System.out.println(this.getClass().getName()
					+ ": Activated Spring environment profiles: {" + profiles
					+ "}");
		}

	}
}
