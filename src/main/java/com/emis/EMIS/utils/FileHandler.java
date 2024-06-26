package com.emis.EMIS.utils;

import org.springframework.http.MediaType;

public class FileHandler {
    private boolean isValidMediaType(MediaType mediaType) {
        return mediaType.isCompatibleWith(MediaType.IMAGE_JPEG) ||
                mediaType.isCompatibleWith(MediaType.IMAGE_PNG) ||
                mediaType.isCompatibleWith(MediaType.APPLICATION_PDF) ||
                mediaType.isCompatibleWith(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) ||
                mediaType.isCompatibleWith(MediaType.parseMediaType("application/vnd.ms-excel"));
    }
}
