package com.Eidiko.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Eidiko.Entity.File;
import com.Eidiko.Service.FileService;




@RestController

public class FileController {

	@Autowired
	private FileService fileService;
	@PostMapping("/saveFile")
	public String saveFile(@RequestBody MultipartFile file) throws Exception{
		return fileService.saveFile(file);
	}

	public File saveDocument(@RequestBody File document) {
        return fileService.saveDocument(document);
    }
	
}
