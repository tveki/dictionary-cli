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
public class MeaningCommandExecutor extends AbstractCommandExecutor {

    public MeaningCommandExecutor(Language fromLanguage, Language toLanguage) {
        super(fromLanguage, toLanguage);
    }

    public MeaningCommandExecutor() {
        super();
    }

    @Override
    public void execute(String[] args) {
        Dictionary dictionary = new GlosbeDictionary();
        TranslateRequest request = prepareRequest();
        request.setPhrase(args[1]);

        TranslateResponse response = dictionary.translate(request);

        System.out.println(response.getFrom().getISO2Code() + " -> " + response.getTo().getISO2Code());

        printMeanings(response);
    }

    private void printMeanings(TranslateResponse response) {
        if (response.getMeanings().isEmpty()) {
            System.out.println("No meanings found for " + response.getPhrase());
        } else {
            System.out.println("-------------------------------------");
            System.out.println("Meanings found for " + response.getPhrase() + ":");
            for (String str : response.getMeanings()) {
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
