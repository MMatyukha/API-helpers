package com.dataaccess.webservicesserver;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;


@WebServiceClient(name = "NumberConversion",
                  wsdlLocation = "file:/C:/Users/Asus1/IdeaProjects/API-helpers/hw5/src/main/resources/wsdl/numberconversion.wsdl",
                  targetNamespace = "http://www.dataaccess.com/webservicesserver/")
public class NumberConversion extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.dataaccess.com/webservicesserver/", "NumberConversion");
    public final static QName NumberConversionSoap = new QName("http://www.dataaccess.com/webservicesserver/", "NumberConversionSoap");
    public final static QName NumberConversionSoap12 = new QName("http://www.dataaccess.com/webservicesserver/", "NumberConversionSoap12");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Asus1/IdeaProjects/API-helpers/hw5/src/main/resources/wsdl/numberconversion.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(NumberConversion.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:C:/Users/Asus1/IdeaProjects/API-helpers/hw5/src/main/resources/wsdl/numberconversion.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public NumberConversion(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public NumberConversion(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NumberConversion() {
        super(WSDL_LOCATION, SERVICE);
    }

    public NumberConversion(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public NumberConversion(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public NumberConversion(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }



    @WebEndpoint(name = "NumberConversionSoap")
    public NumberConversionSoapType getNumberConversionSoap() {
        return super.getPort(NumberConversionSoap, NumberConversionSoapType.class);
    }


    @WebEndpoint(name = "NumberConversionSoap")
    public NumberConversionSoapType getNumberConversionSoap(WebServiceFeature... features) {
        return super.getPort(NumberConversionSoap, NumberConversionSoapType.class, features);
    }



    @WebEndpoint(name = "NumberConversionSoap12")
    public NumberConversionSoapType getNumberConversionSoap12() {
        return super.getPort(NumberConversionSoap12, NumberConversionSoapType.class);
    }


    @WebEndpoint(name = "NumberConversionSoap12")
    public NumberConversionSoapType getNumberConversionSoap12(WebServiceFeature... features) {
        return super.getPort(NumberConversionSoap12, NumberConversionSoapType.class, features);
    }

}
