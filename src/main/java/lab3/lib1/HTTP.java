package lab3.lib1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class HTTP {

	public static void download(URL url, OutputStream dest) throws IOException {
		InputStream src = url.openStream();
	}
	
}
