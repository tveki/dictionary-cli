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

import java.util.Objects;

/**
 *
 * @author tveki
 */
public class TranslateCommandExecutor extends AbstractCommandExecutor {

    public TranslateCommandExecutor(Language fromLanguage, Language toLanguage) {
        super(fromLanguage, toLanguage);
    }

    public TranslateCommandExecutor() {
        super();
    }

    @Override
    public void execute(String[] args) {
        Dictionary dictionary = new GlosbeDictionary();
        TranslateRequest request = prepareRequest();
        request.setPhrase(args[1]);

        TranslateResponse response = dictionary.translate(request);

        System.out.printf("%s -> %s%n", response.getFrom().getISO2Code(), response.getTo().getISO2Code());

        printTranslations(response);
    }

    private void printTranslations(TranslateResponse response) {
        if (response.getTranslations().isEmpty()) {
            System.out.printf("No translations found for %s%n", response.getPhrase());
        } else {
            System.out.println("-------------------------------------");
            System.out.printf("Translations found for %s:%n", response.getPhrase());
            for (String str : response.getTranslations()) {
                System.out.println(str);
            }
        }
    }

    private TranslateRequest prepareRequest() {
        TranslateRequest request = new TranslateRequest();
        request.setFrom(fromLanguage);
        request.setTo(toLanguage);
        return request;
    }

}
