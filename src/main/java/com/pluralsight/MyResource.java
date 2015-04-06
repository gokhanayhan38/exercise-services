package com.pluralsight;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")  // BU PATH BİZİM SERVİSİMİZE ERİŞME YOLUDUR.url in sonuna eklidir.
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET // GET METODU REQUEST İCİN KULLANILIR,
    @Produces(MediaType.TEXT_PLAIN) //HANGİ TİPDE VERİ GONDERİLECEĞİ PRODUCES ANNOTATİONU İLE BELİRLENİR. icerik bununla belirleniyor
    public String getIt() {
        return "Got it!";
    }
}
