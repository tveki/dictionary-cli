/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tveki.dictionary.cli;

import org.tveki.dictionary.cli.command.CommandExecutor;
import org.tveki.dictionary.cli.command.MeaningCommandExecutor;
import org.tveki.dictionary.cli.command.TranslateCommandExecutor;

/**
 *
 * @author tveki
 */
public class DictionaryMain {

    private static final String TRANSLATE_COMMAND = "translate";
    private static final String MEANING_COMMAND = "meaning";

    public static void main(String... args) {

        if (args.length == 0) {
            System.out.println("Usage: translate [phrase] or meaning [phrase]");
            return;
        }

        String command = args[0];

        CommandExecutor executor = null;

        switch (command) {
            case TRANSLATE_COMMAND:
                executor = new TranslateCommandExecutor();
                break;
            case MEANING_COMMAND:
                executor = new MeaningCommandExecutor();
                break;
            default:
                System.out.printf("Unknown command: %s%n", command);
                return;
        }

        executor.execute(args);
    }

}
