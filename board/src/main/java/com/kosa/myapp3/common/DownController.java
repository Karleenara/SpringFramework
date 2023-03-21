package com.kosa.myapp3.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class DownController {
	@RequestMapping("/down")
	public void download(HttpServletRequest req, 
			             HttpServletResponse res,
			             String path,
			             String filename)
	{
		//path - 모든 파일을 같은 폴더에 업로드하면 관리가 어렵다 
		// /upload/board, /upload/gallery   /upload/qna 
		// 이런식으로 나누어서 업로드를 한다. 
		// path에는 board, gallery, qna  등 /upload 아래
		// 폴더명이 들어간다. 
		//  filename : 다운로드할 파일명 
		
		//서블릿 3.0 이상부터   가상폴더의 실제 물리적 구조를 알려주는 
		//함수가 생겼음(이전부터 있었는데 클래스와 함수이름이 달라짐 
		//ServletContext ctx = req.getServletContext();
		//String fullPath = ctx.getRealPath("/upload/"+path);
		String fullPath=CommonConst.UPLOADPATH+"/"+path;
		System.out.println(fullPath);
		System.out.println(filename);
		
		//서버에 있는 파일을 읽어서 클라이언트로 존송해야 한다 
		InputStream in = null; //서버임, 파일을 읽어서  
		OutputStream out = null; //클라이언트 에게 전송한다 
		File file=null;
		boolean skip=false; //파일이 서버에 존재해야 전송한다. 
		                    //이 변수의 값이  true 여야 파일을 전송한다 
		//파일은 두종류가 있다 
		//이진파일(이미지, 동영상, exe, class 파일등)과, 그림판, window player 
		//텍스트파일(txt,  소스파일들) - 특별한 프로그램 없이 읽을 수 있으면  
		// 컴퓨터 내부의 메모리를 가공을 해서 저장 , 이진파일은 컴퓨터 내부 메모리 그대로 
		// 파일에 저장한다. 동영상을 텍스트로 취급하면 복구 불가 
		// 이진으로 처리를 해야 본래 파일을 손상시키지 않는다. 이진형태로 보내야 한다 
		try
		{
			file = new File(fullPath, filename); //파일을 찾아서 
			in = new FileInputStream(file); //읽을 준비중이다 
		}
		catch(Exception e) //파일이 존재하지 않거나 그밖의 오류로 전송불가면
		{
			e.printStackTrace();
			skip = true;
		}
		
		if(skip== true)
			return;
		
		//한글처리 
		try {
			String client = req.getHeader("User-Agent");
			if(client.indexOf("MSIE")!=-1)
				filename = new String(filename.getBytes("KSC5601"),"ISO8859_1");
			else 
				filename = new String(filename.getBytes("KSC5601"),"iso-8859-1");
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
				
				
		//보낼 준비 
		res.reset(); //혹시 서버에서 클라이언트로 보낼 정보가 남아있을경우 비우자
		res.setContentType("application/octet-stream"); 

		//2진형태로 파일을 보내겠다는 사실을 클라이언트에 알린다.
		//attachment; filename="test.txt"
		res.setHeader("Content-Disposition", 
				"attachment; filename=\""+  filename + "\"");
		res.setHeader("Content-type", "application/octet-stream");
		res.setHeader("Content-length", file.length()+"");
		
		try {
			out = res.getOutputStream();
			//파일을 byte단위로 읽어야 한다 
			//파일이 아주 클경우에 한번에 메모리 확보가 안될경우 잘라서 읽어 보내야 한다. 
			//현재는 파일 하나를 저장할 byte 배열을 만들었음
			byte b[] = new byte[(int)file.length()];
			int leng=0;
			//in.read(b) - 주어진 배열크기만큼 데이터를 읽어온다. 
			//반환값은 실제로 읽은 데이터 크기이다. 
			while((leng=in.read(b))>0)  //>0 읽은 데이터가 있다면 
			{
				out.write(b, 0, leng); //클라이언트로 전송하라 
			}
			out.close();// 파일을 닫는다
			in.close(); //파일을 닫는다 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //파일을 보내기 위한 통로 개설,
	}
	//http://127.0.0.1:9000/myapp3/down?path=board&filename=test.txt
}




