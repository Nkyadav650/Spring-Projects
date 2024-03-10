package com.file_reader.service;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file_reader.entity.FileEntity;
import com.file_reader.repository.FileRepository;

@Service
public class FileServiceImp implements FileService {

	@Autowired
	private FileRepository fileRepo;

	@Override
	public String saveFile(String filePath) throws FileNotFoundException {
		try {
			BufferedReader files = new BufferedReader(new FileReader(filePath));
			Scanner scan = new Scanner(files);
			List<FileEntity> list = new ArrayList<>();
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				// String [] arr=line.split(",");
				String[] arr = customSplit(line);
				FileEntity fileEntity = new FileEntity();
				fileEntity.setId(Long.parseLong(arr[0].trim()));
				fileEntity.setUserName(arr[1].trim());
				fileEntity.setCity(arr[2].trim());
				fileEntity.setMobile(Long.parseLong(arr[3].trim()));
				fileEntity.setAbout(arr[4].trim());
				list.add(fileEntity);
			}
			fileRepo.saveAll(list);
			scan.close();
			return "file Saved successfully..";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private String[] customSplit(String line) {

		if (line.contains(",")) {
			return line.split(",");
		} else if (line.contains(" ")) {
			return line.split(" ");
		} else if (line.contains("/")) {

			return line.split("/");
		} else {
			return line.split(",");
		}
	}

	@Override
	public List<FileEntity> getAll() {

		return fileRepo.findAll();

	}

	@Override
	public String readPdfFile(MultipartFile files) {
		try {
            // Load PDF document
            
            PDDocument document = PDDocument.load(files.getInputStream());

            // Create PDFTextStripper
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // Get text from PDF
            String text = pdfStripper.getText(document);
            System.out.println("Text in the PDF:\n" + text);

         // Extract images
            PDFRenderer renderer = new PDFRenderer(document);
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                PDPage page = document.getPage(i);
                BufferedImage image = renderer.renderImageWithDPI(i, 300); // Adjust DPI as needed
                // Save each image to a file
                File output = new File("image_" + i + ".png");
                ImageIO.write(image, "png", output);
                System.out.println("Image saved: " + output.getAbsolutePath());
            }
            // Close the document
            document.close();
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null; 
		
	}

}
