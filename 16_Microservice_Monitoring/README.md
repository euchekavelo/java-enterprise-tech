## Практическое задание

**Цель практической работы**

В этом модуле вы изучили особенности сбора метрик в Spring-приложении и способы взаимодействия с инструментами сбора и визуализации метрик Prometheus и Grafana; научились профилировать Java-приложение с использованием инструмента VisualVM.

В практической работе вы потренируетесь:
- самостоятельно настраивать сбор метрик из Spring Boot приложения;
- подключать сбор метрик в Prometheus;
- использовать Grafana для визуализации собранных метрик.


### Что нужно сделать

### Задание 1. Добавить сбор метрик для order-service и их визуализацию в Grafana

Подключите в order-service библиотеки для сбора метрик и запуска эндпоинтов для доступа к метрикам извне:
```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   <dependency>
   <groupId>io.micrometer</groupId>
   <artifactId>micrometer-registry-prometheus</artifactId>
   </dependency>
```
   
Настройте order-service для сбора метрик в Prometheus.
   Для этого добавьте следующие настройки в файл application.yml:
```yaml
management:
   endpoints:
   web:
   exposure:
   include: health,prometheus
   metrics:
   export:
   prometheus:
   enabled: true
   distribution:
   percentiles-histogram:
   "[http.server.requests]": true
```
   
Запустите order-service, перейдите к эндпоинту /actuator/prometheus.
Убедитесь, что отображаются собранные с помощью Micrometer метрики.

Следующий шаг — запуск Prometheus. Рекомендуемый способ запуска Prometheus и Grafana — docker-compose. Поэтому будет удобнее запустить order-service так же, как докер-контейнер. Для этого можно собрать докер-образ для order-service и запустить его с помощью docker-compose.
   Как пример можно использовать следующий Dockerfile:

```dockerfile
FROM amazoncorretto:11-alpine-jdk
   MAINTAINER apolyakov
   COPY target/order-service-0.0.1-SNAPSHOT.jar order-service.jar
   ENTRYPOINT ["java","-jar","/order-service.jar"]
```
   
   Для запуска всех сервисов в связке можно использовать следующий пример файла docker-compose:

```yaml
services:
   message-server:
   container_name: order-service
   image: order-service:latest
   ports:
    - 8080:8080
      networks:
      monitoring:
      aliases:
        - orderservice
          grafana:
          build: './config/grafana'
          ports:
    - 3000:3000
      volumes:
    - ./grafana:/var/lib/grafana
      environment:
    - GF_SECURITY_ADMIN_USER=admin
    - GF_SECURITY_ADMIN_PASSWORD=admin
      networks:
      monitoring:
      aliases:
        - grafana
          prometheus:
          image: prom/prometheus
          ports:
    - 9090:9090
      volumes:
    - ./config/prometheus.yml:/etc/prometheus/prometheus.yml
    - ./prometheus:/prometheus
      networks:
      monitoring:
      aliases:
        - prometheus
          networks:
          monitoring:
```
   

Перед запуском Prometheus настройте источник сбора метрик. Укажите настройки в файле prometheus.yml.
Пример файла prometheus.yml:

```yaml
scrape_configs:
- job_name: 'sample_monitoring'
  scrape_interval: 5s
  metrics_path: '/actuator/prometheus'
  static_configs:
    - targets: ['metrics-demo:8080']
```


Далее настройте Grafana: укажите, как подключаться к Prometheus. Пример файла настроек:  
```yaml
apiVersion: 1

datasources:
- name: Prometheus
  label: Prometheus
  type: prometheus
  access: proxy
  url: http://prometheus:9090
  isDefault: true
```


Также для Grafana пригодятся дашборды для визуализации метрик JVM и Spring Boot. Готовые дашборды можно скачать из официального репозитория Grafana:
- https://grafana.com/grafana/dashboards/6756-spring-boot-statistics/
- https://grafana.com/grafana/dashboards/4701-jvm-micrometer/

Скачанные дашборды положите в папку /provisioning/dashboards.

Всё готово к запуску! Запустите все сервисы через docker-compose и обратитесь к любому API order-service. После обращения к API order-service на дашбордах в Grafana отобразится график со статистикой запросов.
   
После выполнения задания у вас должна быть настроена сборка метрик из order-service в Prometheus и их визуализация в Grafana.

В качестве результата выполнения задания отправьте ссылку на GitLab с кодом и скриншот веб-интерфейса Grafana, в котором отображаются дашборды с метриками order-service.


### Критерии оценки задания

**Принято:**

- Выполнено задание практической работы.
- В задании выполнены все указанные требования: реализован сбор метрик в order-service, запущены Prometheus и Grafana, прикреплены скриншоты из веб-интерфейса Grafana с дашбордами с метриками order-service.
- Все сервисы работают без ошибок, код компилируется.

**На доработку:** задание не выполнено, выполнено неточно или частично.

### Как отправить работу на проверку

Сдайте работу через систему контроля версий Git сервиса Skillbox GitLab. 
В форме ниже напишите «Сделано» и прикрепите ссылку на репозиторий, а также необходимые скриншоты. Ссылки на реплит оставлять не нужно.
