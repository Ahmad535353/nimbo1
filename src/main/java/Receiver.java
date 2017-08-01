/**
 * Created by QotboddiniH on 7/26/17.
 */
import com.satori.rtm.*;
import com.satori.rtm.model.*;
import org.omg.PortableInterceptor.ACTIVE;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeSet;

public class Receiver { //SubscribeToOpenChannel
    static final String endpoint = "wss://open-data.api.satori.com";
    static final String appkey = "21320cC01D7bC9364399e01eC8dDFa9C";
    static final String channel = "github-events";

    static Deque<JsonData> jsonList = new LinkedList<JsonData>();

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setPriority(5);

        final RtmClient client = new RtmClientBuilder(endpoint, appkey).setListener(new RtmClientAdapter() {
            @Override
            public void onEnterConnected(RtmClient client) {
//                System.out.println("Connected to Satori RTM!");
            }
        }).build();

        Analyzer analyzer = new Analyzer();
        analyzer.start();

        Input input = new Input();
        input.start();

        SubscriptionAdapter listener = new SubscriptionAdapter() {
            @Override
            public void onSubscriptionData(SubscriptionData data) {
                for (AnyJson json : data.getMessages()) {
                    jsonList.addLast(json.convertToType(JsonData.class));
//                    System.out.println("hi buddy 1    " + jsonList.size() +  "\n");
//                    System.out.println(json.toString() + "\n");//
                }
            }
        };

        client.createSubscription(channel, SubscriptionMode.SIMPLE, listener);

        client.start();


//        TreeSet<Actor> actors = new TreeSet<Actor>();
//        Actor actor = new Actor();
//        Actor actor1 = new Actor();
//        actor.cntr = 10;
//        actor1.cntr = 25;
//        actors.add(actor1);
//        actors.add(actor);
//        Actor temp = new Actor();
//        temp = actors.last();
//        System.out.println(actors.last().cntr);
//        actors.remove(temp);
//        System.out.println(actors.last().cntr);
////        System.out.println(actors.first());
    }
}