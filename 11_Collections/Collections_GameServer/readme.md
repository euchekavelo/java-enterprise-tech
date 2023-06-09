## Цель задания

### Научиться:
- применять различные виды коллекций и ассоциативных массивов на практике;
- использовать реализации класса Collection при реализации бизнес-логики приложений.

В этом модуле вы начнёте реализовывать бэкенд для игрового сервера.

Для выполнения этого задания используйте проект из практической работы 11.1, в GitLab — директория Collections_GameServer.

**Логика игрового сервера:** 
сервер организует бои между игроками. Для организации боёв необходимо хранить данные об игроках. Для этого используется **LeagueManager**.

**Модель данных:**

класс **Player** содержит поля:
- **nickname** - имя игрока
- **points** - количество набранных очков
- **race** - раса игрока
- **league** - лига, в которой состоит игрок

перечисление **Race**;

перечисление **League**.

### Что нужно сделать

1. Реализуйте возможность сравнивать игроков между собой:
   - По количеству очков (реализуйте интерфейс **Comparable** для класса **Player**).
   - По их лиге (создайте класс **LeagueComparator**, который будет сравнивать два объекта класса **Player** по их лиге).
2. Сделайте удобочитаемый вывод данных об игроках, реализовав метод **toString()** в классе Player.
3. Реализуйте методы **hashcode** и **equals** в классе Player.
4. Разработайте класс, реализующий следующий интерфейс:
```java
public interface LeagueManager {
   void addPlayer(Player player);
   void removePlayer(Player player);
   Player getPlayer(String name);
   Player[] getAllPlayers();
   Player[] getPlayers(League league);
   Player[] getPlayers(Race race);
   void addPoints(String name, int points);
}
```
Реализация должна хранить данные об игроках эффективным образом для осуществления быстрого поиска, а также добавления/удаления игроков согласно представленному интерфейсу методов.

5. Сделайте реализацию интерфейса **LeagueManager** потокобезопасной: должно быть обеспечено корректное добавление/изменение данных об игроках при многопоточном доступе к нему.

6. По желанию. Напишите юнит-тесты, используя JUnit 4, для проверки корректности реализации интерфейса LeagueManager.

### Критерии оценки задания

**Принято:**

- Выполнены все указанные в задании требования: создана потокобезопасная реализация для интерфейса LeagueManager, 
поиск игроков в соответствии с заданным интерфейсом работает корректно и быстро (!).
- Все методы работают без ошибок, код компилируется.
   
**На доработку:** задание не выполнено, выполнено неточно либо частично.
