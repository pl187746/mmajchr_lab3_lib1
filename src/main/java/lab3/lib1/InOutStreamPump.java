package lab3.lib1;

import java.io.InputStream;
import java.io.OutputStream;

public class InOutStreamPump {

	private byte[] buffer;
	private InputStream in;
	private OutputStream out;

	public InOutStreamPump(InputStream in, OutputStream out, int bufSize) {
		super();
		this.buffer = new byte[bufSize];
		this.in = in;
		this.out = out;
	}

}
