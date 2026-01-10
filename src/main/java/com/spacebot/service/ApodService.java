package com.spacebot.service;

import java.time.LocalDate;

public interface ApodService {

    String getTodayApod();

    String getApodByDate(LocalDate date);

    String getApodNotification();

}
