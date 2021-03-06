## Исходный текст задания

Написать серверную часть Web-приложения согласно следующим требованиям:

- Java 1.7 (или выше)
- Spring Framework
- HTTP
- PostgreSQL
- RESTful
- Все данные в формате JSON
- Обработка ошибок

## Функционал

1. Добавление нового пользователя. Передаем на сервер данные пользователя
   (имя пользователя, email, phoneNumber и т.д.). Сохраняем информацию в базе данных. Ответ сервера — уникальный ID
   нового пользователя.
2. Получение информации о пользователе. Передаем на сервер уникальный ID пользователя. Читаем информацию из базы данных.
   Ответ сервера — данные пользователя.
3. Изменение статуса пользователя (Online, Away, Offline). Передаем на сервер уникальный ID пользователя и новый статус.
   Изменяем статус пользователя. Ответ сервера — уникальный ID пользователя, новый и предыдущий статус.
4. Перевод в статус Away должен делаться автоматически через 5 минут после последнего изменения статуса на Online. Таким
   образом, если “подтверждать” статус Online пользователя каждые 5 минут — автоматического перехода в Away не должно
   быть.

## Инструкции

- Все запросы сервер принимает по пути `localhost:8080/api`
- Документация в формате Swagger UI доступна по пути `localhost:8080/api/docs-ui`
- В скрипте `Dockerfile` доступна сборка легковесного контейнера с `PostgreSQL 13.2`:
  - Запуск скрипта осуществляется с помощью команд `docker build -t postgres-13-alpine`
      и `docker run --name postgres-alpine -p 5432:5432 -d localhost/postgres-13-alpine`
- Приложение может работать в двух профилях: с `PostgreSQL` и `H2` в качестве баз данных:
  - Выбор базы данных зависит от профиля приложения и установленной переменной окружения `spring_profiles_active` или
      системного параметра JVM `spring.profiles.active`
  - По умолчанию запускается профиль с `PostgreSQL`
  - Запуск с `H2` осуществляется установки переменной окружения `export spring_profiles_active=h2` или системного
      параметра `-Dspring.profiles.active=h2`
