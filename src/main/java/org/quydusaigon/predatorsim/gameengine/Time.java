package org.quydusaigon.predatorsim.gameengine;

public class Time {

    private static float deltaTime = 0;
    private static long prevTimeNs = -1;

    static void update(long nowNs) {
        if (prevTimeNs != -1) {
            // update deltaTime for all frames except the first one
            Time.deltaTime = (nowNs - prevTimeNs) * 1E-9f;
        }
        prevTimeNs = nowNs;
    }

    public static float getDeltaTime() {
        return deltaTime;
    }

}
