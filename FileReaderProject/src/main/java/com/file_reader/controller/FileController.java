package com.file_reader.controller;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file_reader.entity.FileEntity;
import com.file_reader.service.FileService;

@RestController
public class FileController {
	
	

	@Autowired
	private FileService fileService;
	final static String path="C:\\Users\\nkyad\\OneDrive\\Documents\\";
	
	Map<String,Object>response=new HashMap<>();
	@PostMapping("/saveFile")
	public ResponseEntity<Map<String,Object>> saveFile(@RequestParam("filePaths") String filePaths) throws FileNotFoundException {
		String filePath=path+filePaths;
		String resp=fileService.saveFile(filePath);
		response.clear();
		if(!resp.equals(null)) {
			
			response.put("Result", resp);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			response.put("Result","file content not uploaded because of bad credential !!");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST); 
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Map<String,Object>> getAllfileDetails() {
		List<FileEntity>list=fileService.getAll();
		response.clear();
		if(list!=null) {
			
			response.put("Result", list);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}else {
			response.put("Result","Data not Not Available!!");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND); 
		}
	}
	
	@GetMapping("/readPdfFile")
	public ResponseEntity<String> readPdfFile(@RequestParam ("file") MultipartFile file){
		String text= fileService.readPdfFile(file);
		return new ResponseEntity<>(text,HttpStatus.OK);
	}
	
	@GetMapping("/readTextFromImage")
	public ResponseEntity<String> readTextFromImage(@RequestParam ("file") MultipartFile file){
		String text= fileService.readTextFromImage(file);
		return new ResponseEntity<>(text,HttpStatus.OK);
	}
	
	@RequestMapping("/input")
	public String homePage() {
		return "/home";
	}
	@GetMapping("/createPdf")
	public ResponseEntity<InputStreamResource> createPdf() throws Exception{
		ByteArrayInputStream createPdf = fileService.createPdf();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;file=product.pdf");
		return  (ResponseEntity<InputStreamResource>) ResponseEntity.ok()
				.headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(createPdf));
	}
}
