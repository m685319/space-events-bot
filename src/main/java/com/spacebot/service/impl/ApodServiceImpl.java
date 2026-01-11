package com.spacebot.service.impl;

import com.spacebot.client.ApodClient;
import com.spacebot.dto.apod.ApodResponseDTO;
import com.spacebot.service.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class ApodServiceImpl implements ApodService {

    private final ApodClient client;
    private static final LocalDate FIRST_APOD_DATE = LocalDate.of(1995, 6, 16);
    private static final DateTimeFormatter HUMAN_DATE = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    @Override
    public String getTodayApod() {
        ApodResponseDTO response = fetchApod(LocalDate.now());
        return formatInformation(response);
    }

    @Override
    public String getApodByDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("APOD isn’t available for future dates (no time travel yet 😁). If you’d like, you can subscribe to daily updates using /subscribe_apod and get the new APOD every day at 5:00 AM UTC.");
        }
        if (date.isBefore(FIRST_APOD_DATE)) {
            throw new IllegalArgumentException("The first APOD was published on June 16, 1995. Want to see how it all began?");
        }
        ApodResponseDTO response = fetchApod(date);
        return formatInformation(response);
    }

    @Override
    public String getApodNotification() {
        ApodResponseDTO response = fetchApod(LocalDate.now());
        return formatNotificationInformation(response);
    }

    @Cacheable(value = "apod", key = "#date", unless = "#result == null")
    public ApodResponseDTO fetchApod(LocalDate date) {
        ApodResponseDTO response = client.getApod(date);
        if (response == null) {
            String strDate = date.format(HUMAN_DATE);
            throw new IllegalStateException(String.format("APOD not published for %s", strDate));
        }
        return response;
    }

    private String formatInformation(ApodResponseDTO apod) {
        LocalDate apodDate = LocalDate.parse(apod.getDate());
        if(apodDate.isEqual(LocalDate.now())) {
            return """
                🪐 Astronomy Picture of the Day: %s

                %s

                🔗 %s

                ℹ️ Tip: Use /apod DD.MM.YYYY to view APOD for a specific date (e.g. /apod 16.06.1995).
                
                🔔 Want daily notifications?
                   Use /subscribe_apod to get APOD every day at 5:00 AM UTC.
                """.formatted(
                    apod.getTitle(),
                    apod.getExplanation(),
                    apod.getUrl()
            );
        } else {
            return """
                    🪐 %s was published on %s
                    
                    %s
                    
                    🔗 %s
                    
                    ℹ️ Tip: Use /apod to view APOD for today.
                    
                    🔔 Want daily notifications?
                       Use /subscribe_apod to get APOD every day at 5:00 AM UTC.
                    """.formatted(
                    apod.getTitle(),
                    apodDate.format(HUMAN_DATE),
                    apod.getExplanation(),
                    apod.getUrl()
            );
        }
    }

    private String formatNotificationInformation(ApodResponseDTO apod) {
        return """
                🪐 Astronomy Picture of the Day is live!

                🔗 %s

                ℹ️ Tip: Use /apod to view APOD details or /unsubscribe_apod to stop notifications.
                """.formatted(
                apod.getUrl()
        );
    }

}
