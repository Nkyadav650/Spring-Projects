package com.Eidiko.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity 
@Table(name="image_data")
public class Image {
 public Image(String message, boolean b) {
		// TODO Auto-generated constructor stub
	}
public Image(Image imageData) {
	super();// TODO Auto-generated constructor stub
}
@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long imageId;
	private String imageName;
	private String imagePath;
	@Lob
	@Column(name="imageData",length=1000)
	private byte[] image;
}
