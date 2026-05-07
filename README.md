# RescuePaymentService

📌 Emergency Response System (Microservices)

🧠 Overview

This project is a real-time emergency response application built using a microservices architecture.
It connects requesters (people in need) with nearby responders (helpers) using location-based matching and instant notifications.

⸻

🏗️ Architecture

The system is built with Spring Boot microservices and uses Feign Client for inter-service communication.

Services:

* User Service (includes Notification logic)
* report Service (include feedService)
* payment Service


⸻

👥 User Roles

* Requester
    * Creates emergency request
    * Location is captured automatically
* Responder
    * Registers with location
    * Receives nearby emergency alerts
    * Accepts requests
* Admin
    * Manages users
    * Monitors system activities
    * Deletes users

⸻

⚙️ Key Features

* 📍 Automatic GPS location capture
* 🚨 Real-time emergency request handling
* 📡 Notification system (integrated in User Service)
* ✅ First responder acceptance logic
* 🚫 Prevents multiple responders from accepting same request

⸻

🔁 Workflow

1. Requester sends emergency request
2. System finds nearby responders (based on location)
3. Notification is sent via User/Notification Service
4. One responder accepts the request
5. System updates status → blocks other responders

⸻

🔗 Feign Client Communication

Feign Client is used for service-to-service communication.