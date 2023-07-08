
package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DocumentService", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://192.168.20.78:53535/WebServices/DocumentService.asmx?WSDL")
public class DocumentService
    extends Service
{

    private final static URL DOCUMENTSERVICE_WSDL_LOCATION;
    private final static WebServiceException DOCUMENTSERVICE_EXCEPTION;
    private final static QName DOCUMENTSERVICE_QNAME = new QName("http://tempuri.org/", "DocumentService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.20.78:53535/WebServices/DocumentService.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DOCUMENTSERVICE_WSDL_LOCATION = url;
        DOCUMENTSERVICE_EXCEPTION = e;
    }

    public DocumentService() {
        super(__getWsdlLocation(), DOCUMENTSERVICE_QNAME);
    }

    public DocumentService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DOCUMENTSERVICE_QNAME, features);
    }

    public DocumentService(URL wsdlLocation) {
        super(wsdlLocation, DOCUMENTSERVICE_QNAME);
    }

    public DocumentService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DOCUMENTSERVICE_QNAME, features);
    }

    public DocumentService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DocumentService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DocumentServiceSoap
     */
    @WebEndpoint(name = "DocumentServiceSoap")
    public DocumentServiceSoap getDocumentServiceSoap() {
        return super.getPort(new QName("http://tempuri.org/", "DocumentServiceSoap"), DocumentServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DocumentServiceSoap
     */
    @WebEndpoint(name = "DocumentServiceSoap")
    public DocumentServiceSoap getDocumentServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "DocumentServiceSoap"), DocumentServiceSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns DocumentServiceSoap
     */
    @WebEndpoint(name = "DocumentServiceSoap12")
    public DocumentServiceSoap getDocumentServiceSoap12() {
        return super.getPort(new QName("http://tempuri.org/", "DocumentServiceSoap12"), DocumentServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DocumentServiceSoap
     */
    @WebEndpoint(name = "DocumentServiceSoap12")
    public DocumentServiceSoap getDocumentServiceSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "DocumentServiceSoap12"), DocumentServiceSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DOCUMENTSERVICE_EXCEPTION!= null) {
            throw DOCUMENTSERVICE_EXCEPTION;
        }
        return DOCUMENTSERVICE_WSDL_LOCATION;
    }

}
