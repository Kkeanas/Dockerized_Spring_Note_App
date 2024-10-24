# Укажите базовый образ с установленной Java 21
FROM openjdk:21-jdk-slim

# Установите рабочую директорию
WORKDIR /app

# Скопируйте файл jar в контейнер
# Предполагается, что ваш jar файл находится в директории target/app.jar
COPY target/NotesV2-0.0.1-SNAPSHOT.jar app.jar

# Укажите команду, которая должна быть выполнена при запуске контейнера
ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080