https://github.com/Ruslan8999/java-explore-with-me/pull/4

# _"Explore With Me"_

### _Java_

В рамках дипломного проекта разработал REST API для приложения которое
предоставляет
возможность
делиться информацией об интересных событиях и помогать найти компанию для участия в них (аналог "Афиши").

### **Инструкция по запуску проекта:**

1. mvn clean package
2. docker-compose build
3. docker-compose up -d
6. основной сервис доступен по адресу: http://localhost:8080
7. сервис статистики доступен по адресу: http://localhost:9090

### _Приложение включает в себя сервисы:_

- Основной сервис — содержит всё необходимое для работы.
    - Просмотр событий без авторизации;
    - Возможность создания и управления категориями;
    - События и работа с ними - создание, модерация;
    - Запросы пользователей на участие в событии - запрос, подтверждение, отклонение.
    - Создание и управление подборками.
    - Добавление и удаление комментариев к событиям.
- Сервис статистики — хранит количество просмотров и позволяет делать различные выборки для анализа работы приложения.
    - Отдельный сервис для сбора статистики;


### _Спецификация REST API swagger_

- [Основной сервис](https://github.com/Ruslan8999/java-explore-with-me/blob/feature_comments/swagger/main-service.json)
- [Сервис статистики](https://github.com/Ruslan8999/java-explore-with-me/blob/feature_comments/swagger/stat-service.json)

### _Postman тесты для сервисов_

- [Основной сервис](https://github.com/Ruslan8999/java-explore-with-me/blob/feature_comments/postman/main.json)
- [Сервис статистики](https://github.com/Ruslan8999/java-explore-with-me/blob/feature_comments/postman/stat.json)
- [Фича комментарии](https://github.com/Ruslan8999/java-explore-with-me/blob/feature_comments/postman/feature.json)


