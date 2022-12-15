# ДЗ

Вам необходимо реализовать свой класс MyOptional, который сможет работать с любым объектом и позволит
уберечь пользователя от NullPointerException.
Требуется реализовать методы в `MyOptional.java`, и проверить их тестами `MyOptionalTest.java`

Запуск тестов:

*Кнопкой в IntelliJ*

![IntelliJ RUN button](https://i.imgur.com/uHwKybe.png)

* Или командой `mvn -Dtest=TreeTest test` в терминале

### 1. Метод of(T value)

★★☆☆☆

Метод должен создать объект `MyOptional` параметризован `Т` и поместить в него value. Если `value == null` выбросить ошибку
`InvalidParameterException`

### 2. Метод ofNullable(T value)

★★★★☆

Метод должен создать объект `MyOptional` параметризован `Т` и поместить в него value. Если `value == null` класс MyOptional должен быть пустым
Для экономии памяти всегда возвращайте ссылку на один и тот же пустой `MyOptional`

### 3. Метод T get()

★★☆☆☆

Метод должен возвращать содержимое нашего контейнера `MyOptional`. Если содержимого нет выбрасывать ошибку `InvalidParameterException`.

### 3. Метод boolean isPresent()

★☆☆☆☆

Метод должен возвращать `true` если в `MyOptional` лежит какой-то объект.

### 4. Метод T orElse(T other)

★★☆☆☆

Метод должен возвращать `other` если внутри `MyOptional` пусто, если нет то возвращать значение, которое содержится в `MyOptional`

