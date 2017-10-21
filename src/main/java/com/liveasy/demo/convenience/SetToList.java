package com.liveasy.demo.convenience;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SetToList {

    public static List<Object> convert (Set<Object> set){
        List<Object> list = new ArrayList<Object>(set);
        return list;
    }

}
