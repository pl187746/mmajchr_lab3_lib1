package lab3.lib1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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
					@SuppressWarnings("resource")
					InOutStreamPump pump = new InOutStreamPump(zip, fout);
					while(pump.pump() > 0);
					zip.closeEntry();
				}
			}
		}
	}
	
	public static void unzip(InputStream in, Path dir) throws IOException {
		try(ZipInputStream zip = new ZipInputStream(in)) {
			unzip(zip, dir);
		}
	}
	
	public static void unzip(File zip, Path dir) throws IOException {
		try(FileInputStream fin = new FileInputStream(zip)) {
			unzip(fin, dir);
		}
	}
	
	public static void zip(File path, ZipOutputStream zo) throws IOException {
		zip(null, path, zo);
	}
	
	private static void zip(String prefix, File path, ZipOutputStream zo) throws IOException {
		prefix = Optional.ofNullable(prefix).map(s -> s + "/").orElse("") + path.getName();
		if(!path.isDirectory()) {
			ZipEntry entry = new ZipEntry(prefix);
			zo.putNextEntry(entry);
			try(FileInputStream fin = new FileInputStream(path)) {
				@SuppressWarnings("resource")
				InOutStreamPump pump = new InOutStreamPump(fin, zo);
				while(pump.pump() > 0);
				zo.closeEntry();
			}
		}
	}
	
	public static void zip(File path, OutputStream out) throws IOException {
		try(ZipOutputStream zo = new ZipOutputStream(out)) {
			zip(path, zo);
		}
	}

}
