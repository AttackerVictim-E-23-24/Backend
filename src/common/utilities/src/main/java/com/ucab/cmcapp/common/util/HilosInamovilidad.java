package com.ucab.cmcapp.common.util;

import java.util.ArrayList;
import java.util.List;

public class HilosInamovilidad {

    private static List<Thread> threads = new ArrayList<>();

    public static void registerThread(Thread thread) {
        threads.add(thread);
    }

    public static void unregisterThread(Thread thread) {
        threads.remove(thread);
    }

    public static List<Thread> getThreads() {
        return threads;
    }
}
