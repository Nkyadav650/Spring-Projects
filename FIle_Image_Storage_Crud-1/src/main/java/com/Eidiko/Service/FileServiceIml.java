package com.Eidiko.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Eidiko.Entity.File;
import com.Eidiko.Repository.FileRepo;

@Service
public class FileServiceIml implements FileService {
	@Autowired
	private FileRepo fileRepo;

	final String filePath="C:\\Users\\nkyad\\Downloads\\project\\Project test";
	@Override
	@Transactional
	public String saveFile(MultipartFile file) throws Exception{
		
			String savePath=filePath+file.getOriginalFilename();
			File files=fileRepo.save(File.builder()
					.fileName(file.getOriginalFilename())
					.filePath(savePath)
					.file(file.getBytes()).build()
					);
			file.transferTo((Path) new File(savePath));
		if(files!=null) {
			return "file uploaded successfully: "+savePath;
		}
			return null;
	}
	
	public byte[] downloadByFileSystem(String fileName) throws IOException {
		Optional<File> dbFile=fileRepo.findByfileName(fileName);
		String filePath=dbFile.get().getFilePath();
		byte [] image=Files.readAllBytes((Path) new File(filePath));
		return image;
	}

	@Override
	public File saveDocument(File document) {
		
	        return fileRepo.save(document);
	    
	}
	
	
	

}
