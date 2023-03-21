package com.kosa.myapp3.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileUpload;
import org.springframework.web.multipart.MultipartFile;

// 파일업로드 전담 클래스
public class FileUploadUtil {
	static String filePath = ""; // 물리적 경로

	// 생성자를 private로 만들면 개체 생성 불가
	private FileUploadUtil() {
	}

	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		FileUploadUtil.filePath = filePath;
	}
	
	public static String getFileName(String orifilename) {
		String newFilename="";
		int pos = orifilename.lastIndexOf(".");
		String ext=""; 
		String filename="";
		if(pos!=-1)
		{
			filename = orifilename.substring(0,  pos);
			ext = orifilename.substring(pos+1); 
		}
		else
		{
			filename = orifilename;
			ext="";
		}
		
		System.out.println("파일이름: " + filename);
		System.out.println("확장자: " + ext);
		newFilename = filename + "." + ext;
		File newFile = new File(filePath + "/"+ newFilename);
		int i =1;
		while(newFile.exists()) {
			// 새로운 파일명 만들기 a(1).jpg, a(2).jpg ...
			newFilename = filename + "("+i+")"+"."+ext;
			i++;
			newFile = new File(filePath + "/"+ newFilename);
		}
		
		return newFilename;
	}

	// 컨트롤러에서 MultipartFile 객체리스트 전달하면 파일 저장 후 파일의 이름 목록 전달
	// 파일명 충돌날때 충돌 방지 - a.jpg, a(1).jpg, a(2).jpg ..
	// 					- 20220202215030.jpg
	public static void upload(List<MultipartFile> fileList, 
			List<String>fileNameList)
	{
		File file  = new File(filePath);
		if(!file.exists())
		{
			file.mkdirs();
		}
	
		if( fileList!=null && fileList.size()>0)
		{
			for(MultipartFile mfile : fileList)
			{
				// 본래 파일명 가져오기
				String orifilename=mfile.getOriginalFilename();
				// 파일 첨부 안했을 때 오류처리 하기
				System.out.println("filename: " + orifilename);
				if(orifilename==null || orifilename.equals("")){
					fileNameList.add("");
					continue;	// 이 다음 코드를 건너뛰어서 다시 for문으로 이동
				}
				
				String newFileName = getFileName( orifilename );
				
				try {
					mfile.transferTo(new File(filePath+"/"+newFileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				fileNameList.add(newFileName);
			}
		}
	}
		

}
