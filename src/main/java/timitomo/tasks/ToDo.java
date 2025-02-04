package timitomo.tasks;

import timitomo.enums.TaskType;

public class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }
}
