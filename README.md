# Smart Hotel Management System (SHMS)

A Java console application that simulates a hotel's core workflow: room creation, guest booking, notifications, check-in / check-out, billing, and payment. It is built around 7 classic design patterns and runs inside Docker.

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
├── src/                  # Java source code (entry point: app.FinalP)
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

# Run the container
docker run -it smart-hotel-management-system
```

### With Docker Compose
```bash
docker-compose up --build
```

## Stop and Clean Up
The app is a console program: it prints its output and exits by itself. To clean up:
```bash
docker-compose down                         # if started with compose
docker rm shms-app                          # remove the stopped container
docker rmi smart-hotel-management-system    # remove the image
```

## Configuration
No ports or environment variables are required. The hotel name is defined in `src/Singltone/HotelConfig.java`.

## How to Test
Run the container and check that the output ends with:
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

## Attribution
Original project, written by the repository owner. No third-party code.
