package icarus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import essentials.Parser;
import essentials.TaskManager;
import exceptions.InvalidInputException;

public class ParserTest {

    private final Parser parser = new Parser();
    private final TaskManager taskManager = new TaskManager();

    @Test
    public void testParseByeCommand() {
        Command command = parser.parseCommand("bye");
        assertNotNull(command);
        assertInstanceOf(ExitCommand.class, command,
                "Command should be an instance of ExitCommand");
    }

    @Test
    public void testParseListCommand() {
        Command command = parser.parseCommand("list");
        assertNotNull(command);
        assertInstanceOf(ListCommand.class, command,
                "Command should be an instance of ListCommand");
    }

    @Test
    public void testParseMarkCommand() {
        Command command = parser.parseCommand("mark 1");
        assertNotNull(command);
        assertInstanceOf(MarkCommand.class, command,
                "Command should be an instance of MarkCommand");
    }

    @Test
    public void testParseUnmarkCommand() {
        Command command = parser.parseCommand("unmark 2");
        assertNotNull(command);
        assertInstanceOf(UnmarkCommand.class, command,
                "Command should be an instance of UnmarkCommand");
    }

    @Test
    public void testParseDeleteCommand() {
        Command command = parser.parseCommand("delete 3");
        assertNotNull(command);
        assertInstanceOf(DeleteCommand.class, command,
                "Command should be an instance of DeleteCommand");
    }

    @Test
    public void testParseFindCommand() {
        Command command = parser.parseCommand("find homework");
        assertNotNull(command);
        assertInstanceOf(FindCommand.class, command,
                "Command should be an instance of FindCommand");
    }

    @Test
    public void testParseInvalidCommand() {
        Command command = parser.parseCommand("invalidCommand 1");
        assertInstanceOf(InvalidCommand.class, command, "Command should be instance of InvalidCommand");
    }

    @Test
    public void testParseDateFirstFormat() throws InvalidInputException {
        String userInput = "2025-02-08 14:00";
        String formattedTime = parser.parseTime(userInput);
        assertEquals("Feb 8 2025, 02:00 pm", formattedTime,
                "The time should be parsed and formatted correctly");
    }

    @Test
    public void testParseTimeFirstFormat() throws InvalidInputException {
        String userInput = "14:00 2025-02-08";
        String formattedTime = parser.parseTime(userInput);
        assertEquals("Feb 8 2025, 02:00 pm", formattedTime,
                "The time should be parsed and formatted correctly");
    }

    @Test
    public void testParseInvalidTimeFormat() {
        String userInput = "2025-02-08 25:00";
        assertThrows(InvalidInputException.class, () -> parser.parseTime(userInput),
                "InvalidInputException should be thrown for invalid time");
    }

    @Test
    public void testParseInvalidDateFormat() {
        String userInput = "2025-02-31";
        assertThrows(InvalidInputException.class, () -> parser.parseTime(userInput),
                "InvalidInputException should be thrown for invalid date");
    }

    @Test
    public void testParseFromNonExistentFile() {
        File file = new File("tasks.txt");
        assertThrows(IOException.class, () -> parser.parseFromFile(file, taskManager),
                "IOException should be thrown for invalid file path");
    }

}
