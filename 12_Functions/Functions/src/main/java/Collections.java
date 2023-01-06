import com.skillbox.Function1;
import com.skillbox.Function2;
import com.skillbox.Predicate;

import java.util.*;

public class Collections {

    public static <T, R> List<R> map(Function1<T, R> f, T[] a) {
        List<R> resultList = new ArrayList<>();
        for (T elem : a) {
            resultList.add(f.apply(elem));
        }

        return resultList;
    }

    public static <T> List<T> filter(Predicate<T> p, T[] a) {
        List<T> resultList = new ArrayList<>();
        for (T elem : a) {
            if (p.test(elem)) {
                resultList.add(elem);
            }
        }

        return resultList;
    }

    public static <T> List<T> takeWhile(Predicate<T> p, T[] a) {
        List<T> resultList = new ArrayList<>();
        int i = 0;
        while (!p.test(a[i])) {
            resultList.add(a[i]);
            i++;
        }

        return resultList;
    }

    public static <T> List<T> takeUnless(Predicate<T> p, T[] a) {
        return takeWhile(p.not(), a);
    }

    public static <T, U, R, V> R foldl(Function2<T, U, R> f2, T start, Collection<T> collectionElements) {
        List<T> arrayElements = new ArrayList<>(collectionElements);
        for (Object arrayElement : arrayElements) {
            start = (T) f2.apply(start, (U) arrayElement);
        }

        return (R) start;
    }

    public static <T, U, R, V> R foldr(Function2<T, U, R> f2, T start, Collection<T> collectionElements) {
        List<T> arrayElements = new ArrayList<>(collectionElements);

        //Воспользуемся стандартным статическим методом "reverse" из коробочной версии класс "Collections" для того,
        // чтобы "перевернуть" порядок элементов передаваемого списка.
        java.util.Collections.reverse(arrayElements);

        return foldl(f2, start, arrayElements);
    }
}
