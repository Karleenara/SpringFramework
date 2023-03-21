package com.kosa.myapp3.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

//파일업로드 전담 클랫 
public class FileUploadUtil {
	public static String filePath="";//물리적 경로 

	
	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		FileUploadUtil.filePath = filePath;
	}

	public static String getFileName(String orifilename) {
		String newFilename="";
		//1.파일명과 확장자를 분리한다 
		//오른쪽에서 . 가 있는 위치값을 찾는다 
		//오른쪽 끝에서부터 찾는다 
		int pos = orifilename.lastIndexOf(".");
		String ext=""; //학장자
		String filename="";
		if(pos!=-1)
		{
			filename = orifilename.substring(0,  pos);//파일명만 추출
			ext = orifilename.substring(pos+1); //확장자만 추출하기
		}
		else //확장자 없는 파일일 경우에는 
		{
			filename = orifilename;
			ext="";
		}
		
		System.out.println("파일이름 : " + filename);
		System.out.println("확장자 : " + ext);
		newFilename = filename + "." + ext;
		File newFile = new File(filePath +"/"+newFilename);
		int i=1;
		while(newFile.exists())//파일이 있다면 
		{
			//새로운 파일명을 만든다.  a(1).jpg, a(2).jpg ....
			newFilename = filename + "("+i+")"+"."+ext;
			i++;
			newFile = new File(filePath +"/"+newFilename); //다시확인
		}
		System.out.println(newFilename);
		return newFilename;
	}
	
	public static void upload(List<MultipartFile> fileList, 
			List<String>fileNameList)
	{
		File file  = new File(filePath);
		if(!file.exists())
		{
			file.mkdirs();
		}
		
		//첨부파일이 있을때 
		if( fileList!=null && fileList.size()>0)
		{
			for(MultipartFile mfile : fileList)
			{
				//1.본래 파일명을 가져온다 
				String orifilename=mfile.getOriginalFilename();
				//파일 첨부 안했을때의 오류처리를 해야 한다 
				System.out.println("filename : " + orifilename);
				if(orifilename==null || orifilename.equals(""))
				{
					fileNameList.add("");
					continue; //이 다음 코드를 건너뛰어서 다시 for문으로이동 
				}
				//파일명이 충돌안나게 바꾼다 
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





