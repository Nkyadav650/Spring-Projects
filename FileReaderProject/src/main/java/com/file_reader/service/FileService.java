package com.file_reader.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.file_reader.entity.FileEntity;
//file service
public interface FileService {

	public String saveFile(String file) throws FileNotFoundException;
	public List<FileEntity> getAll();
	public String  readPdfFile(MultipartFile file);
}
