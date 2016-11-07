package lab3.lib1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class HTTP {

	public static void download(URL url, OutputStream dest) throws IOException {
		try(InputStream src = url.openStream()) {
			@SuppressWarnings("resource")
			InOutStreamPump pump = new InOutStreamPump(src, dest);
			while(pump.pump() > 0);
		}
	}
	
}
