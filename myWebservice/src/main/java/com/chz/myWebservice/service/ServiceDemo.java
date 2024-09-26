package com.chz.myWebservice.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "ServiceDemo",
        targetNamespace = "http://www.baidu.com")
public interface ServiceDemo {
 
    @WebMethod
    String emrService(@WebParam String data);

}
 
