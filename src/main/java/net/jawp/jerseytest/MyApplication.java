package net.jawp.jerseytest;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Spring HelloWorld Web Application configuration.
 *
 * @author Jakub Podlesak (jakub.podlesak at oracle.com)
 */
public class MyApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public MyApplication () {
        register(RequestContextFilter.class);
    	register(MultiPartFeature.class);
        register(JerseyTest.class);
    }
}