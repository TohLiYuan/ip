package parser;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.*;
import task.Task;

public class CommandParser {
    private static Pattern COMMAND_PATTERN = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static Pattern DEADLINE_ARGS_PATTERN = Pattern.compile("(?<name>\\S+)( \\/by )(?<time>.*)");
    private static Pattern EVENT_ARGS_PATTERN = Pattern.compile("(?<name>\\S+)( \\/from )(?<startTime>.*)( \\/to )(?<endTime>.*)");
    public CommandParser() { }

    public Command parseCommand(String input) {
        Matcher matcher = COMMAND_PATTERN.matcher(input);
        if (!matcher.matches()) {
            return new InvalidCommand("Invalid command format");
        }

        String commandWord = matcher.group("commandWord");
        String args = matcher.group("arguments");

        switch (commandWord) {
            case MarkCommand.COMMAND_PHRASE:
                return new MarkCommand(Integer.parseInt(args.trim()));
            case UnmarkCommand.COMMAND_PHRASE:
                return new UnmarkCommand(Integer.parseInt(args.trim()));
            case ListCommand.COMMAND_PHRASE:
                return new ListCommand();
            case ByeCommand.COMMAND_PHRASE:
                return new ByeCommand();
            case DeleteCommand.COMMAND_PHRASE:
                return new DeleteCommand(Integer.parseInt(args.trim()));
            case TodoCommand.COMMAND_PHRASE:
                return null;
            case EventCommand.COMMAND_PHRASE:
                Matcher evMatcher = EVENT_ARGS_PATTERN.matcher(args.trim());
                return new EventCommand(evMatcher.group("name"), TimeParser.parseTime(evMatcher.group("startTime").trim()), TimeParser.parseTime(evMatcher.group("endTime").trim()));
            case DeadlineCommand.COMMAND_PHRASE:
                Matcher ddlMatcher = DEADLINE_ARGS_PATTERN.matcher(args.trim());
                return new DeadlineCommand(ddlMatcher.group("name"), TimeParser.parseTime(ddlMatcher.group("time").trim()));
        }

        return new InvalidCommand("Command not found");
    }

}
