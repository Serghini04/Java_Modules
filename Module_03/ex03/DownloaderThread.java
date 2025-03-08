import java.io.*;
import java.net.*;
import java.util.concurrent.BlockingQueue;

public class DownloaderThread extends Thread {
    private final BlockingQueue<String> urlQueue;
    private final int threadNumber;

    public DownloaderThread(BlockingQueue<String> urlQueue, int threadNumber) {
        this.urlQueue = urlQueue;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        try {
            while (!urlQueue.isEmpty()) {
                String url = urlQueue.poll();
                if (url == null)
					break;
                System.out.println("Thread-" + threadNumber + " starts downloading: " + url);
                downloadFile(url);
                System.out.println("Thread-" + threadNumber + " finished downloading.");
            }
        } catch (Exception e) {
            System.err.println("Thread-" + threadNumber + " encountered an error: " + e.getMessage());
        }
    }

    private void downloadFile(String fileURL) throws IOException {
        URL url = new URL(fileURL);
        URLConnection connection = url.openConnection();
        String fileName = "Output/" + fileURL.substring(fileURL.lastIndexOf("/") + 1);

        try (InputStream in = connection.getInputStream();
             FileOutputStream out = new FileOutputStream(fileName)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
