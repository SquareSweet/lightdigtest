# lightdigtest

Для корректной работы приложения необходимо указать свои данные для подключения к PostgreSQL и Redis в [application.properties](src/main/resources/application.properties).
Также можно использовать подготовленные докер-контейнеры: `docker-compose up`

Необходимиы ключи для работы с [DaData](https://dadata.ru/), поля для них также подготовлены в [application.properties](src/main/resources/application.properties).

При запуске приложения таблицы БД инициализируются тестовыми данными, которые содержат следующих пользователей:
| Логин | Пароль | Роли |
| --- | --- | --- |
| admin | admin | ROLE_ADMIN |
| operadmin | operadmin | ROLE_ADMIN, ROLE_OPERATOR |
| oper | oper | ROLE_OPERATOR |
| userone | userone | ROLE_USER |
| usertwo | usertwo | ROLE_USER |
