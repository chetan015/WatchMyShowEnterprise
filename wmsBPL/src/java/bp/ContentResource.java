/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bp;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import services.Content;

/**
 * REST Web Service
 *
 * @author chetansurana
 */
@Path("content")
public class ContentResource {

    @Context
    private UriInfo context;

    /**
     * Retrieves representation of an instance of bp.ContentResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Content> getXml() {
        ContentFacadeREST_JerseyClient client1 = new 
            ContentFacadeREST_JerseyClient();
        GenericType<List<Content>> gType = new GenericType<List<Content>>(){};
        List<Content> content = (List<Content>) client1.findAll_XML(gType);
        return content;
    }

    /**
     * PUT method for updating or creating an instance of ContentResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    /**
     * Creates a new instance of ContentResource
     */
    public ContentResource() {
    }

    
    static class ContentFacadeREST_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = 
                "http://localhost:8080/wmsBSL/webresources";

        public ContentFacadeREST_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("services.content");
        }
        public <T> T findAll_XML(GenericType<T> responseType) 
                throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.
                    MediaType.APPLICATION_XML).get(responseType);
        }
        public String countREST() throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path("count");
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
        }

        public void edit_XML(Object requestEntity, String id) throws ClientErrorException {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
        }

        public void edit_JSON(Object requestEntity, String id) throws ClientErrorException {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        public <T> T find_XML(Class<T> responseType, String id) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        }

        public <T> T find_JSON(Class<T> responseType, String id) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T findRange_XML(Class<T> responseType, String from, String to) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        }

        public <T> T findRange_JSON(Class<T> responseType, String from, String to) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void create_XML(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
        }

        public void create_JSON(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

       

        public <T> T findAll_JSON(Class<T> responseType) throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void remove(String id) throws ClientErrorException {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
        }

        public void close() {
            client.close();
        }
    }
    
}
