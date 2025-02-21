package timitomo.enums;

import java.util.HashMap;

import timitomo.exceptions.TimitomoException;
import timitomo.tasks.Deadline;
import timitomo.tasks.Event;
import timitomo.tasks.Task;
import timitomo.tasks.ToDo;

/**
 * Represents the types of tasks that can be created in the chatbot.
 */
public enum TaskType {
    /**
     * Represents a {@code ToDo} task with identifier "T".
     */
    TODO("T") {
        @Override
        public Task deserializeTask(String[] args, boolean isDone) throws TimitomoException {
            if (args.length != 3) {
                throw new TimitomoException("Error: corrupted text.");
            }
            return new ToDo(args[2], isDone);
        }
    },
    /**
     * Represents a {@code Deadline} task with identifier "D".
     */
    DEADLINE("D") {
        @Override
        public Task deserializeTask(String[] args, boolean isDone) throws TimitomoException {
            if (args.length != 4) {
                throw new TimitomoException("Error: corrupted text.");
            }
            return new Deadline(args[2], isDone, args[3]);
        }
    },
    /**
     * Represents an {@code Event} task with identifier "E".
     */
    EVENT("E") {
        @Override
        public Task deserializeTask(String[] args, boolean isDone) throws TimitomoException {
            if (args.length != 5) {
                throw new TimitomoException("Error: corrupted text.");
            }
            return new Event(args[2], isDone, args[3], args[4]);
        }
    };

    private static final HashMap<String, TaskType> dict = new HashMap<>();
    private final String id;

    // Populates the lookup dictionary.
    static {
        for (TaskType type: TaskType.values()) {
            dict.put(type.id, type);
        }
    }

    /**
     * Constructs a {@code TaskType} with the given identifier.
     *
     * @param id The identifier associated with the task type.
     */
    TaskType(String id) {
        this.id = id;
    }

    /**
     * Deserializes a task from its stored string format.
     *
     * @param args The stored task data organized in an array.
     * @param isDone Whether the task is marked as done.
     * @return A {@code Task} object corresponding to the task type.
     * @throws TimitomoException If the expected number of arguments in the array {@code args} is incorrect.
     */
    public abstract Task deserializeTask(String[] args, boolean isDone) throws TimitomoException;

    /**
     * Returns the corresponding identifier for the {@code TaskType}.
     *
     * @return The task type identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the corresponding {@code TaskType} for the specified identifier.
     *
     * @param id The identifier of the task type.
     * @return The corresponding {@code TaskType}, or {@code null}.
     */
    public static TaskType fromId(String id) {
        return dict.get(id);
    }
}
