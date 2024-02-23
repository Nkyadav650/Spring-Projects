package com.file_reader.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
