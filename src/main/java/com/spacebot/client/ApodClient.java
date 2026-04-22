package com.spacebot.client;

import com.spacebot.dto.apod.ApodResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApodClient {

    private final RestClient restClient;
    @Value("${apod.url}")
    private String apodUrl;

    public ApodResponseDTO getApod(LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyMMdd"));
        String url = apodUrl + formattedDate + ".html";
        try {
            String html = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(String.class);
            Document doc = Jsoup.parse(html);
            String title = doc.select("b").first().text();
            String explanation = doc.select("center ~ center ~ p")
                    .text()
                    .replace("Explanation:", "")
                    .trim();
            Element img = doc.select("a[href^=image] img").first();
            String imageUrl = img != null
                    ? "https://apod.nasa.gov/apod/" + img.attr("src")
                    : null;
            Element videoSource = doc.select("video source").first();
            String videoUrl = videoSource != null
                    ? "https://apod.nasa.gov/apod/" + videoSource.attr("src")
                    : null;
            Element iframe = doc.select("iframe").first();
            String iframeUrl = iframe != null
                    ? iframe.attr("src")
                    : null;
            String mediaType;
            String finalUrl;
            if (imageUrl != null) {
                mediaType = "image";
                finalUrl = imageUrl;
            } else if (videoUrl != null) {
                mediaType = "video";
                finalUrl = videoUrl;
            } else {
                mediaType = "other";
                finalUrl = null;
            }
            ApodResponseDTO dto = new ApodResponseDTO();
            dto.setTitle(title);
            dto.setDate(date.toString());
            dto.setExplanation(explanation);
            dto.setUrl(finalUrl);
            dto.setMediaType(mediaType);
            return dto;
        } catch (Exception exception) {
            log.error("Error fetching APOD for date {}: {}", date, exception.getMessage());
            return null;
        }
    }

}
