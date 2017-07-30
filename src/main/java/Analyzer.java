import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Created by QotboddiniH on 7/29/17.
 */
public class Analyzer extends Thread {
//    Deque<Actor> actors = new ArrayDeque<Actor>();
    HashMap<Long, Long> actors = new HashMap<Long, Long>();

    public Analyzer () {
        Thread.currentThread().setPriority(5);
    }


    public void update (JsonData jsonData) {
        Actor actor = jsonData.actor;
        if (actors.containsKey(actor.id)) {
            actors.remove(actor.id);
        }
        actors.put(actor.id, ++actor.cntr);
        //System.out.println("hi buddy 3  **" + actor.id + "     " + actor.cntr + "\n");
        if (actor.cntr > 1) {
            System.out.println("                             yes\n");
            System.exit(0);
        }
    }

    @Override
    public void run () {
        while (true) {
            if (Receiver.jsonList.isEmpty()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (!Receiver.jsonList.isEmpty()) {
                update(Receiver.jsonList.getFirst());
                Receiver.jsonList.removeFirst();
             //   System.out.println("hi buddy 2   *" + Receiver.jsonList.size() + "\n");
            }
        }
    }
}
