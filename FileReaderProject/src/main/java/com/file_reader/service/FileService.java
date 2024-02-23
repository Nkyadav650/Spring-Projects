package com.file_reader.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.file_reader.entity.FileEntity;
//file service
public interface FileService {

	public String saveFile(String file) throws FileNotFoundException;
	public List<FileEntity> getAll();
}
