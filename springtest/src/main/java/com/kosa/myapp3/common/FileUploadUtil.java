package com.kosa.myapp3.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileUpload;
import org.springframework.web.multipart.MultipartFile;

// ���Ͼ��ε� ���� Ŭ����
public class FileUploadUtil {
	static String filePath = ""; // ������ ���

	// �����ڸ� private�� ����� ��ü ���� �Ұ�
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
		
		System.out.println("�����̸�: " + filename);
		System.out.println("Ȯ����: " + ext);
		newFilename = filename + "." + ext;
		File newFile = new File(filePath + "/"+ newFilename);
		int i =1;
		while(newFile.exists()) {
			// ���ο� ���ϸ� ����� a(1).jpg, a(2).jpg ...
			newFilename = filename + "("+i+")"+"."+ext;
			i++;
			newFile = new File(filePath + "/"+ newFilename);
		}
		
		return newFilename;
	}

	// ��Ʈ�ѷ����� MultipartFile ��ü����Ʈ �����ϸ� ���� ���� �� ������ �̸� ��� ����
	// ���ϸ� �浹���� �浹 ���� - a.jpg, a(1).jpg, a(2).jpg ..
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
				// ���� ���ϸ� ��������
				String orifilename=mfile.getOriginalFilename();
				// ���� ÷�� ������ �� ����ó�� �ϱ�
				System.out.println("filename: " + orifilename);
				if(orifilename==null || orifilename.equals("")){
					fileNameList.add("");
					continue;	// �� ���� �ڵ带 �ǳʶپ �ٽ� for������ �̵�
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
