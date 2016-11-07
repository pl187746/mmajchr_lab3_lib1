package lab3.lib1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Zip {

	public static void unzip(ZipInputStream zip, Path dir) throws IOException {
		dir.toFile().mkdirs();
		ZipEntry entry;
		while((entry = zip.getNextEntry()) != null) {
			File path = dir.resolve(entry.getName()).toFile();
			if(entry.isDirectory()) {
				path.mkdirs();
			} else {
				try(FileOutputStream fout = new FileOutputStream(path)) {
					InOutStreamPump pump = new InOutStreamPump(zip, fout);
					while(pump.pump() > 0);
				}
			}
		}
	}

}
