package com.chz.myCoap.demo1;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

@Slf4j
public class MyDemo1CoapServerTest {

	private static String name = MyDemo1CoapServerTest.class.getSimpleName();
 
	public static void main(String[] args) {
		CoapServer server = new CoapServer();// 默认端口5683

		server.add(new CoapResource("get_hello") {
			@Override
			public void handleGET(CoapExchange exchange) {
				log.info("{}::handleGET: get_hello", name);
				exchange.respond(ResponseCode.CONTENT, "get_hello: Hello this is MyDemo1CoapServerTest");
			}
		});

		server.add(new CoapResource("post_hello") {
			@Override
			public void handlePOST(CoapExchange exchange) {
				String requestPayload = new String(exchange.getRequestPayload());
				log.info("{}::handlePOST: post_hello, content={}", name, requestPayload);
				exchange.respond(ResponseCode.CONTENT, "post_hello: Hello this is MyDemo1CoapServerTest");
			}
		});

		server.add(new CoapResource("time") { // 创建一个资源为time 请求格式为 主机：端口\time
			@Override
			public void handleGET(CoapExchange exchange) {
				log.info("{}::handleGET: time", name);
				Date date = new Date();
				exchange.respond(ResponseCode.CONTENT, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			}
		});

		server.start();
	}
}
