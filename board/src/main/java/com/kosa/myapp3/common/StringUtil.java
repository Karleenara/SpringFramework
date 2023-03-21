package com.kosa.myapp3.common;

public class StringUtil {
	//생성자 private로 만들어서 객체 생성못하게 
	private StringUtil() {}
	
	public static String nullToValue(Object obj, String value) 
	{
		//전달된 객체가  null 일경우 두번째 인자가 전달한 값을 반환
		if(obj==null)
			return value;
		else //null 이 아닐경우에 obj의 toString() 을 이용해 String 값을 전달
			return obj.toString();
		
		//String 객체에만 사용가능하다. 
		//객체 안만들고 함수 사용이 가능하게 하기위해서  static 키워드를 붙임 
	}
	
	//디비에 ' -> ''   바꿔서 넣어야 한다. 
	//% -> 
	// > -> &gt;
	// < -> &lt;
	
	
}





