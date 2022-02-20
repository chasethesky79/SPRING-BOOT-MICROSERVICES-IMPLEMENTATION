package se.magnus.api.composite;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecommendationSummary {
    private int recommendationId;
    private String author;
    private int rate;
}
