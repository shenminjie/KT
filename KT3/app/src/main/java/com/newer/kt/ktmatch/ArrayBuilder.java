package com.newer.kt.ktmatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by litli on 2017/3/7.
 */

public class ArrayBuilder<T> {
    public static ArrayBuilder create(){
        return new ArrayBuilder();
    }
    List<T> array = new ArrayList<T>();
    public ArrayBuilder add(T option){
        array.add(option);
        return this;
    }
    public List<T> get(){
        return array;
    }
}
