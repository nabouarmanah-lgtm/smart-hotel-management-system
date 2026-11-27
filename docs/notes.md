# Technical Notes

## Biggest Docker problem and how I solved it
My project was created in NetBeans, so it has no pom.xml or build.gradle file, and the main class listed in the project settings (shms.SHMS) is an empty class. The real entry point is app.FinalP. At first it was not clear how Docker should compile and run it. I solved it by writing a Dockerfile that uses a Java JDK base image, copies the src folder, compiles all .java files with javac into an "out" folder, and starts the program with CMD ["java", "-cp", "out", "app.FinalP"].

## Most important Git lesson I learned
I learned to keep the repository clean and organized: I used .gitignore so build files (build/, nbproject/) are not uploaded, and I made small commits with clear messages (feat:, docs:, docker:) instead of one big commit. This makes the project history easy to read and shows the real progress of the work.

## Assignment 3: Deploying to a VPS
### Challenges and how I solved them
1. **Claw Cloud was unavailable.** The recommended provider (run.claw.cloud) did not open and appears to have shut down. My instructor confirmed that any provider is acceptable.
2. **Other providers did not work.** Play With Docker was discontinued, and Azure for Students rejected my account ("not eligible for an Azure free account").
3. **Solution: GitHub Codespaces.** I used a Blank Codespace (Ubuntu 24.04), a remote Linux machine with a browser terminal.
4. **Git and Docker were already installed.** I verified them with `git --version` and `docker --version`.
5. **Console app, not a web app.** SHMS prints its output and exits, so at first I could only verify it with `docker logs` and a background container, not from a browser. To make it reachable at an address and a port, I added a small HTTP server (`src/app/WebServer.java`, built on the JDK's `HttpServer`) that runs the same program and returns its output at `/`, `/run` and `/health`. The original code (`FinalP`) was not changed, and the Dockerfile now starts the web server on port 8080.
6. **Container name conflict in Docker Compose.** The name `shms-app` was already in use, so I removed the old container with `docker rm -f shms-app`.
7. **Git push rejected.** After I changed the files locally, `git push` was rejected because the repository on GitHub already had newer commits. I pulled the remote changes, merged the two versions of `README.md` and `docs/notes.md` by hand so no work was lost, and then pushed.
