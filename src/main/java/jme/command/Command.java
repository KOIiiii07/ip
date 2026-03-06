package jme.command;

import jme.task.TaskList;
import jme.storage.Storage;
import jme.ui.Ui;

/**
 * Abstract base class for all JMe commands.
 * Each command encapsulates an action that operates on the task list,
 * UI, and storage components.
 */
public abstract class Command {

    /**
     * Executes this command.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI component for user interaction.
     * @param storage The storage component for saving/loading tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether this command signals the application to exit.
     *
     * @return {@code true} if this is an exit command, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}