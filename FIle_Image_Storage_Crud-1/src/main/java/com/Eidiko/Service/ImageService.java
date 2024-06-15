package com.Eidiko.Service;



import org.springframework.web.multipart.MultipartFile;

import com.Eidiko.Entity.Image;





public interface ImageService {

	public String uploadImage(MultipartFile file)throws Exception;
	public  byte[] downloadImage(String imageName) throws Exception;
	public Image downloadImageDetails(String imageName) throws Exception;
}
