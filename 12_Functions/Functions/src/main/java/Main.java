import com.skillbox.Function2;

import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        Integer[] testArray = new Integer[5];
        testArray[0] = 1;
        testArray[1] = 2;
        testArray[2] = 3;
        testArray[3] = 4;
        testArray[4] = 5;

        //Проверка работоспособности статического метода "map" кастомного класса "Collections"
        List<Integer> mapTestList = Collections.map(elem -> elem * 2, testArray);
        System.out.println(mapTestList);

        //Проверка работоспособности статического метода "filter" кастомного класса "Collections"
        List<Integer> filterTestList = Collections.filter(elem -> elem % 2 == 0, testArray);
        System.out.println(filterTestList);

        //Проверка работоспособности статического метода "takeWhile" кастомного класса "Collections"
        List<Integer> takeWhileTestList = Collections.takeWhile(elem -> elem % 2 == 0, testArray);
        System.out.println(takeWhileTestList);

        //Проверка работоспособности статического метода "takeUnless" кастомного класса "Collections"
        List<Integer> takeUnlessTestList = Collections.takeUnless(elem -> elem % 2 == 0, testArray);
        System.out.println(takeUnlessTestList);

        //Проверка работоспособности статического метода "foldl" (левоассоциативная свертка) кастомного класса "Collections"
        Function2<Integer, Integer, Integer> func = (x, y) -> x * y;
        System.out.println(Collections.foldl(func, 1, Arrays.asList(testArray)));

        //Проверка работоспособности статического метода "foldr" (правоассоциативная свертка) кастомного класса "Collections"
        Function2<Integer, Integer, Integer> func2 = (x, y) -> x * y;
        System.out.println(Collections.foldr(func2, 1, Arrays.asList(testArray)));
    }
}