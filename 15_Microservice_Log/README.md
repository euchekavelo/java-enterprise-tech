## Практическое задание

Закрепите полученные знания, выполнив практическую работу. Оба задания обязательны для выполнения и сдачи на проверку.

В этом модуле вы:
- изучили особенности анализа логов в микросервисных системах;
- изучили способы реализации распределённой трассировки с использованием Spring Cloud Sleuth;
- научились использовать классический стек ELK для сбора и анализа логов.


### Цель практической работы

**Закрепить полученные знания и научиться:**
- самостоятельно настраивать сбор логов и трейсов;
- подключать сервисы к Zipkin (система хранения трейсов);
- использовать сервисы ELK для агрегации и визуализации логов.

**Что входит в работу**
- Задание 1. Добавить сбор логов и их агрегацию в ELK.
- Задание 2. Добавить сбор трейсов в Zipkin.

Перед началом выполнения работы обновитесь из репозитория, после этого в вашем репозитории появятся следующие папки:
- /15_Microservice_Log/discovery,
-  /15_Microservice_Log/configuration,
-  /15_Microservice_Log/order-service.


### Что нужно сделать

### Задание 1

Добавить сбор логов и их агрегацию в ELK.

Добавьте и настройте **logback** в **order-service**, добавьте файл **logback-spring.xml**, добавьте **FileAppender** (любой паттерн, логи должны сохраняться в файл). Пример:

```xml
<appender name="SAVE-TO-FILE" class="ch.qos.logback.core.FileAppender">               
    <file>/docker-elk/logstash/pipeline/logs/application.log</file>         
    <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">               
        <Pattern>%d{dd-MM-yyyy HH:mm:ss.SSS} [%thread] %-5level %logger{36}.%M - %msg%n</Pattern>
    </encoder>
</appender>
```

Запустите в docker ELK-стек по инструкции из репозитория: https://github.com/deviantony/docker-elk/blob/main/README.md

Настройте сбор логов **logstash**.

Для настройки **logstash** используется файл **logstash.conf**. В этом файле нужно настроить секции input и filter.

Пример файла logstash.conf:

```js
input {
   file {
      type => "log"
      path => "/usr/share/logstash/pipeline/logs/application.log"
   }

    beats {
	port => 5044
   }

   tcp {
	port => 50000
   }
}

filter {
  if [message] =~ "\tat" {
    grok {
      match => ["message", "^(\tat)"]
      add_tag => ["stacktrace"]
    }
  }
 
}

output {
	elasticsearch {
		hosts => "elasticsearch:9200"
		user => "logstash_internal"
		password => "${LOGSTASH_INTERNAL_PASSWORD}"
	}
}
```

Настройте визуализацию логов в Kibana.
- Проверьте, что logstash и elastic search запущены и работают.
- Отправьте HTTP-запрос в order-service, перейдите в веб-интерфейс Kibana.
- Убедитесь, что создался файл с логами, order-service пишет логи в этот файл.
- Проверьте, что в elastic search отображается индекс, созданный logstash.
- Создайте Data View на основе индекса “log-*”.
- Просмотрите логи, которые отправил order-service за последние полчаса.

В результате выполненного задания у вас должна быть настроена сборка логов из order-service в ELK.

Отправьте ссылку на GitLab с кодом и скриншот веб-интерфейса Kibana, в котором отображаются логи order-service.

### Задание 2

Добавить сбор трейсов в Zipkin.

Добавьте spring-проект для Zipkin.

Для подключения Zipkin понадобятся библиотеки:

```xml
<dependency>
    <groupId>io.zipkin</groupId>
    <artifactId>zipkin-server</artifactId>
    <version>2.23.16</version>
</dependency>
```

Для включения **Zipkin Server** используйте аннотацию **@EnableZipkinServer.**

В качестве настроек в **application.properties** можно указать:

```properties
spring.application.name=zipkin
server.port=9411
eureka.client.region=default
eureka.client.registryFetchIntervalSeconds=5
armeria.ports[0].port=9411
```

Затем запустите приложение zipkin-server.

Настройте order-service для записи трейсов и их отправки в Zipkin.

Сбор трейсов должен осуществляться в каждом из контроллеров сервиса.
Для подключения Zipkin понадобятся библиотеки:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>
```

В качестве настроек в application.properties можно указать:
```properties
spring.zipkin.base-url=http://localhost:9411/
```

Отправьте HTTP-запрос к любому эндпоинту order-service.

Перейдите в веб-интерфейс Zipkin. Убедитесь, что отобразился трейс отправленного ранее в order-service запроса.

В результате выполненного задания у вас должна быть настроена сборка трейсов из order-service в Zipkin.

Отправьте ссылку на GitLab с кодом и скриншот веб-интерфейса Zipkin, в котором отображаются трейсы order-service.

### Критерии оценки задания

**Принято:**

- Выполнено задание практической работы.
- В задании выполнены все указанные требования: реализован сбор метрик в order-service, запущены Prometheus и Grafana, прикреплены скриншоты из веб-интерфейса Grafana с дашбордами с метриками order-service.
-  Все сервисы работают без ошибок, код компилируется.



**На доработку:** задание не выполнено, выполнено неточно или частично.

### Как отправить работу на проверку

Сдайте работу через систему контроля версий Git сервиса Skillbox GitLab. 
В форме ниже напишите «Сделано» и прикрепите ссылку на репозиторий, а также необходимые скриншоты. Ссылки на реплит оставлять не нужно.
