package org.quydusaigon.predatorsim.gameengine;

import org.quydusaigon.predatorsim.App;
import org.quydusaigon.predatorsim.gameengine.component.Behaviour;
import org.quydusaigon.predatorsim.util.Parameter;

/**
 * This class provides static methods to access data about time. This is useful
 * for {@link Behaviour} scripts that needs information about time between each
 * frame.
 */
public class Time {

    private static float deltaTime;
    private static long prevTimeNs;

    private static float sliderValue = 1f;

    public static void setSliderValue(float sliderValue) {
        Time.sliderValue = sliderValue;
    }





    static {
        reset();
    }

    /**
     * Resets the internal state of the time information system to avoid the system
     * being left in a bad and stale state due to insufficient internal update by
     * the {@link GameLoop}; i.e., when the game loop is first started or after the
     * game loop has been stopped for a while and restarted again.
     */
    static void reset() {
        deltaTime = 0;
        prevTimeNs = -1;
    }

    /**
     * Updates the internal time information with new data.
     * 
     * @param nowNs new current timestamp in nanoseconds or {@code -1} to indicate a
     *              move in {@linkplain App#getTimeStep() time step}.
     */
    static void update(long nowNs) {
        if (nowNs == -1) {
            // for fixed time step
            deltaTime = Parameter.getTimeStep();
            return;
        }

        if (prevTimeNs != -1) {
            // update deltaTime for all frames except the first one
            Time.deltaTime = (nowNs - prevTimeNs) * 1E-9f;
        }
        prevTimeNs = nowNs;
    }

    /**
     * Returns the elapsed time in seconds between the current frame and the
     * previous frame while the game loop is running.
     *
     * <p>
     * Alternatively, returns {@link App#getTimeStep()} if the last time the
     * {@code Time} system was updated was with the fixed time step specified. See
     * {@link GameLoop#handle(long)}.
     * 
     * @return the elapsed time in seconds between the current frame and the
     *         previous frame.
     * @see GameLoop
     */
    public static float getDeltaTime() {
        return deltaTime * sliderValue;
    }

}
