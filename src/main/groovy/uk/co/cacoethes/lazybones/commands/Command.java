package uk.co.cacoethes.lazybones.commands;

import groovy.util.ConfigObject;

import java.util.List;
import java.util.Map;

/**
 * An implementation of a sub-command for Lazybones.
 */
public interface Command {
    String getName();
    String getDescription();
    String getHelp(String message);
    int execute(List<String> args, Map globalOptions, ConfigObject config);
}
