package com.kosa.myapp3.common;

public class StringUtill {
	// 생성자 private 로 만들어서 객체 생성 못하게
	private StringUtill() {}
	public static String nullToValue(Object obj, String value) {
		
		// 전달된 객체가 null 일 경우 두번째 인자가 전달한 값 반환
		if(obj == null)
			return value;
		// null 이 아닐 경우 obj의 toString() 을 이용해 String 값 전달
		else
			return obj.toString();
		
		// String 객체에만 사용가능
		// 객체 안만들고 함수 사용이 가능하게 하기 위해서 static 키워드 붙임
	}
}
