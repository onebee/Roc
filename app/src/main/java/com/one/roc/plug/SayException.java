package com.one.roc.plug;

/**
 * @author diaokaibin@gmail.com on 1/2/21.
 */
public class SayException implements ISay{
    @Override
    public String saySomething() {
        return "some thing exception";
    }
}
