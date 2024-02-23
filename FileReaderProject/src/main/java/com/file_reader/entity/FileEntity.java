package com.file_reader.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

	@Id
	private Long id;
	private String userName;
	private String city;
	private Long mobile;
	private String about;
}
