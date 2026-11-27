# Technical Notes

## Assignment 2 - Docker and GitHub
<!-- EDIT THIS with your real experience -->
The project was created in NetBeans, so it has no pom.xml or build.gradle file, and the real entry point (`app.FinalP`) is different from the main class NetBeans lists (`shms.SHMS`, an empty class). I solved it with a Dockerfile that compiles all `.java` files with `javac` and starts the program with `CMD`.

Git lesson: keep the repository clean with `.gitignore` and make small commits with clear messages (`feat:`, `docs:`, `docker:`).

## Assignment 3 - Deploying to a VPS
<!-- EDIT THIS with the real challenges you faced on the VPS -->
**Challenge 1 - the project was a console program.** A production deployment must be reachable at the server IP and a port, but the original program only printed text in the terminal. I added a small HTTP server (`app/WebServer.java`, using the JDK's built-in `HttpServer`) that runs the same program and returns its output at `/`, `/run`, and `/health`. The original code (`FinalP`) was not changed.

**Challenge 2 - the site was not reachable from the browser.** (Write here what actually happened, for example: the container was running but the page did not open until port 8080 was allowed in the VPS network/firewall settings.)

**Challenge 3 - installing Git and Docker on the VPS.** (Write here what you did, for example: checked with `git --version` and `docker --version`, then installed what was missing.)
