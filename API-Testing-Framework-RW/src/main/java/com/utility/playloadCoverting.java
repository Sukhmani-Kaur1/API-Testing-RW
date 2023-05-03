package com.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class playloadCoverting {
 public static String generatingLoadString(String filename) throws IOException {
//	String filePath = "/Users/Administrator/Documents/workspace-spring-tool-suite-4-4.17.2.RELEASE/APITesting-Cucumber/src/test/resources/"+filename;
	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\"+filename;
	return new String(Files.readAllBytes(Paths.get(filePath)));
	
			
 }
}
