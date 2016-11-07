package lab3.lib1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class HTTP {

	public static int download(URL url, OutputStream dest) throws IOException {
		try(InputStream src = url.openStream()) {
			@SuppressWarnings("resource")
			InOutStreamPump pump = new InOutStreamPump(src, dest);
			while(pump.pump() > 0);
			return pump.getTotal();
		}
	}
	
	public static int download(URL url, File file) throws FileNotFoundException, IOException {
		try(FileOutputStream out = new FileOutputStream(file)) {
			return download(url, out);
		}
	}
	
}
