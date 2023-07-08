
package org.tempuri;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DocumentServiceSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DocumentServiceSoap {


    /**
     * 
     * @param documentName
     * @return
     *     returns byte[]
     */
    @WebMethod(operationName = "DownLoadDocument", action = "http://tempuri.org/DownLoadDocument")
    @WebResult(name = "DownLoadDocumentResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "DownLoadDocument", targetNamespace = "http://tempuri.org/", className = "org.tempuri.DownLoadDocument")
    @ResponseWrapper(localName = "DownLoadDocumentResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.DownLoadDocumentResponse")
    public byte[] downLoadDocument(
        @WebParam(name = "documentName", targetNamespace = "http://tempuri.org/")
        String documentName);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HelloWord", action = "http://tempuri.org/HelloWord")
    @WebResult(name = "HelloWordResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "HelloWord", targetNamespace = "http://tempuri.org/", className = "org.tempuri.HelloWord")
    @ResponseWrapper(localName = "HelloWordResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.HelloWordResponse")
    public String helloWord();

}
