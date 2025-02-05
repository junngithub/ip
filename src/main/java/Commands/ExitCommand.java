package Commands;

import java.io.IOException;

import Essentials.Parser;
import Essentials.Storage;
import Essentials.TaskManager;
import Essentials.UI;

public class ExitCommand extends Command {
    public ExitCommand () {}

    public void execute(TaskManager taskManager, UI ui, Parser parser,  Storage store)
            throws IOException {
        store.updateSave(taskManager.getList());
        String bye = "Bye! See you next time, my friend.";
        System.out.println(bye);
    }

    @Override
    public boolean isExiting() {
        return true;
    }
}
