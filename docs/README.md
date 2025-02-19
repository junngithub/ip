# Icarus User Guide ☀️
Have you ever found yourself completing a task way too close to a deadline and wondered, 
"Wow, I wished I had something to remind me". Fret not, Icarus is here to help. 
Icarus is a chatbot that helps you manage your tasks.

## Icarus GUI Preview
[icarus preview](Ui.png)

## Pre-requisites
- Java version 17

## Summary of Features
Icarus allows you to
- add tasks
- view tasks added
- mark and unmark tasks as completed
- delete tasks
- find tasks
- edit syntax preferences
- view syntax preferences

### Adding tasks
Users can categorise tasks into "todo", "deadline" and "event" and add them to a list.
Users have to adhere to specific formats for each task:
- todo: None
- deadline: /by after task description
- event: /from after task description, /to after /from description

Users can specify the date/time or both.

Icarus will then reply in the format
- "I have added:"
- the task added
- number of items in the list 

Example: `todo some task`
Expected output:
```
I have added:

[T][ ] some task
You have 1 item(s) in your list
```

Example: `deadline another task /by 18:00 2025-02-28`
Expected output:
```
I have added:

[D][ ] another task (by: Feb 28 2025, 06:00 pm)
You have 1 item(s) in your list
```

Example: `event this task /from 2025-02-28 18:00 /to 19:00`
Expected output:
```
I have added:

[E][ ] this task (from: Feb 28 2025, 06:00 pm to: 07:00 pm)
You have 1 item(s) in your list
```


### Viewing tasks
Users can view tasks added.

Icarus will reply in the format
- "Here is your list:"
- the list of tasks

Example: `list`
Expected output:
```
Here is your list:

1. [T][ ] some task
2. [D][ ] another task (by: today)
```

### Marking and unmarking tasks
Users can mark/unmark a task as completed, using the index of the task they want to mark/unmark.

Icarus will then reply in the format
- "Very well, I have marked this as completed:" / "Sure, I have marked this as unfinished:"
- the task marked/unmarked

Example: `mark 1`
Expected output:
```
Very well, I have marked this as completed:

[T][X] some task
```

### Deleting tasks
Users can delete tasks from a list, using the index of the task they want to delete.

Icarus will then reply in the format
- "I have removed this item:"
- the task deleted
- number of items in the list

Example: `delete 1`
Expected output:
```
I have removed this item:

[T][ ] some task
You have 1 item(s) in your list
```

### Finding tasks
Users can find tasks that match their description of the task.

Icarus will then reply in the format
- "Here is a list of Tasks that match your query:"
- the list of tasks that match the description

Example: `find task`
Expected output:
```
Here is a list of Tasks that match your query:

1. [T][X] some task
2. [D][ ] another task (by: Feb 28 2025, 06:00 pm)
3. [E][ ] this task (from: Feb 28 2025, 06:00 pm to: 07:00 pm)
```

### Editing syntax preferences 
Users can edit the syntax preference for todo, deadline, event, mark, unmark, list, find and delete commands

Icarus will then reply in the format
- "Here is your updated syntax:"
- the list of syntax mappings

Example: `set todo t`
Expected output:
```
Here is your updated syntax:

todo: t
find: find
deadline: deadline
event: event
list: list
delete: delete
mark: mark
unmark: unmark
```

### Viewing syntax preferences
Users can view their syntax preferences.

Icarus will reply in the format
- "Here's your syntax:"
- the list of syntax mappings
- reminder to adhere to the format for adding task commands

Example: `set todo t`
Expected output:
```
Here's your syntax:

todo: t
find: find
deadline: deadline
event: event
list: list
delete: delete
mark: mark
unmark: unmark

Reminder to please adhere to the following formats: 

todo ...
deadline ... /by ...
event ... /from ... /to
```

### Saying bye to Icarus
Users can say "bye" to Icarus to exit the program. Alternatively, they can close the window (rude!).
In both cases, tasks and syntax preferences will be saved.
