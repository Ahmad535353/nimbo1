import java.util.Comparator;

/**
 * Created by QotboddiniH on 7/29/17.
 */
public class Actor implements Comparator<Actor> , Comparable<Actor>{
    public long id;
    public long cntr = 0;
    public String login;

    Actor () {
        cntr = 0;
    }

    @Override
    public int compare(Actor o1, Actor o2)
    {
        return (int) (o1.cntr - o2.cntr);
    }

    @Override
    public int compareTo(Actor o) {
        return (int) (this.cntr - o.cntr);
    }
}
