package com.spacebot.service;

import com.spacebot.dto.apod.ApodResponseDTO;

import java.time.LocalDate;

public interface ApodService {

    ApodResponseDTO getTodayApod();

    ApodResponseDTO getApodByDate(LocalDate date);

    String formatApodMessage(ApodResponseDTO apod);

}
