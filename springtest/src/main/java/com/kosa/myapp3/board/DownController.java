package com.kosa.myapp3.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosa.myapp3.common.CommonConst;

@Controller
public class DownController {
	@RequestMapping(value = "/down")
	public void download(HttpServletRequest req, HttpServletResponse res, String filename, String path) {

		// path - ��� ������ ���� ������ ���ε��ϸ� ������ �����
		// /upload/board, /upload/gallery , /upload/qna
		// filename : �ٿ�ε��� ���ϸ�

		// ���������� ���� ������ ������ �˷��ִ� �Լ�
//		ServletContext ctx = req.getServletContext();
		String fullPath = CommonConst.UPLOADPATH + "/" + path;
		System.out.println(fullPath);
		System.out.println(filename);
		// test�ϱ� : http://localhost:9000/myapp3/down?path=board&filename=test.txt

		// ������ �ִ� ���� �о Ŭ���̾�Ʈ�� ����
		InputStream in = null; // ����, ���� �о
		OutputStream out = null; // Ŭ���̾�Ʈ���� ����
		File file = null;
		boolean skip = false; // ������ ������ �����ؾ� ���� - true ���� ����

		try {
			// ���� ã�Ƽ�
			file = new File(fullPath, filename);
			// ���� �غ���
			in = new FileInputStream(file);
		} catch (Exception e) {
			// ������ �������� �ʰų� �� ���� ������ ���� �Ұ���
			skip = true;
		}
		if(skip == true)
			return ;
		
		// �ѱ� ó��
		try {
			// ������ ���� �޶� if ó��
			String client = req.getHeader("User-Agent");
			if(client.indexOf("MSIE") != -1)
				filename = new String(filename.getBytes("KSC5601"), "ISO08859_1");
			else
				// ũ��
				filename = new String(filename.getBytes("KSC5601"), "iso-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// ���� �غ�
		res.reset(); // �������� Ŭ���̾�Ʈ�� ���� ������ �������� ��� ����
		res.setContentType("application/octet-stream"); 
		
		// 2�� ���·� �����ٰ� Ŭ���̾�Ʈ���� �˸���
		// attachment; filename="test.txt"
		res.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		res.setHeader("Content-type", "application/octet-stream");
		res.setHeader("Content-length", file.length()+"");
		
		try {
			// ���� ������ ���� ��� ����
			out = res.getOutputStream();
			// ������ ���� Ŭ��� �ѹ��� �޸� Ȯ���� �ȵ� ��� �߶� �о� ������ ��
			// ����� ���� �ϳ� ������ byte �迭 �����
			byte b[] = new byte[(int) file.length()];
			int leng = 0;
			
			// ��ȯ���� ������ ���� ������ ũ��
			while((leng=in.read(b))>0) {
				// Ŭ���̾�Ʈ�� �����ض�
				out.write(b, 0, leng);
			}
			
			out.close();
			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
