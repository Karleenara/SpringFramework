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

		// path - 모든 파일을 같은 폴더에 업로드하면 관리가 어려움
		// /upload/board, /upload/gallery , /upload/qna
		// filename : 다운로드할 파일명

		// 가상폴더의 실제 물리적 구조를 알려주는 함수
//		ServletContext ctx = req.getServletContext();
		String fullPath = CommonConst.UPLOADPATH + "/" + path;
		System.out.println(fullPath);
		System.out.println(filename);
		// test하기 : http://localhost:9000/myapp3/down?path=board&filename=test.txt

		// 서버에 있는 파일 읽어서 클라이언트로 전송
		InputStream in = null; // 서버, 파일 읽어서
		OutputStream out = null; // 클라이언트에게 전송
		File file = null;
		boolean skip = false; // 파일이 서버에 존재해야 전송 - true 여야 전송

		try {
			// 파일 찾아서
			file = new File(fullPath, filename);
			// 읽을 준비중
			in = new FileInputStream(file);
		} catch (Exception e) {
			// 파일이 존재하지 않거나 그 밖의 오류로 전송 불가면
			skip = true;
		}
		if(skip == true)
			return ;
		
		// 한글 처리
		try {
			// 브라우저 마다 달라서 if 처리
			String client = req.getHeader("User-Agent");
			if(client.indexOf("MSIE") != -1)
				filename = new String(filename.getBytes("KSC5601"), "ISO08859_1");
			else
				// 크롬
				filename = new String(filename.getBytes("KSC5601"), "iso-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// 보낼 준비
		res.reset(); // 서버에서 클라이언트로 보낼 정보가 남아있을 경우 비우기
		res.setContentType("application/octet-stream"); 
		
		// 2진 형태로 보낸다고 클라이언트에게 알리기
		// attachment; filename="test.txt"
		res.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		res.setHeader("Content-type", "application/octet-stream");
		res.setHeader("Content-length", file.length()+"");
		
		try {
			// 파일 보내기 위한 통로 개설
			out = res.getOutputStream();
			// 파일이 아주 클경우 한번에 메모리 확보가 안될 경우 잘라서 읽어 보내야 함
			// 현재는 파일 하나 저장할 byte 배열 만들기
			byte b[] = new byte[(int) file.length()];
			int leng = 0;
			
			// 반환값은 실제로 읽은 데이터 크기
			while((leng=in.read(b))>0) {
				// 클라이언트로 전송해라
				out.write(b, 0, leng);
			}
			
			out.close();
			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
