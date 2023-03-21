package com.kosa.mycompany;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

	@Override
	public void displayName() {
		System.out.println("È«±æµ¿");
	}

	@Override
	public void displayNumber() {
		int i, sum = 0;
		for (i = 1; i <= 10; i++) {
			sum += i;
		}
		System.out.println(String.format("ÇÕ°è: %d", sum));

	}

	@Override
	public Object displayNumber(int limit) {
		int i, sum = 0;
		for (i = 1; i <= 100; i++) {
			sum += i;
		}
		return sum;
	}

	@Override
	public void guguDan(int dan) {
		for(int i=1; i<=9; i++) {
			System.out.println(String.format("%d X %d = %d", dan, i, dan*i));
		}
	}

}
