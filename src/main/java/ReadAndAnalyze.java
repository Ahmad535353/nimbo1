import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadAndAnalyze extends Thread{
    public ReadAndAnalyze(int timeInSec) throws InterruptedException {
        Thread.currentThread().setPriority(5);
        System.out.println("ReadAndAnalyze started!");
        long limit = System.currentTimeMillis() - (timeInSec*1000);

    }
    @Override
    public void run() {

        System.out.println("ReadAndAnalyze run! before sleep");
        long thisTime = System.currentTimeMillis() / 60000;
        System.out.println("Time : " + thisTime);
        try {
            Thread.currentThread().sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ReadAndAnalyze run! after sleep");
        try (BufferedReader br = new BufferedReader(new FileReader("PersonsMain" + thisTime))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                // process the line.
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }

        thisTime--;
        try (BufferedReader br = new BufferedReader(new FileReader("PersonsMain" + thisTime + ".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                // process the line.
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
}
