# 🚀 Space Events Bot

A Telegram bot that provides up-to-date information about space-related events such as rocket launches, near-Earth asteroids, space news, and NASA's Astronomy Picture of the Day (APOD).

---

## ✨ Features

* 🚀 **Upcoming Launches** — information about upcoming rocket launches
* ☄️ **Asteroids** — near-Earth objects approaching our planet
* 📰 **Space News** — latest news from the space industry
* 🪐 **Astronomy Picture of the Day (APOD)**

    * APOD for today
    * APOD for a specific date
    * daily APOD notifications via subscription

---

## 🤖 Bot Commands

```text
/start                     — open the main menu
/help                      — show help information
/apod                      — Astronomy Picture of the Day (today)
/apod DD.MM.YYYY           — APOD for a specific date
/launches                  — upcoming rocket launches
/asteroids                 — near-Earth asteroids
/news                      — latest space news
/subscribe_apod            — subscribe to daily APOD notifications
/unsubscribe_apod          — unsubscribe from daily APOD notifications
```

---

## 🧠 Architecture

The project follows a layered architecture:

```text
Command / Action
        ↓
      Service
        ↓
      Client (external APIs)
```

* **Command** — handles Telegram text commands
* **Action** — handles callback queries from inline buttons
* **Service** — business logic
* **Client** — communication with external APIs

Enums are used for both commands and callback actions to avoid magic strings and keep the code consistent.

---

## ⚡ Caching

The project uses **Spring Cache** with an in-memory cache:

* APOD responses are cached by date
* reduces the number of calls to the NASA API
* helps avoid hitting API rate limits

> Note: the cache is in-memory and is cleared on application restart.

---

## ⏰ Scheduler

A scheduler is implemented for APOD subscriptions:

* periodically checks subscribed users
* sends the Astronomy Picture of the Day
* reuses cached data when available

---

## 🛠 Tech Stack

* Java 21
* Spring Boot
* Spring Cache (in-memory)
* Telegram Bots API
* REST Client
* Lombok
* Gradle

---

## 🔐 Environment Variables

To run the application, you need to set the following environment variables:

```env
TELEGRAM_BOT_TOKEN=your_telegram_bot_token
NASA_API_KEY=your_nasa_api_key
```

---

## ▶️ How to Run Locally

1. Clone the repository:

```bash
git clone https://github.com/m685319/space-events-bot.git
cd space-events-bot
```

2. Set environment variables:

```bash
export TELEGRAM_BOT_TOKEN=your_telegram_bot_token
export NASA_API_KEY=your_nasa_api_key
```

(Or configure them in your IDE run configuration.)

3. Build and run the application:

```bash
./gradlew bootRun
```

4. Open Telegram and start a chat with your bot.

---

## 🚧 Current Limitations

* subscriptions are stored in memory
* cache is in-memory and does not persist between restarts

These limitations are acceptable for the current MVP version and can be improved later.

---

## 📈 Possible Improvements

* persistent storage for subscriptions
* distributed caching (e.g. Redis)
* inline navigation buttons for APOD
* unit tests for the service layer
* cloud deployment

---

Clear skies and happy exploring ✨