package duke;

import duke.command.Command;
import duke.command.InvalidCommand;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * It represents the Duke task list.
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for duke.
     * @param filepath the relative path to store task list on disk.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.tasks = new TaskList(storage.load());
        this.parser = new Parser();
    }

    /**
     * The main method for the duke task list.
     * Creates an instance of duke and runs it.
     * @param args the arguments that are given when the program is run.
     */
    public static void main(String[] args) {
        Duke chatBot = new Duke("data/data.txt");
        chatBot.run();
    }

    /**
     * The main method to run the duke task list.
     * It waits for user input and parses it into a command and then executes the command and then loops.
     * The loop will only end when the command returns false breaking the while loop.
     */
    public void run() {
        ui.showWelcome();
        boolean shouldRun = true;
        while (shouldRun) {
            System.out.print("Me   : ");
            String message = ui.readCommand();
            ui.showLine();
            try {
                Command command = parser.parseCommand(message);
                shouldRun = command.exec(tasks, ui, storage);
            } catch (DukeException e) {
                shouldRun = new InvalidCommand(e.toString()).exec(tasks, ui, storage);
            }
        }
    }

}
