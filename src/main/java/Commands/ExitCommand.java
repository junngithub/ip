package Commands;

import java.io.IOException;

import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;

public class ExitCommand extends Command {
    public ExitCommand () {}

    public void execute(TaskManager taskManager, UI ui, Storage store) throws IOException {
        store.updateSave(taskManager.getList());
        String bye = "Bye! See you next time, my friend.\n";
        System.out.println(bye);
    }

    @Override
    public boolean isExiting() {
        return true;
    }
}
