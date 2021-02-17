package health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Random;

@Named
@ApplicationScoped
public class CustomHealthCheck implements HealthCheck {

    private static final Logger LOG = Logger.getLogger(HealthCheck.class);


    public CustomHealthCheck() {
    }

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder healthBuilder = HealthCheckResponse.named("Ftp connection health check")
                .withData("key", "value");
        Random random = new Random();
        boolean success = random.nextBoolean();
        LOG.info("test");
        if (success) {
            return healthBuilder.up().build();
        }

        return healthBuilder.withData("reason", "not successful").down().build();
    }

}
