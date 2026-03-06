# JMe User Guide

![Java](https://img.shields.io/badge/Java-17+-blue)

JMe is a command-line task management chatbot that helps you keep track of your todos, deadlines, and events. Tasks are saved automatically, so your list is always there when you come back.
```
Hello! I'm JMe!
      _ __  __
     | |  \/  | ___
  _  | | |\/| |/ _ \
 | |_| | |  | |  __/
  \___/|_|  |_|\___|
How may I assist you?
```
---

## Quick Start

1. Ensure you have **Java 17** or above installed.
2. Download the latest `jme.jar`.
3. Open a terminal in the folder containing the jar file and run:
   ```
   java -jar jme.jar
   ```
4. Type a command and press Enter. Type `bye` to exit.

---

## Features

### Adding a todo: `todo`

Adds a simple task with no date attached.

Format: `todo DESCRIPTION`

```
> todo read book
____________________________________________________________
Added: [T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
```

### Adding a deadline: `deadline`

Adds a task with a due date and time.

Format: `deadline DESCRIPTION /by DATE_TIME`

- `DATE_TIME` must be in **yyyy-MM-dd HHmm** format (e.g. `2025-09-30 1800`).

```
> deadline submit report /by 2025-09-30 1800
____________________________________________________________
Added: [D][ ] submit report (by: Sep 30 2025, 6:00PM)
Now you have 2 tasks in the list.
____________________________________________________________
```

### Adding an event: `event`

Adds a task that spans a time range.

Format: `event DESCRIPTION /from START /to END`

```
> event team meeting /from Mon 2pm /to 4pm
____________________________________________________________
Added: [E][ ] team meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
```

### Listing all tasks: `list`

Shows all tasks currently in your list.

Format: `list`

```
> list
____________________________________________________________
Here are your tasks:
1.[T][ ] read book
2.[D][ ] submit report (by: Sep 30 2025, 6:00PM)
3.[E][ ] team meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
```

### Marking a task as done: `mark`

Marks the task at the given index as completed.

Format: `mark INDEX`

```
> mark 1
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] read book
____________________________________________________________
```

### Unmarking a task: `unmark`

Removes the done marker from the task at the given index.

Format: `unmark INDEX`

```
> unmark 1
____________________________________________________________
Ok! I've unmarked this task as done:
  [T][ ] read book
____________________________________________________________
```

### Deleting a task: `delete`

Removes the task at the given index from your list.

Format: `delete INDEX`

```
> delete 3
____________________________________________________________
Deleted: [E][ ] team meeting (from: Mon 2pm to: 4pm)
Now you have 2 tasks in the list.
____________________________________________________________
```

### Finding tasks: `find`

Searches for tasks whose description contains the given keyword (case-insensitive).

Format: `find KEYWORD`

```
> find book
____________________________________________________________
Here are your matching tasks:
1.[T][ ] read book
____________________________________________________________
```

### Exiting: `bye`

Exits the application.

```
> bye
____________________________________________________________
Bye friend! See you soon!
____________________________________________________________
```

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| Todo | `todo DESCRIPTION` | `todo read book` |
| Deadline | `deadline DESCRIPTION /by DATE_TIME` | `deadline essay /by 2025-10-01 2359` |
| Event | `event DESCRIPTION /from START /to END` | `event hackathon /from Sat 9am /to Sun 6pm` |
| List | `list` | `list` |
| Mark | `mark INDEX` | `mark 2` |
| Unmark | `unmark INDEX` | `unmark 2` |
| Delete | `delete INDEX` | `delete 3` |
| Find | `find KEYWORD` | `find essay` |
| Exit | `bye` | `bye` |

## Data Storage

Tasks are saved automatically to `data/jme.txt` after every change. The file and folder are created automatically on first run — no setup needed.