package com.mjm.filedata;

import com.mjm.filedata.entities.Image;
import com.mjm.filedata.repos.ImageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FiledataApplicationTests {

	@Autowired
	ImageRepository repository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testImageUpload(){
		Image image = new Image();
		image.setId(1);
		image.setName("greenville.jpg");
		try {
			File file = new File("C:\\Users\\MarkM\\Pictures\\greenville.jpg");
			byte[] fileContent = new byte[(int)file.length()];
			FileInputStream inputStream = new FileInputStream(file);
			inputStream.read(fileContent);
			image.setData(fileContent);
			repository.save(image);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testImageRead(){
		try {
			Image image = repository.findOne(1);
			File file = new File("C:\\Users\\MarkM\\Pictures\\saved\\" + image.getName());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(image.getData());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
