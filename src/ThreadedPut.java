import store.Pair;
import store.Store;

import java.util.ArrayList;
import java.util.List;

public class ThreadedPut extends Thread {

    private Store store;
    private String key;
    private Pair<String, String> pair;

    public ThreadedPut(Store store, String key, Pair<String, String> pair) {
        this.store = store;
        this.key = key;
        this.pair = pair;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);

            List<Pair<String, String>> list = new ArrayList<>();
            list.add(pair);
            synchronized (store) {
                store.put(key, list);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
