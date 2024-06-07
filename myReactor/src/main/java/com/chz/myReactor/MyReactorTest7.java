package com.chz.myReactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Iterator;

public class MyReactorTest7 {

	public static void main(String[] args) {
		System.out.println("################################");
		testMono();
		System.out.println("################################");
		testFlux();
		System.out.println("################################");
	}

	private static void testMono()
	{
		Mono.create((sink) -> {
			sink.success(1);
		})
		.subscribe((a)->{
			System.out.println(a);
		});
	}

	private static void testFlux()
	{
		Iterator<Integer> it = Arrays.asList(1,2,3).iterator();
		Flux<Integer> iteratorFlux = Flux.create(sink -> {
			while (it.hasNext()) {
				sink.next(it.next());
			}
			sink.complete();
		});
		iteratorFlux.subscribe(System.out::println);
	}

}