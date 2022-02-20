package se.magnus.api.composite;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewSummary {
    private int reviewId;
    private String author;
    private String subject;
}
