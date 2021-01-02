package com.one.roc.generic;

/**
 * @author diaokaibin@gmail.com on 12/26/20.
 */
public class Test<T extends String> {
    private T value;


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }


}
