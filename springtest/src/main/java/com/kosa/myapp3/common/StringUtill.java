package com.kosa.myapp3.common;

public class StringUtill {
	// ������ private �� ���� ��ü ���� ���ϰ�
	private StringUtill() {}
	public static String nullToValue(Object obj, String value) {
		
		// ���޵� ��ü�� null �� ��� �ι�° ���ڰ� ������ �� ��ȯ
		if(obj == null)
			return value;
		// null �� �ƴ� ��� obj�� toString() �� �̿��� String �� ����
		else
			return obj.toString();
		
		// String ��ü���� ��밡��
		// ��ü �ȸ���� �Լ� ����� �����ϰ� �ϱ� ���ؼ� static Ű���� ����
	}
}
