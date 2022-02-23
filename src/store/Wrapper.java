package store;

import java.lang.reflect.Type;

public class Wrapper<T> {

    private T val;

    private final Class<T> type;


    public Wrapper(Class<T> type) {

        this.type = type;
    }


    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public Type getType() {
        return type;
    }
}
