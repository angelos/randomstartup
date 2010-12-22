package net.luminis.tools.randomstartup;

import java.io.PrintStream;

import org.apache.felix.shell.Command;

public class RandomStartupCommand implements Command {
    private final RandomStartup m_randomStartup;

    public RandomStartupCommand(RandomStartup randomStartup) {
        m_randomStartup = randomStartup;
    }

    public void execute(String command, PrintStream out, PrintStream err) {
        String[] args = command.split(" ");
        if (!args[0].equals(getName())) {
            return;
            // this command is not for us...
        }
        if (args.length < 2) {
            out.println("Not enough arguments given. Syntax: " + getUsage());
            return;
        }
        
        int seed;
        try {
            seed = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException nfe) {
            err.println(args[1] + " is not a valid number.");
            return;
        }
        
        m_randomStartup.randomStartup(seed, out, err);
    }

    public String getName() {
        return "startrandom";
    }

    public String getShortDescription() {
        return "Starts all non-active bundles in the framework in a random order," +
               " seeded by the number given to the command.";
    }

    public String getUsage() {
        return "startrandom <nr>";
    }

}
