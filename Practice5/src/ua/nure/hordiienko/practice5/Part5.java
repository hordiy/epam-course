package ua.nure.hordiienko.practice5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Part5 implements Callable<Object> {

    private static final Object M = new Object();
    private static final int TH_NUM = 10;
    private static final int COL = 20;
    private static final int EOL_LEN = System.lineSeparator().length();
    private static String fName = "test.txt";
    private static final String CH_NAME = "UTF-8";

    private RandomAccessFile out;
    private int pos;
    private String c;

    public Part5(RandomAccessFile out, int pos, String ch, int sLength) {
        this.out = out;
        this.pos = pos;

        StringBuilder builder = new StringBuilder(sLength);
        for (int i = 0; i < COL; i++) {
            builder.append(ch);
        }
        builder.append(System.lineSeparator());
        c = builder.toString();
    }

    @Override
    public Object call() throws IOException {
        RandomAccessFile raf = getRAF();
        synchronized (M) {
            raf.seek(pos);
            raf.write(c.getBytes(CH_NAME));
        }
        return null;
    }

    private RandomAccessFile getRAF() {
        return out;
    }

    public static void main(String[] args)  {
        try {
			Files.deleteIfExists(new File(fName).toPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        File file = new File(fName);
        
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
	        ExecutorService exe = Executors.newFixedThreadPool(TH_NUM);
	
	        ArrayList<Future> futures = new ArrayList<>();
	
	        int position = 0;
	        for (int currentNumber = 0; currentNumber < TH_NUM; currentNumber++) {
	            String charToFill = String.valueOf(currentNumber);
	            int cLength = COL * charToFill.length();
	            int sLength = cLength + EOL_LEN;
	
	            Future f = exe.submit(new Part5(raf, position, charToFill, sLength));
	            futures.add(f);
	
	            position += sLength;
	        }
	
	        for (Future future : futures) {
					future.get();
	        }
	        exe.shutdown();
		} catch (InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
		}
        try {
			System.out.println(new String(Files.readAllBytes(file.toPath()), CH_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}