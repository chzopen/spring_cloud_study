package com.chz.myCoap.demo1;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;

@Slf4j
public class MyDemo1CoapClientTest {

	private static String name = MyDemo1CoapClientTest.class.getSimpleName();

	public static void main(String[] args) throws URISyntaxException, IOException {

		URI uri = new URI("coap://localhost:5683/get_hello");
		CoapClient client = new CoapClient(uri);
		CoapResponse response = client.get();
		if (response != null) {
			log.info("code: {}", response.getCode());
			log.info("options: {}", response.getOptions());
			log.info("responseText: {}", response.getResponseText());
		}

		URI uri2 = new URI("coap://localhost:5683/post_hello");
		CoapClient client2 = new CoapClient(uri2);
		String payload = "I am "+name;
		CoapResponse response2 = client2.post(payload, 0);
		if (response2 != null) {
			log.info("code: {}", response2.getCode());
			log.info("options: {}", response2.getOptions());
			log.info("responseText: {}", response2.getResponseText());
		}

		URI uri3 = new URI("coap://localhost:5683/time");
		CoapClient client3 = new CoapClient(uri3);
		CoapResponse response3 = client3.get();
		if (response3 != null) {
			log.info("code: {}", response3.getCode());
			log.info("options: {}", response3.getOptions());
			log.info("responseText: {}", response3.getResponseText());
		}
 
	}
 
}
