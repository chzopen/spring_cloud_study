package com.chz.myReactor;

import reactor.core.publisher.Mono;

public class MyReactorTest {

	public static void main(String[] args)
	{
		System.out.println("#################################################");
		Mono.justOrEmpty(1).subscribe((a)->{
			System.out.println(a);
		});

		System.out.println("#################################################");
		Mono.justOrEmpty(null).subscribe((a)->{
			System.out.println(a);
		});

		System.out.println("#################################################");
	}

}