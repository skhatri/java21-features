package readbigfiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;


public class App {
    private static class LineCounter extends OutputStream {
        int count;

        LineCounter() {
        }

        @Override
        public void write(int b) throws IOException {
            char c = (char) b;
            if (c == '\n') {
                count++;
            }
        }

        int getLines() {
            return count;
        }
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        String filename = "build.gradle.kts";
        if (args.length > 0) {
            filename = args[0];
        }
        LineCounter lineCounter = new LineCounter();
        WritableByteChannel out = Channels.newChannel(lineCounter);
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             FileChannel inputStreamChannel = fileInputStream.getChannel()) {
            long progress = 0;
            long total = inputStreamChannel.size();

            while (progress < total) {
                progress += inputStreamChannel.transferTo(0, total - progress, out);
            }
        } catch (IOException e) {
            System.err.println("error transferring data: " + e.getMessage());
        }
        System.out.printf("read %d lines of [%s] in %d ms\n", lineCounter.getLines(), filename, (System.currentTimeMillis() - start));
    }
}
