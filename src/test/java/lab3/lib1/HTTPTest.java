package lab3.lib1;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.junit.Test;

public class HTTPTest {

	@Test
	public void google() throws Throwable {
		byte[] content = HTTP.download(new URL("http://google.pl"));
		String strCnt = new String(content);
		assertTrue(strCnt.contains("Google"));
	}

}
