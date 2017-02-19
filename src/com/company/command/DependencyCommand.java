package com.company.command;

import com.company.Graph;
import com.company.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengw on 2/17/2017.
 */
public class DependencyCommand implements ICommand {

    String component;
    List<String> dependOn;

    public DependencyCommand(String[] params) throws InvalidCommandException {
        if (params.length < 3) {
            throw new InvalidCommandException("Invalid DependencyCommand: parameters less than 3.");
        }

        component = params[1];
        dependOn = new ArrayList<>();
        for (int i = 2; i < params.length; i ++) {
            dependOn.add(params[i]);
        }
    }

    public void execute() {
        Graph graph = Singleton.INSTANCE.getInstance();
        graph.makeDependencies(component, dependOn);
    }
}
