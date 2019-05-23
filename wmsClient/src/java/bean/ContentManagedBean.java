/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import services.Content;

/**
 *
 * @author chetansurana
 */
@Named(value = "contentManagedBean")
@RequestScoped
public class ContentManagedBean {

    private List<Content> contents;
    private List<Content> subscribedContents;
    private Integer id;
    private String name;
    private String genre;
    private String type;
    private Integer rating;
    private String subscriptionListInput;
    private long responseTime;

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    

    public String getSubscriptionListInput() {
        return subscriptionListInput;
    }

    public void setSubscriptionListInput(String subscriptionListInput) {
        this.subscriptionListInput = subscriptionListInput;
    }

    /**
     * Creates a new instance of ContentManagedBean
     */
    public ContentManagedBean() {
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void details() {
        long startTime = System.nanoTime();
        ContentResource_JerseyClient lcClient = 
                new ContentResource_JerseyClient();
        GenericType<List<Content>> gType = 
                new GenericType<List<Content>>() {
        };
        List<Content> persons = (List<Content>) lcClient.getXml(gType);
        long endTime = System.nanoTime();
        responseTime = (endTime - startTime)/1000000;
        List<Content> abc = new ArrayList<Content>();
        for (Content p : persons) {
            abc.add(p);
        }
        contents = abc;
    }

    public String subscribe() {
        String ids[] = subscriptionListInput.split(",");
        List<Content> def = new ArrayList<Content>();
        ContentResource_JerseyClient lcClient = 
                new ContentResource_JerseyClient();
        GenericType<List<Content>> gType = new GenericType<List<Content>>() {
        };
        List<Content> persons = (List<Content>) lcClient.getXml(gType);
        List<Content> abc = new ArrayList<Content>();
        for (Content p : persons) {
            abc.add(p);
        }
        contents = abc;
        for (Content c : contents) {
            String id = Integer.toString(c.getId());
            boolean found = false;
            for (String element : ids) {
                if (element.equals(id)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                def.add(c);
            }
        }
        subscribedContents = def;
        return "display";
    }

    public List<Content> getSubscribedContents() {
        return subscribedContents;
    }

    public void setSubscribedContents(List<Content> subscribedContents) {
        this.subscribedContents = subscribedContents;
    }

//    public void getContentByName(){
//        ContentResource_JerseyClient lcClient = new ContentResource_JerseyClient();
//        GenericType<List<Content>> gType = new GenericType<List<Content>>() {};
//        List<Content> persons = (List<Content>) lcClient.getXmlContentByName(gType,name);
//        List<Content> abc = new ArrayList<Content>();
//        for (Content p : persons) {
//            abc.add(p);
//        }
//        contents = abc;
//    }
    static class ContentResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI =
                "http://localhost:8080/wmsBPL/webresources";

        public ContentResource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("content");
        }

        public <T> T getXml(GenericType<T> responseType) 
                throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(
                javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        }

        public void putXml(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).
                    put(javax.ws.rs.client.Entity.entity(requestEntity,
                            javax.ws.rs.core.MediaType.APPLICATION_XML));
        }

        public void close() {
            client.close();
        }
    }

}
