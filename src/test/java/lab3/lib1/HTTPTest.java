package lab3.lib1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.junit.Test;

public class HTTPTest {

	@Test
	public void google() throws Throwable {
		byte[] content = HTTP.download(new URL("http://google.pl"));
		String strCnt = new String(content);
		assertTrue(strCnt.contains("Google"));
	}
	
	@Test
	public void regulamin() throws Throwable {
		URL url = new URL("https://www.p.lodz.pl/sites/default/files/pliki/regulamin_studiow_0.pdf");
		File file = new File("regulamin.pdf");
		int size = HTTP.download(url, file);
		assertTrue(file.exists());
		assertEquals(file.length(), size);
	}

}
