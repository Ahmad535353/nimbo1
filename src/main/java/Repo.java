import java.util.Comparator;

/**
 * Created by QotboddiniH on 7/29/17.
 */
public class Repo implements Comparable<Repo> , Comparator<Repo>{
    public String name;
    public long cntr = 0;
    public long id;

    public Repo () {
        cntr = 0;
    }

    @Override
    public int compareTo(Repo o) {
        return (int) (this.cntr - o.cntr);
    }

    @Override
    public int compare(Repo o1, Repo o2) {
        return (int) (o1.cntr - o2.cntr);
    }
}
