package com.company;

/**
 * Created by dengw on 2/17/2017.
 */
import com.company.command.CommandFactory;
import com.company.command.ICommand;
import com.company.command.InvalidCommandException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/*
 * Jumpstart
 */

/**
 * Class which will read input from the console, and call the appropriate
 * command.
 *
 * @author interview
 */
public class Jumpstart {

    /**
     * Input stream for commands
     */
    private BufferedReader input;

    /**
     * Output stream for results
     */
    private PrintStream output;

    /**
     * Creates a new CommandParser, sending input and output to the specified
     * locations
     *
     * @param in
     *            input stream for commmands
     * @param out
     *            output stream for results
     */
    public Jumpstart(InputStream in, PrintStream out) {
        input = new BufferedReader(new InputStreamReader(in));
        output = out;
    }

    /**
     * Processes a command from user. invalid commands are not printed, and
     * silently ignored. An invalid command includes a command which is missing
     * its argument. For example: "mkdir " is invalid.
     *
     * @param line
     *            line of text representing the command string
     */
    public void processLine(String line) throws InvalidCommandException {
        String command;
        String argument;

        // for demonstration purposes only
        output.println("ECHO> " + line);

        ICommand cmd = CommandFactory.makeCommand(line);
        cmd.execute();
    }

    /**
     * Reads all commands from the input, and executes them
     *
     * @throws IOException
     *             if a read error occurs while parsing commands
     */
    public void process() throws InvalidCommandException, IOException {
        String line = input.readLine();
        while (line != null && line.length() > 0) {
            processLine(line);
            line = input.readLine();
        }
    }

    /**
     * Runs the parser on the supplied test data set. Expects a file in the
     * current working directory. Output is sent to stdout
     *
     * @param args
     *            not used
     */
    public static void main(String[] args) {
        try {
            Jumpstart parser = new Jumpstart(new FileInputStream(
                    "proga.dat"), System.out);
            parser.process();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
