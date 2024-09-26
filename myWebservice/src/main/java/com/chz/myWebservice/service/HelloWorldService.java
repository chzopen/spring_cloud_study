package com.chz.myWebservice.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HelloWorldService {

    @WebMethod
    String sayHello(String name);

}