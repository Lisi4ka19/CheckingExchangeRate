**Сервис сравнения курсов валют**

**Задание:**
Создать сервис, который обращается к сервису курсов валют, и отображает gif:

- если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
- если ниже - отсюда https://giphy.com/search/broke

Ссылки
- REST API курсов валют - https://docs.openexchangerates.org/
- REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide

Must Have
- Сервис на Spring Boot 2 + Java / Kotlin
- Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD
- Для взаимодействия с внешними сервисами используется Feign
- Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
- На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)
- Для сборки должен использоваться Gradle
- Результатом выполнения должен быть репо на GitHub с инструкцией по запуску

Nice to Have
- Сборка и запуск Docker контейнера с этим сервисом



**Инструкция по запуску:**

Адрес сервиса: YOUR_IP:8080/api/v1/currency/comparison

- GET метод comparison. 

Обязательный параметр – symbols, три символа – код валюты, курс которой будет сравниваться

В методе сравнивается курс валюты (переданной в параметр) за сегодня и за вчера. 
В теле ответа возвращается ссылка на gif из сервиса, указанного в параметре gif.service.url. 
Если курс валюты стал выше, чем вчера, то в методе gif сервиса указывается параметр rich, если курс валюты стал ниже, чем вчера, то в методе gif сервиса указывается параметр broke.


Пример вызова: localhost:8080/api/v1/currency/comparison?symbols=EUR

Структура ответа:

{

    "code": 200,
    
    "message": "OK",
    
     "data": https://giphy.com/gifs/money-trap-dro-lRjJfmLvAyjBmY8uIs
     
}

**Описание полей ответа:**

code	  - Код ответа

message -	Сообщение о статусе или описание ошибки

data	  - Адрес возвращаемого gif


**В файле application.properties необходимые параметры:**

feign.client.url  - адрес сервиса по получению курсов валют для подключение клиента feign (по умолчанию установлено значение https://openexchangerates.org/api/)

app_id – id для подключения к сервису по получению курсов валют. **Нужно установить свое значение**

base – валюта, с которой будет сравниваться переданная в метод валюта (по умолчанию установлено значение "USD")

gif.service.url – адрес сервиса выдачи gif

gif.api_key – id для подключения к сервису выдачи gif. **Нужно установить свое значение**


**Docker:**
docker pull alisaaniskina/checkingexchangerate
docker run -p 8080:8080 alisaaniskina/checkingexchangerate



