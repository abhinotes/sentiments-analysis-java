package com.abhinotes.gcp.sentiments.service;

import com.google.cloud.language.v1.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SentimentsService {

    public Sentiment getSentiments(String textToAnalyze) {

        try (LanguageServiceClient languageServiceClient = LanguageServiceClient.create()) {

            Document document = Document.newBuilder().setContent(textToAnalyze)
                    .setType(Document.Type.PLAIN_TEXT).build();

           Sentiment sentiment = languageServiceClient.analyzeSentiment(document).getDocumentSentiment();

            return sentiment;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
