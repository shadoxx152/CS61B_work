package gh2;

import deque.ArrayDeque;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
//     private LinkedListDeque<Double> buffer;
     private ArrayDeque<Double> buffer;
     private int capacity;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        capacity = (int) Math.round(SR / frequency);

//        buffer = new LinkedListDeque<>();
         buffer = new ArrayDeque<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast((double) 0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.removeFirst();
        }

        for (int i = 0; i < capacity; i++) {
            double randomValue = Math.random() - 0.5;
            buffer.addLast(randomValue);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double x = buffer.removeFirst();
        double y = buffer.get(0);

        double newVal = DECAY * (x + y) / 2.0;
        buffer.addLast(newVal);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
