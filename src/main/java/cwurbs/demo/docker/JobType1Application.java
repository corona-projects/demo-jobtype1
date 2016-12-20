package cwurbs.demo.docker;

import cwurbs.demo.docker.health.TemplateHealthCheck;
import cwurbs.demo.docker.resources.EstimationResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class JobType1Application extends Application<JobType1Configuration> {

    public static void main(final String[] args) throws Exception {
        new JobType1Application().run(args);
    }

    @Override
    public String getName() {
        return "JobType1";
    }

    @Override
    public void initialize(final Bootstrap<JobType1Configuration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final JobType1Configuration configuration,
                    final Environment environment) {
        final EstimationResource resource = new EstimationResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}
