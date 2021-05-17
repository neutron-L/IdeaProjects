package cn.edu.bupt.common.utils;

import java.util.Random;

public class UUIDtools {
    public static String createTimeID(){
        //String uuid = UUID.randomUUID().toString();
        long l = System.currentTimeMillis();
        String s = String.valueOf(l);
        Random random = new Random();
        int i = random.nextInt(10000);
        return s + i;
    }
}
