# Smart Hotel Management System (SHMS)

A Java application that simulates a hotel's core workflow: room creation, guest booking, notifications, check-in / check-out, billing, and payment. It is built around 7 classic design patterns and runs inside Docker. It can run as a console program or as a small web server (port 8080) so it can be reached from a browser on a remote server.

## Tech Stack
- Java 21
- Docker and Docker Compose

## Design Patterns Used
| Pattern | Used for |
|---|---|
| Singleton | `HotelConfig` - single hotel configuration |
| Factory Method | `RoomFactory` - Standard / Deluxe / Suite rooms |
| Builder | `Booking.Builder` - building a booking step by step |
| Observer | `NotificationService` - notifies Guest, Staff, Manager |
| Template Method | `HotelWorkflow` - Check-in / Check-out flow |
| Strategy | `BillingStrategy` - Regular, Member discount, Seasonal pricing |
| Adapter | `PaymentAdapter` - wraps the legacy payment system |

## Project Structure
```
smart-hotel-management-system/
├── src/                  # Java source code (console entry point: app.FinalP, web entry point: app.WebServer)
├── Dockerfile
├── docker-compose.yml
├── .dockerignore
├── .gitignore
└── docs/
    ├── screenshots/
    └── notes.md
```

## Build and Run with Docker
```bash
# Build the image
docker build -t smart-hotel-management-system .

# Run the container (web server on port 8080)
docker run -d --name shms-app -p 8080:8080 smart-hotel-management-system
```
Then open **http://localhost:8080** in the browser.

### With Docker Compose
```bash
docker compose up -d --build
```

### Console mode (output in the terminal only)
```bash
docker run -it --rm --entrypoint java smart-hotel-management-system -cp out app.FinalP
```

## Stop and Clean Up
```bash
docker stop shms-app
docker rm shms-app
docker rmi smart-hotel-management-system   # remove the image
# or, with Docker Compose:
docker compose down
```

## Endpoints
| URL | Description |
|---|---|
| `/` | HTML page showing the output of the hotel workflow |
| `/run` | Same output as plain text (API-style response) |
| `/health` | Returns `OK` |

## Configuration
The web server uses port **8080** by default. It can be changed with the `PORT` environment variable. The hotel name is defined in `src/Singltone/HotelConfig.java`.

## How to Test
Open `http://localhost:8080/run` and check that the output ends with:
```
System process completed successfully!
```
Expected billing totals: Regular = $1800.0, Member = $1440.0, Seasonal = $2340.0.

## Run Without Docker (optional)
```bash
mkdir out
javac -d out $(find src -name "*.java")
java -cp out app.FinalP
```

## Deploying to a VPS (Production)
These steps were used on a remote Linux server (Ubuntu 24.04, GitHub Codespace). They work the same on any Ubuntu VPS.

### 1. Install Git and Docker
```bash
git --version || (sudo apt update && sudo apt install -y git)
docker --version || (curl -fsSL https://get.docker.com | sudo sh)
```
### 2. Clone the repository
```bash
git clone https://github.com/nabouarmanah-lgtm/smart-hotel-management-system.git
cd smart-hotel-management-system
```
### 3. Build and run
```bash
docker build -t smart-hotel-management-system .
docker run -d --name shms-app -p 8080:8080 --restart unless-stopped smart-hotel-management-system
```
### 4. Verify
```bash
docker ps
curl http://localhost:8080/health
```
Then open the server address on port **8080** in the browser. If the page does not open, make sure port 8080 is allowed / public in the provider's firewall or port settings.

### 5. Update after a new commit
```bash
git pull
docker rm -f shms-app
docker build -t smart-hotel-management-system .
docker run -d --name shms-app -p 8080:8080 --restart unless-stopped smart-hotel-management-system
```

## Production URL
https://vigilant-space-goggles-q7rjw6vgj4r4cxqrr-8080.app.github.dev
## Attribution
Original project, written by the repository owner. No third-party code.

## Author
Nada Nael Abu Armana
