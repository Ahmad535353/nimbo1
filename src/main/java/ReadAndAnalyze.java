import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadAndAnalyze extends Thread{
    private long upperTime;
    private long lowerTime;
    HashMap<Long, Actor> actors = new HashMap<Long, Actor>();
    HashMap<Long, Repo> repos = new HashMap<Long, Repo>();

    public ReadAndAnalyze(int timeInSec) throws InterruptedException {
        Thread.currentThread().setPriority(5);
        timeInSec = 120;
        lowerTime = System.currentTimeMillis();
        upperTime = lowerTime + (timeInSec*1000);

    }
    @Override
    public void run() {

        System.out.println("ReadAndAnalyze run! before sleep");
        long upperTimeInMinute = upperTime / 60000;
        System.out.println("Time : " + upperTimeInMinute);
        try {
            Thread.currentThread().sleep(150000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ReadAndAnalyze run! after sleep");

        //read
        long buffTime = upperTime / 60000;

        while (buffTime >= (lowerTime / 60000) ){
            try (BufferedReader br = new BufferedReader(new FileReader("PersonsMain" + buffTime))) {
                String line;
                while ((line = br.readLine()) != null) {
                    long savedTime = Long.valueOf(line);
                    Actor actor = new Actor();
                    if (savedTime < upperTime && savedTime > lowerTime)
                    {
                        String idS = br.readLine();
                        actor.id = Long.valueOf(idS);
                        actor.login = br.readLine();

                        if (actors.containsKey(actor.id)) {
                            actor.cntr = actors.get(actor.id).cntr;
                            actors.remove(actor.id);
                        }
                        actor.cntr++;
                        actors.put(actor.id, actor);
                    }
                    else {
                        br.readLine();
                        br.readLine();
                    }
//                System.out.println(line + " " + actor.id + " " + actor.login );
//                    System.out.println(line);
//                    // process the line.
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffTime--;
            System.out.println("*************************************************************************");
        }
    }
}
