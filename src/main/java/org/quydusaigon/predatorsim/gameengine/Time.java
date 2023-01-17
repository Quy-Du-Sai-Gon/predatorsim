package org.quydusaigon.predatorsim.gameengine;

import org.quydusaigon.predatorsim.App;

public class Time {

    private static float deltaTime;
    private static long prevTimeNs;

    public static void reset() {
        deltaTime = 0;
        prevTimeNs = -1;
    }

    static void update(long nowNs) {
        if (nowNs == -1) {
            // for fixed time step
            deltaTime = App.getTimeStep();
            return;
        }

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
