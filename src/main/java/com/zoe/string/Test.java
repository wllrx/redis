package com.zoe.string;

import java.util.UUID;

/**
 * @ClassName : Test
 * @Author : zoe
 * @Date : 2019/6/19 17:17
 */
public class Test {

    public static void main(String[] args) {

        String uuid = UUID.randomUUID().toString();
        char[] cs = new char[32];
        char c = 0;
        for (int i = uuid.length() / 2, j = 1; i-- > 0; ) {
            if ((c = uuid.charAt(i)) != '-') {
                cs[j++] = c;
            }
        }
        String uid = String.valueOf(cs);
        System.out.println(uid);

    }
}


