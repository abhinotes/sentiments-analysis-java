package com.abhinotes.gcp.sentiments.model;

import lombok.Builder;
import lombok.Data;

import java.security.PrivateKey;

@Data
@Builder
public class SentimentResponse {

    private String originalText;
    private String sentiment;
    private float score;
    private float magnitude;

}
