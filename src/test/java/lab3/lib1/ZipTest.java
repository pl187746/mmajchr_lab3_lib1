package lab3.lib1;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipOutputStream;

import org.junit.After;
import org.junit.Test;

public class ZipTest {
	
	@Test
	public void unzip() throws IOException {
		Zip.unzip(new File("test_zip_in.zip"), Paths.get("test_unzipped"));
		File file = new File("test_unzipped/lol.txt");
		assertTrue(file.exists());
		String cnt = new String(Files.readAllBytes(Paths.get("test_unzipped/lol.txt")));
		assertTrue("rotfl".equals(cnt));
	}

	@After
	public void deleteUnzipped() {
		deleteDir(new File("test_unzipped"));
	}

	private static void deleteDir(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteDir(f);
	        }
	    }
	    file.delete();
	}	

	@Test
	public void zipOne() throws FileNotFoundException, IOException {
		Zip.zipFile(new File("pom.xml"), new ZipOutputStream(new FileOutputStream(new File("test_zip_out_1.zip"))));
	}

}
