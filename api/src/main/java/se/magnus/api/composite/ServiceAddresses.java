package se.magnus.api.composite;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceAddresses {
    private String compositeAddress;
    private String productAddress;
    private String reviewAddress;
    private String recommendationAddress;
}
