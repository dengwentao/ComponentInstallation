package com.company.command;

import com.company.Graph;
import com.company.Singleton;

/**
 * Created by dengw on 2/17/2017.
 */
public class InstallCommand implements ICommand {

    String component;

    public InstallCommand(String[] params) throws InvalidCommandException {
        if (params.length != 2) {
            throw new InvalidCommandException("Invalid InstallCommand: only allows 2 parameters.");
        }

        component = params[1];
    }

    public void execute() throws InvalidCommandException {
        Graph graph = Singleton.INSTANCE.getInstance();
        graph.install(component, true);
    }
}
