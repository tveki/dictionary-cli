/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tveki.dictionary.cli;

import org.tveki.dictionary.api.Language;
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

        if (args.length != 2) {
            System.out.println("Usage: [command] [phrase] (valid commands are 'translate' and 'meaning')");
            return;
        }

        String command = args[0];

        CommandExecutor executor;

        switch (command) {
            case TRANSLATE_COMMAND:
                executor = new TranslateCommandExecutor(Language.GERMAN, Language.HUNGARIAN);
                break;
            case MEANING_COMMAND:
                executor = new MeaningCommandExecutor(Language.GERMAN, Language.HUNGARIAN);
                break;
            default:
                System.out.printf("Unknown command: %s (valid commands are 'translate' and 'meaning')%n", command);
                return;
        }

        executor.execute(args);
    }

}
