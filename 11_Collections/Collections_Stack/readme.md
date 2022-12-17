## Цель задания

### Научиться:
- применять принципы проектирования и создания коллекций;
- самостоятельно создавать абстрактные типы данных для хранения наборов данных по определённому принципу по аналогии со стандартными реализациями интерфейса Collection.

### Что нужно сделать
Для выполнения этого задания используйте проект из практической работы 11.1, в GitLab — директория Collections_Stack.

**Стек** — абстрактный тип данных, представляющий собой список элементов, организованных по принципу LIFO (англ. last in — first out, «последним пришёл — первым вышел»).

Чаще всего принцип работы стека сравнивают со стопкой тарелок: чтобы взять вторую сверху, нужно снять верхнюю.

В Java есть стандартная коллекция Stack, которая является deprecated: [Stack Class in Java](https://www.geeksforgeeks.org/stack-class-in-java/).

Необходимо реализовать интерфейс Stack:
```java
public interface Stack<E> {
    // add new element to the top of the stack
    void push(E element) throws StackException;
    // return and remove an element from the top
    E pop() throws StackException;
    // return the top element but doesn’t remove
    E peek();
    int getSize();
    boolean isEmpty();
    boolean isFull();
    // add all elements from @src to the stack
    void pushAll(Collection<? extends E> src) throws StackException;
    // pop all elements from stack to @dst
    void popAll(Collection<? super E> dst) throws StackException;
}
```

Внутри стека данные можно хранить в массиве или в списке.

**В итоге:** создан класс, реализующий интерфейс Stack<E>.

### Критерии оценки задания

**Принято:**
- Выполнены все указанные в задании требования: создана реализация стека в соответствии с заданным интерфейсом Stack<E>.
- Все методы работают без ошибок, код компилируется.

**На доработку:** задание не выполнено, выполнено неточно либо частично.

### Как отправить задание на проверку
Выполните также второе задание (смотрите ниже) и отправьте куратору результаты сразу по обоим заданиям.
