package ebike.core.def;

public class ActionReturn<T> {

    private String message;

    private T value;

    public ActionReturn(T value) {
        this(value, "");
    }

    public ActionReturn(T value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public T getValue() {
        return this.value;
    }
}
