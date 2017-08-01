import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ReadAndAnalyze extends Thread{
    private long upperTime;
    private long lowerTime;
    private HashMap<Long, Actor> actors = new HashMap<Long, Actor>();
    private HashMap<Long, Repo> repos = new HashMap<Long, Repo>();
    private TreeSet <Actor> actorsTree = new TreeSet<Actor>();
    private TreeSet <Repo> reposTree = new TreeSet<Repo>();

    public ReadAndAnalyze(int timeInSec) throws InterruptedException {
        Thread.currentThread().setPriority(5);
        upperTime = System.currentTimeMillis();
        lowerTime = System.currentTimeMillis() - (timeInSec * 1000);

    }
    @Override
    public void run() {

        System.out.println("ReadAndAnalyze run!");
        long upperTimeInMinute = upperTime / 60000;
        System.out.println("Time : " + upperTimeInMinute);

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
                System.out.println("time is invalid");
            }

            //start new
            try (BufferedReader br = new BufferedReader(new FileReader("RepositoriesMain" + buffTime))) {
                String line;
                while ((line = br.readLine()) != null){
                    long savedTime = Long.valueOf(line);
                    Repo repo = new Repo();
                    if (savedTime < upperTime && savedTime > lowerTime)
                    {
                        String idS = br.readLine();
                        repo.id = Long.valueOf(idS);
                        repo.name = br.readLine();
                        if (repos.containsKey(repo.id))
                        {
                            repo.cntr = repos.get(repo.id).cntr;
                            repos.remove(repo.id);
                        }
                        repo.cntr++;
                        repos.put(repo.id,repo);
                    }
                    else
                    {
                        br.readLine();
                        br.readLine();
                    }
                }
            } catch (IOException e) {
                System.out.println("time is invalid");
                e.printStackTrace();
            }
            //end new
            buffTime--;
            System.out.println("*************************************************************************");
        }
        this.analyze();
    }
    public void analyze()
    {
        System.out.println("Actors:");
        for(Map.Entry<Long, Actor> en: actors.entrySet()) {
            actorsTree.add(en.getValue());
        }
        while (!actorsTree.isEmpty()){
            Actor temp = actorsTree.last();
            System.out.println(String.format("%-50s %-10s %-10s",temp.login , temp.id , temp.cntr));
            actorsTree.remove(temp);
        }
        System.out.println("\n Repositories:");
        for(Map.Entry<Long, Repo> en: repos.entrySet()) {
            reposTree.add(en.getValue());
        }
        while (!reposTree.isEmpty()){
            Repo temp = reposTree.last();
            System.out.println(String.format("%-50s %-10s %-10s",temp.name , temp.id , temp.cntr));
            reposTree.remove(temp);
        }
    }
}
