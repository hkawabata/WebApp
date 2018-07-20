package jp.hkawabata.webapp.sample.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/badstatus")
public class AnyHttpStatusResource {
    /**
     * 任意の HTTP ステータスでレスポンスを返す
     * 存在しないステータスは Bad Request として返す
     *
     * @param code 返してほしい HTTP ステータス
     * @return
     */
    @GET
    @Path("code/{code}")
    public Response a(@PathParam("code") String code) {
        try{
            Response.Status status = Response.Status.fromStatusCode(Integer.valueOf(code));
            return Response.status(status.getStatusCode()).entity(status.getStatusCode() + " " + status.getReasonPhrase()).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Status Code: " + code).build();
        } catch (NullPointerException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Status Code: " + code).build();
        }
    }
}
