package com.Eidiko.Service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.Eidiko.Entity.File;

public interface FileService {

	public String saveFile(MultipartFile file) throws Exception;
	public File saveDocument(File document);
}
