public class MarkCommand extends Command {

    int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        Task markedTask = taskList.mark(index);
        storage.saveUpdatedTask(index, markedTask);
        return true;
    }
}
