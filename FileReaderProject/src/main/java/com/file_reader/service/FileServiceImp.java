package com.file_reader.service;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

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

	@Override
	public String readTextFromImage(MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));

			if (bufferedImage == null) {
				return "Error: Unable to decode image format.";
			}

			// Perform OCR only if the image format is supported (e.g., PNG, JPEG, etc.)
			ITesseract tesseract = new Tesseract();
			// tesseract.setDatapath("C:\\Users\\nkyad\\Downloads");

			// tesseract.setDatapath("path/to/your/tessdata"); // Provide the path to the
			// tessdata folder

			// tesseract.setLanguage("eng");
			// tesseract.setPageSegMode(1); //Set page segmentation mode if needed
			// tesseract.setOcrEngineMode(1); // Set OCR engine mode if needed

			String result = tesseract.doOCR(bufferedImage);
			System.out.println("Extracted Text:");
			System.out.println(result);
			return result;
		} catch (IOException | TesseractException e) {
			e.printStackTrace();
			return "Error reading text from image: " + e.getMessage();
		}

	}

	@Override
	public ByteArrayInputStream createPdf() throws Exception {
		String title = "Welcome to Pdf file";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		
		HeaderFooter footer= new HeaderFooter(new Phrase("nk"),true);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorderWidthBottom(0);
		document.setFooter(footer);
		
		document.open();

		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
		Paragraph titlePara = new Paragraph(title, titleFont);
		titlePara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlePara);

		List<FileEntity> dataList = fileRepo.findAll();
		Font tableFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
		// Assuming dataList is a list of objects where each object represents a row
		// and has appropriate getters for the columns
		if (!dataList.isEmpty()) {
			// Define the number of columns based on the FileEntity fields
			int numColumns = 5;
			Table table = new Table(numColumns);

			// Set table column widths
			table.setWidths(new float[] { 1, 2, 2, 2, 3 }); // Adjust as necessary
			
			// Add header row
			table.addCell(new Cell(new Phrase("ID", tableFont)));
			table.addCell(new Cell(new Phrase("User Name", tableFont)));
			table.addCell(new Cell(new Phrase("City", tableFont)));
			table.addCell(new Cell(new Phrase("Mobile", tableFont)));
			table.addCell(new Cell(new Phrase("About", tableFont)));

			// Add data rows
			for (FileEntity entity : dataList) {
				table.addCell(new Cell(new Phrase(entity.getId().toString())));
				table.addCell(new Cell(new Phrase(entity.getUserName())));
				table.addCell(new Cell(new Phrase(entity.getCity())));
				table.addCell(new Cell(new Phrase(entity.getMobile().toString())));
				table.addCell(new Cell(new Phrase(entity.getAbout())));
				
			}
			table.setAlignment(Element.ALIGN_CENTER);
			document.add(table);
		}

		Paragraph paragraph = new Paragraph("This end of line ! ", tableFont);
		paragraph.add(new Chunk("Thank You"));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);
		document.close();

		return new ByteArrayInputStream(out.toByteArray());
	}

}
