package lab3.lib1;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Test;

public class ZipTest {
	
	@Test
	public void unzip() throws IOException {
		Zip.unzip(new File("test_zip_in.zip"), Paths.get("test_unzipped"));
		File file = new File("test_unzipped/lol.txt");
		assertTrue(file.exists());
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

}
