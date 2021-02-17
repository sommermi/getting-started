package health;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Liveness;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

@ApplicationScoped
public class HealthCheckProducer {

    private static final Logger LOG = Logger.getLogger(HealthCheckProducer.class);

    @Inject
    @ConfigProperty(name = "health.enabled", defaultValue = "true")
    boolean enabled;

    @Produces
    @Liveness
    CustomHealthCheck getHealthCheck() {
        LOG.info("producer");
        return enabled ? retrieveHealthCheck() : null;
    }

    private CustomHealthCheck retrieveHealthCheck() {
        return CDI.current().select(CustomHealthCheck.class).get();
    }

}
