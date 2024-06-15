package com.Eidiko.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Eidiko.Entity.Image;
import com.Eidiko.Repository.ImageRepo;


@Service
public class ImageServiceIml implements ImageService{

	@Autowired
	private ImageRepo imageRepo;
	
	String folderPath="C:\\Users\\Sreenivas Bandaru\\Pictures\\Saved Pictures\\";
	@Override
	public String uploadImage(MultipartFile file)throws Exception{
		String imagePaths=folderPath+file.getOriginalFilename();
		Image image1= imageRepo.save(Image.builder()
				.imageName(file.getOriginalFilename())
				.imagePath(imagePaths)
				.image(file.getBytes()).build()
				);
		if(image1!=null) {
			return "image uploaded sucessfully : "+file.getOriginalFilename();
		}
		return null;
			
		
	}
	@Override
	public  byte[] downloadImage(String imageName) throws Exception {
	 Image imageData=imageRepo.findByimageName(imageName).get();
	 byte[] image=imageData.getImage();

		 
		 return image;
	}
	
	@Override
	public  Image downloadImageDetails(String imageName) throws Exception {
	 Image imageData=imageRepo.findByimageName(imageName).get();
	 byte[] image=imageData.getImage();

		imageData.setImage(image); 
		 return imageData;
	}
	/*
	 @GetMapping(path = {"/get/image/{name}"})
	    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

	        final Optional<Image> dbImage = imageRepository.findByName(name);

	        return ResponseEntity
	                .ok()
	                .contentType(MediaType.valueOf(dbImage.get().getType()))
	                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
	    }
	}
*/
 
}
