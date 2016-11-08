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

	public static void zipFile(InputStream in, String name, ZipOutputStream zo) throws IOException {
		ZipEntry entry = new ZipEntry(name);
		zo.putNextEntry(entry);
		@SuppressWarnings("resource")
		InOutStreamPump pump = new InOutStreamPump(in, zo);
		while(pump.pump() > 0);
		zo.closeEntry();
	}
	
	public static void zipFile(File file, String name, ZipOutputStream zo) throws IOException {
		try(FileInputStream fin = new FileInputStream(file)) {
			zipFile(fin, name, zo);
		}
	}
	
	public static void zipFile(File file, ZipOutputStream zo) throws IOException {
		zipFile(file, file.getName(), zo);
	}
	
	public static void zipFile(InputStream in, String name, OutputStream out) throws IOException {
		try(ZipOutputStream zo = new ZipOutputStream(out)) {
			zipFile(in, name, zo);
		}
	}
	
	public static void zipFile(File file, String name, OutputStream out) throws IOException {
		try(FileInputStream fin = new FileInputStream(file)) {
			zipFile(fin, name, out);
		}
	}
	
	public static void zipFile(File file, OutputStream out) throws IOException {
		zipFile(file, file.getName(), out);
	}
	
	public static void zipFile(InputStream in, String name, File out) throws IOException {
		try(FileOutputStream fout = new FileOutputStream(out)) {
			zipFile(in, name, fout);
		}
	}
	
	public static void zipFile(File file, String name, File out) throws IOException {
		try(FileInputStream fin = new FileInputStream(file)) {
			zipFile(fin, name, out);
		}
	}
	
	public static void zipFile(File file, File out) throws IOException {
		zipFile(file, file.getName(), out);
	}
	
	public static void zipDir(File dir, String name, ZipOutputStream zo) throws IOException {
		File[] files = dir.listFiles();
		for(File f : files) {
			String fn = Optional.ofNullable(name).map(p -> p + "/").orElse("") + f.getName();
			File sub = dir.toPath().resolve(f.getName()).toFile();
			if(sub.isDirectory()) {
				zipDir(sub, fn, zo);
			} else {
				zipFile(sub, fn, zo);
			}
		}
	}
	
	public static void zipDir(File dir, String name, OutputStream out) throws IOException {
		try(ZipOutputStream zo = new ZipOutputStream(out)) {
			zipDir(dir, name, zo);
		}
	}
	
	public static void zipDir(File dir, String name, File out) throws IOException {
		try(FileOutputStream fout = new FileOutputStream(out)) {
			zipDir(dir, name, fout);
		}
	}
	
	public static void zipDir(File dir, ZipOutputStream zo) throws IOException {
		zipDir(dir, null, zo);
	}
	
	public static void zipDir(File dir, OutputStream out) throws IOException {
		zipDir(dir, null, out);
	}
	
	public static void zipDir(File dir, File out) throws IOException {
		zipDir(dir, null, out);
	}

}
