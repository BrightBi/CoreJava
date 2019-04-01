package others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class CopyFile {

	private static File source = new File("/Users/mingliangbi/Downloads/pl-PL/test.txt");
	private static File dest = new File("/Users/mingliangbi/Downloads/pl-PL/test-copy.txt");

	public static void main(String[] args) throws IOException {
		copyFileUsingFileChannels(source, dest);
//		copyFileUsingJava7Files(source, dest);
//		copyFileUsingFileStreams(source, dest);
	}

	public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(source);
			inputChannel = fileInputStream.getChannel();
			fileOutputStream = new FileOutputStream(dest);
			outputChannel = fileOutputStream.getChannel();

			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			fileInputStream.close();
			outputChannel.close();
			fileOutputStream.close();
		}
	}

	public static void copyFileUsingJava7Files(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}

	public static void copyFileUsingFileStreams(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}

}
