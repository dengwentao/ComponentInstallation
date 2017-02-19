package com.company.command;

/**
 * Created by dengw on 2/17/2017.
 */
public interface ICommand {
    public void execute() throws InvalidCommandException;
}
