#FROM openjdk:17
#COPY build/libs/myallstarteam-0.0.1-SNAPSHOT.jar myallstarteam.jar
#
#ENTRYPOINT ["java", "-jar", "/myallstarteam.jar"]

FROM ubuntu:latest

# Install dependencies: OpenJDK, wget, gnupg, Chrome (and Chromium for ARM64)
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    wget \
    gnupg \
    && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && if [ "$(uname -m)" = "aarch64" ]; then \
         echo "Using Chromium for ARM64"; \
         apt-get install -y chromium; \
       else \
         echo "Using Google Chrome for x86_64"; \
         echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list; \
         apt-get update && apt-get install -y google-chrome-stable; \
       fi \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Install required dependencies for Chrome to run headlessly
RUN apt-get update && apt-get install -y \
    libx11-dev \
    libgdk-pixbuf2.0-0 \
    libnspr4 \
    libnss3 \
    libatk-bridge2.0-0 \
    libatk1.0-0 \
    libgdk-pixbuf2.0-0 \
    libdrm2 \
    libxrandr2 \
    libxcomposite1 \
    libxdamage1 \
    libxi6 \
    libxtst6 \
    && apt-get clean

# Set Chrome options to run headlessly and point to the correct location for ChromeDriver
ENV CHROME_BIN=/usr/bin/google-chrome
ENV CHROMEDRIVER_PATH=/chromedriver
ENV DISPLAY=:99
ENV GOOGLE_CHROME_BIN=/usr/bin/google-chrome-stable

# Set shared memory size to avoid Chrome crashing due to lack of /dev/shm space
RUN mkdir /etc/systemd/system && echo -e "[Service]\nLimitNOFILE=4096\nLimitNPROC=4096" > /etc/systemd/system/chrome.service

# Copy ChromeDriver to container and make it executable
COPY chromedriver /chromedriver
RUN chmod +x /chromedriver

# Copy the Java application jar file to the container
COPY build/libs/myallstarteam-0.0.1-SNAPSHOT.jar myallstarteam.jar

# Set the entry point for running the Java application
ENTRYPOINT ["java", "-jar", "/myallstarteam.jar"]

