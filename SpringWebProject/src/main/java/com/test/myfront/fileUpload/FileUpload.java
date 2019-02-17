package com.test.myfront.fileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class FileUpload {
	String fileName="";
	
	public String fileUpload(MultipartHttpServletRequest request,
								MultipartFile uploadFile) {
		String path="";
		String fileName="";
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		try {
			fileName = uploadFile.getOriginalFilename();
			byte[] bytes = uploadFile.getBytes();
			path = getSaveLocation(request);
			
			File file = new File(path);
			
			out = new FileOutputStream(file);
			out.write(bytes);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(out != null) {
					out.close();
				}
				if(printWriter != null) {
					printWriter.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return path+fileName;
	}

	private String getSaveLocation(MultipartHttpServletRequest request) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		String attachPath = "resources/files/";
		return uploadPath+attachPath;
	}
}
