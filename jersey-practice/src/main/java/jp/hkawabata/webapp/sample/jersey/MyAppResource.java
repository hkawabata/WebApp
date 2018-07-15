package jp.hkawabata.webapp.sample.jersey;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MyAppResource {
    @Inject
    private IZooKeeperWatcher zkWatcher;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String rootPage() {
        return "<html><body><h1>Root Page</h1></body></html>";
    }

    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public String xml() {
        return "<tag>sample xml</tag>";
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String json() {
        return "{\"num\": 100}";
    }

    @GET
    @Path("/zk")
    @Produces(MediaType.TEXT_PLAIN)
    public String zk() {
        return zkWatcher.s();
    }
}
