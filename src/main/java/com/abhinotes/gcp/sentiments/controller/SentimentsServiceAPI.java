package com.abhinotes.gcp.sentiments.controller;

import com.abhinotes.gcp.sentiments.model.SentimentResponse;
import com.abhinotes.gcp.sentiments.service.SentimentsService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Sentiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@JsonAutoDetect
public class SentimentsServiceAPI {

    @Autowired
    SentimentsService sentimentsService;

    @PostMapping("/sentiments")
    public SentimentResponse getSentimentsForText(@RequestBody String textToAnalyze) {
        Sentiment sentiment  = sentimentsService.getSentiments(textToAnalyze);

        SentimentResponse.SentimentResponseBuilder sentimentResponseBuilder =
                SentimentResponse.builder();

        sentimentResponseBuilder.originalText(textToAnalyze)
                .magnitude(sentiment.getMagnitude())
                .score(sentiment.getScore());

        if (sentiment.getScore() > 0 ) {
            sentimentResponseBuilder.sentiment("Positive");
        } else {
            sentimentResponseBuilder.sentiment("Negative");
        }

        return sentimentResponseBuilder.build();
    }

}
