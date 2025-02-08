package Icarus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Tasks.Task;
import Tasks.ToDos;

public class ToDoTest {
    @Test
    public void testToDosCreation() {
        String taskDescription = "Complete homework";
        Task todo = ToDos.of(taskDescription);

        assertNotNull(todo, "The ToDos object should not be null");
        assertTrue(todo instanceof ToDos, "The object should be an instance of ToDos");
    }

    @Test
    public void testToStringFormat() {
        String taskDescription = "Complete homework";
        Task todo = ToDos.of(taskDescription);

        assertEquals("[T][ ] Complete homework", todo.toString(),
                "The toString() method should return the correct formatted string");
    }

}
