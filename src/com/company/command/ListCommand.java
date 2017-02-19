package com.company.command;

import com.company.Graph;
import com.company.Singleton;

/**
 * Created by dengw on 2/17/2017.
 */
public class ListCommand implements ICommand {

    public ListCommand(String[] params) {
        ;
    }

    public void execute() {
        Graph graph = Singleton.INSTANCE.getInstance();
        for (String comp : graph.listInstalled()) {
            System.out.println("\t" + comp);
        }
    }

}
