package org.proleesh.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class OutputMessage {
    private Instant time;
    private String content;
}
