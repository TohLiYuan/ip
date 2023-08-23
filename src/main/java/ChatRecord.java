import task.*;
import java.util.ArrayList;

public class ChatRecord {
    private ArrayList<Task> chatRecords;
    private int counter;
    public ChatRecord() {
         chatRecords = new ArrayList<>();
         counter = 0;
    }

    public Task addTask(String name, TaskTypes type) {
        Task ret = new Todo(name);
        chatRecords.add(ret);
        counter++;
        return ret;
    }

    public Task addTask(String name, TaskTypes type, String args) {
        Task ret = new Deadline(name, args);
        chatRecords.add(ret);
        counter++;
        return ret;
    }

    public Task addTask(String name, TaskTypes type, String[] args) {
        Task ret = new Event(name, args[0], args[1]);
        chatRecords.add(ret);
        counter++;
        return ret;
    }

    public String listMessage() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < chatRecords.size(); i++) {
            ret.append(String.format("\t%d. %s\n", i + 1, chatRecords.get(i).toString()));
        }
        return ret.toString().stripTrailing();
    }

    public int getCount() {
        return counter;
    }

    public void setMark(int n) {
        chatRecords.get(n - 1).mark();
    }

    public void setUnmark(int n) {
        chatRecords.get(n - 1).unmark();
    }

    public String getTask(int n) {
        return chatRecords.get(n - 1).toString();
    }
}
