package com.muzammilpeer.utilitylayer.gesture;

/**
 * Created by muzammilpeer on 15/07/2015.
 */
public class GestureUtil {
    public static boolean isPointWithin(int x, int y, int x1, int x2, int y1, int y2) {
        return (x <= x2 && x >= x1 && y <= y2 && y >= y1);
    }
}
