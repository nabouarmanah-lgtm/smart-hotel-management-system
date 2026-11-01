# Technical Notes

## Biggest Docker problem and how I solved it
My project was created in NetBeans, so it has no pom.xml or build.gradle file, and the main class listed in the project settings (shms.SHMS) is an empty class. The real entry point is app.FinalP. At first it was not clear how Docker should compile and run it. I solved it by writing a Dockerfile that uses a Java JDK base image, copies the src folder, compiles all .java files with javac into an "out" folder, and starts the program with CMD ["java", "-cp", "out", "app.FinalP"].

## Most important Git lesson I learned
I learned to keep the repository clean and organized: I used .gitignore so build files (build/, nbproject/) are not uploaded, and I made small commits with clear messages (feat:, docs:, docker:) instead of one big commit. This makes the project history easy to read and shows the real progress of the work.