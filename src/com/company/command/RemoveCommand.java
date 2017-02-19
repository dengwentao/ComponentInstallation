package com.company.command;

import com.company.Graph;
import com.company.Singleton;

/**
 * Created by dengw on 2/17/2017.
 */
public class RemoveCommand implements ICommand {

    String component;

    public RemoveCommand(String[] params) throws InvalidCommandException {
        if (params.length != 2) {
            throw new InvalidCommandException("Invalid RemoveCommand: only allows 2 parameters.");
        }

        component = params[1];
    }

    public void execute() throws InvalidCommandException {
        Graph graph = Singleton.INSTANCE.getInstance();
        graph.remove(component, true);
    }

}
