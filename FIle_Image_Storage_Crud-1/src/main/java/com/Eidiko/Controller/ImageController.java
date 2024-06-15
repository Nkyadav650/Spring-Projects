package com.Eidiko.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Eidiko.Entity.Image;
import com.Eidiko.Service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@PostMapping()
	public String uploadImage(@RequestParam("image") MultipartFile file) throws Exception{
		String image=imageService.uploadImage(file);
		return image;
	}
	
	@GetMapping("/{imageName}")
	public ResponseEntity<?> downloadImage(@PathVariable String imageName) throws Exception {
		 byte[] imageData=imageService.downloadImage(imageName);
		 
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
	}
	@GetMapping("/{imageDetails}")
	public ResponseEntity<?> downloadImageDetails(@PathVariable String imageName) throws Exception {
		 byte[] imageData=imageService.downloadImage(imageName);
		 
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
	}
	
	//file upload by file system
	
}
