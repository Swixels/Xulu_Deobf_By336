package com.elementars.eclient.util;

import com.elementars.eclient.*;
import java.util.*;

public class TaskScheduler
{
    private final /* synthetic */ Queue<Runnable> prioritizedTasks;
    /* synthetic */ int delay;
    private final /* synthetic */ Queue<Runnable> tasks;
    
    public void onUpdate() {
        if (this.delay > 0) {
            --this.delay;
        }
        if (this.prioritizedTasks.peek() != null) {
            this.prioritizedTasks.remove().run();
            this.delay = Xulu.VALUE_MANAGER.getValueByName("Offhand Delay").getValue();
            return;
        }
        if (this.tasks.peek() != null && this.delay == 0) {
            this.tasks.remove().run();
            this.delay = Xulu.VALUE_MANAGER.getValueByName("Offhand Delay").getValue();
        }
    }
    
    public void addTask(final Runnable llIlllIIIllIIl) {
        this.tasks.add(llIlllIIIllIIl);
    }
    
    public void addPrioritizedTask(final Runnable llIlllIIIlIlIl) {
        this.prioritizedTasks.add(llIlllIIIlIlIl);
    }
    
    public TaskScheduler() {
        this.tasks = new LinkedList<Runnable>();
        this.prioritizedTasks = new LinkedList<Runnable>();
    }
}
