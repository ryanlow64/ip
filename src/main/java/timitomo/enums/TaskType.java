package timitomo.enums;

import timitomo.exceptions.TimitomoException;
import timitomo.tasks.Deadline;
import timitomo.tasks.Event;
import timitomo.tasks.Task;
import timitomo.tasks.ToDo;

import java.util.HashMap;

public enum TaskType {
    TODO("T") {
        @Override
        public Task deserializeTask(String[] args, boolean isDone) throws TimitomoException {
            if (args.length != 3) {
                throw new TimitomoException("Error: corrupted text.");
            }
            return new ToDo(args[2], isDone);
        }
    },
    DEADLINE("D") {
        @Override
        public Task deserializeTask(String[] args, boolean isDone) throws TimitomoException {
            if (args.length != 4) {
                throw new TimitomoException("Error: corrupted text.");
            }
            return new Deadline(args[2], isDone, args[3]);
        }
    },
    EVENT("E") {
        @Override
        public Task deserializeTask(String[] args, boolean isDone) throws TimitomoException {
            if (args.length != 5) {
                throw new TimitomoException("Error: corrupted text.");
            }
            return new Event(args[2], isDone, args[3], args[4]);
        }
    };

    private final String id;
    private static final HashMap<String, TaskType> dict = new HashMap<>();

    static {
        for (TaskType type: TaskType.values()) {
            dict.put(type.id, type);
        }
    }

    TaskType(String id) {
        this.id = id;
    }

    public abstract Task deserializeTask(String[] args, boolean isDone) throws TimitomoException;

    public String getId() {
        return id;
    }

    public static TaskType fromId(String id) {
        return dict.get(id);
    }
}