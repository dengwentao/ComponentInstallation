package com.company.command;

/**
 * Created by dengw on 2/17/2017.
 */
public class CommandFactory {
    static public ICommand makeCommand(String command) throws InvalidCommandException {
        String[] params = command.split("\\s+");
        if (params == null || params.length == 0) {
            throw new InvalidCommandException("Invalid command: " + command);
        }

        switch (params[0]) {
            case "DEPEND":
                return new DependencyCommand(params);
            case "INSTALL":
                return new InstallCommand(params);
            case "REMOVE":
                return new RemoveCommand(params);
            case "LIST":
                return new ListCommand(params);
            case "END":
                return new EndCommand(params);
            default:
                throw new InvalidCommandException("Invalid command: " + command);
        }
    }
}
