package lab3.lib1;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InOutStreamPump implements Closeable {

	private byte[] buffer;
	private InputStream in;
	private OutputStream out;

	public InOutStreamPump(InputStream in, OutputStream out, int bufSize) {
		super();
		this.buffer = new byte[bufSize];
		this.in = in;
		this.out = out;
	}

	@Override
	public void close() throws IOException {
		out.close();
		in.close();
	}
	
	public int pump() throws IOException {
		int read = in.read(buffer);
		if(read > 0) {
			out.write(buffer, 0, read);
		}
		return read;
	}

}
