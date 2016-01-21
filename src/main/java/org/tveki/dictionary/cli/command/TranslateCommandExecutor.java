/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tveki.dictionary.cli.command;

import org.tveki.dictionary.api.Dictionary;
import org.tveki.dictionary.api.Language;
import org.tveki.dictionary.api.TranslateRequest;
import org.tveki.dictionary.api.TranslateResponse;
import org.tveki.glosbe.dictionary.GlosbeDictionary;

/**
 *
 * @author tveki
 */
public class TranslateCommandExecutor implements CommandExecutor {

    public static final Language DEFAULT_FROM_LANGUAGE = Language.ENGLISH;
    public static final Language DEFAULT_TO_LANGUAGE = Language.HUNGARIAN;

    @Override
    public void execute(String[] args) {
        Dictionary dictionary = new GlosbeDictionary();
        TranslateRequest request = prepareDefaultRequest();
        request.setPhrase(args[1]);

        TranslateResponse response = dictionary.translate(request);

        System.out.println(response.getFrom().getISO2Code() + " -> " + response.getTo().getISO2Code());

        printTranslations(response);
    }

    private void printTranslations(TranslateResponse response) {
        if (response.getTranslations().isEmpty()) {
            System.out.println("No translations found for " + response.getPhrase());
        } else {
            System.out.println("-------------------------------------");
            System.out.println("translations found for " + response.getPhrase() + ":");
            for (String str : response.getTranslations()) {
                System.out.println(str);
            }
        }
    }

    private TranslateRequest prepareDefaultRequest() {
        TranslateRequest request = new TranslateRequest();
        request.setFrom(DEFAULT_FROM_LANGUAGE);
        request.setTo(DEFAULT_TO_LANGUAGE);
        return request;
    }

}