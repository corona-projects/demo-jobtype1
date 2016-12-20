package cwurbs.demo.docker.resources;

import cwurbs.demo.docker.api.EstimationResult;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/estimate")
@Produces(MediaType.APPLICATION_JSON)
public class EstimationResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public EstimationResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public EstimationResult sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new EstimationResult(counter.incrementAndGet(), value);
    }
}
