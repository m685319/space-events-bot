package com.spacebot.service;

import com.spacebot.dto.apod.ApodResponseDTO;

public interface ApodService {

    ApodResponseDTO getTodayApod();

    String formatApodMessage(ApodResponseDTO apod);

}
