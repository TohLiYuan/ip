package commands;

import records.ChatRecord;
import storage.SaveData;
import task.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command{
    public static final String COMMAND_PHRASE = "event";
    private static final String COMMAND_DESC = "New Event Task added to list!";
    private String name;
    private LocalDateTime startDate, endDate;

    public EventCommand(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        Event ev = new Event(name, startDate, endDate);
        this.chatRecord.addTask(ev);
        SaveData.saveData(this.chatRecord.toSave());
        return COMMAND_DESC + " " + ev.toString();
    }
}
