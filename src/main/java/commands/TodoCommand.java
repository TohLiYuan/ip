package commands;

import records.ChatRecord;
import task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_PHRASE = "todo";
    private String name;
    public TodoCommand(String name) {
        this.name = name;
    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        Todo td = new Todo(this.name);
        this.chatRecord.addTask(td);
        return td.toString();
    }
}
