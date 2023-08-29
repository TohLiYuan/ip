package commands;

import records.ChatRecord;

public class ListCommand extends Command {
    public static final String COMMAND_PHRASE = "list";
    public ListCommand() {

    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        return chatRecord.listMessage();
    }
}
