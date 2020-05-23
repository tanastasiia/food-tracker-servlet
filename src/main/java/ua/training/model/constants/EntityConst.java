package ua.training.model.constants;

import java.util.function.Function;

public interface EntityConst<T> {
    public String getColumn();
    public String getField();
    public Function<T, ?> fieldGetter();
}
