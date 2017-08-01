import java.util.Scanner;

public class Input extends Thread{
    @Override
    public void run(){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int n = reader.nextInt();
        while (n != 0)
        {
            ReadAndAnalyze readAndAnalyze = null;
            try {
                readAndAnalyze = new ReadAndAnalyze(n);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readAndAnalyze.start();
            n = reader.nextInt();
        }
    }
}
