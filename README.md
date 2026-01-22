# 🚀 Space Events Bot 🌌
Space Events Bot is an open-source Telegram bot that keeps you updated with everything happening beyond Earth 🌍. From
rocket launches and near-Earth asteroids to space news and NASA’s Astronomy Picture of the Day (APOD) — all in one
place, right inside Telegram. Built for space enthusiasts, students, developers, and anyone curious about the universe ✨

👉 Try the bot now: [Space Events Bot](https://telegram.me/space_events_bot)

---

## 🌟 Why Space Events Bot? 
* 🔭 Stay informed about real-world space events
* 🚀 No websites, no apps; just Telegram
* ⚡ Fast responses with smart caching
* 🧑‍💻 Open-source & contributor-friendly
* ☁️ Deployed on a Hostinger Virtual Machine (VM) for 24×7 availability

---

## ✨ Features
* 🚀 **Upcoming Launches**
    * Information about upcoming rocket launches
* ☄️ **Asteroids**
    * Near-Earth objects approaching our planet
* 📰 **Space News**
    * Latest news from the space industry
* 🪐 **Astronomy Picture of the Day (APOD)**
    * APOD for today
    * APOD for a specific date
    * Daily APOD notifications at 00:10 UTC

---

## 🤖 Bot Commands
```text
/start               — Open the main menu
/help                — Show help information
/apod                — Astronomy Picture of the Day (APOD) for today
/apod DD.MM.YYYY     — Astronomy Picture of the Day (APOD) for a specific date
/subscribe_apod      — Subscribe to daily APOD notifications
/unsubscribe_apod    — Unsubscribe from daily APOD notifications
/launches            — Upcoming rocket launches
/asteroids           — Approaching Near-Earth asteroids
/news                — Latest space news
```
> ⏰ Two consecutive requests should be made at a gap of minimum 10 seconds.

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
* **Command**
  * Handles Telegram text commands
* **Action**
  * Handles callback queries from inline buttons
* **Service**
  * Contains business logic
* **Client**
  * Handles communication with external APIs using REST

✅ Enums are used for both commands and callback actions to avoid magic strings and keep the code consistent.

---

## ⚡ Caching
The project uses **Spring Cache** with an in-memory cache:
* APOD responses are cached by date
* Reduces the number of calls to the NASA API
* Helps avoid hitting API rate limits

> ℹ️ The cache is in-memory and is cleared on application restart.

---

## 🔔 Scheduler
A scheduler is implemented for APOD subscriptions:
* Periodically checks subscribed users
* Sends the Astronomy Picture of the Day at 00:10 UTC
* Reuses cached data when available

---

## ☁️ Deployment
* Hosted on a Hostinger Virtual Machine (VM)
* Runs as a long-living service
* Always online and accessible via Telegram

---

## 🛠️ Tech Stack
* [![Java](https://img.shields.io/badge/Java-21-007396?logo=java&logoColor=white)](https://www.java.com)
* [![Spring Boot](https://img.shields.io/badge/Spring_Boot-4-6DB33F?logo=springboot&logoColor=white)](https://spring.io)
* [![Gradle](https://img.shields.io/badge/Gradle-Build-02303A?logo=gradle&logoColor=white)](https://gradle.org)
* [![Telegram](https://img.shields.io/badge/Telegram-Bot-26A5E4?logo=telegram&logoColor=white)](https://telegram.org)
* [![Git](https://img.shields.io/badge/Git-VCS-F05032?logo=git&logoColor=white)](https://github.com)
* [![Hostinger](https://img.shields.io/badge/Hostinger-VM-673DE6)](https://www.hostinger.com)
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

## 🤝 Contributing
Contributions are highly welcome ❤️
* 🐛 Found a bug? → Open an issue
* 💡 Have an idea or improvement? → Raise a PR
* 🔧 Want to refactor or optimize? → Raise a PR

Anyone can contribute — whether it’s code, documentation, or suggestions.

---

## 🚧 Current Limitations
* Subscriptions are stored in File System
* Cache is in-memory and does not persist between restarts

These limitations are acceptable for the current MVP version and can be improved.

---

## 📈 Possible Improvements
* Database storage for subscriptions
* Distributed caching (e.g. Redis)
* Inline navigation buttons for APOD
* Unit Tests
* Observability

---

## 👩‍💻 Who am I?
Maria - Backend Developer & Space Enthusiast

Java | Spring Boot | Microservices | Backend Engineering

Passionate about building real-world systems & cosmos exploration
* [GitHub](https://github.com/m685319)
* [Space Events Bot](https://telegram.me/space_events_bot)

---

🌌 Clear skies and happy exploring

If you like the project, don’t forget to ⭐ the repository and follow me

