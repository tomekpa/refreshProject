//package rest.cxfclient;
//
//import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
//import org.apache.cxf.jaxrs.client.WebClient;
//import org.junit.Test;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//
///**
// * Created by tomasz.pasieka on 08.02.2019.
// */
//public class CXFClientTest {
//
//    @Test
//    void fdfsdf() {
//
//        MyClientResource resource = JAXRSClientFactory.create("www.google.com", MyClientResource.class);
//        WebClient.getConfig(resource).getOutInterceptors().add(resource);
//;
//
//        resource
//
//
//    }
//
//    @Path("/")
//    static class MyClientResource {
//
//        @GET
//        @Path("/{prescriptionCode}")
//        @Produces({ "application/json" })
//        public String readPrescription() ;
//
//
//
//    }
//}
