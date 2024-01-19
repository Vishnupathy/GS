package com.GreenStitch.CampusPlacements.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private String timestamp;
    private String message;
    private String details;
}
