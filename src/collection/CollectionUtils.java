package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionUtils {

    public static <T> T[] filter(T[] array, СollectionFilter filter) {
        List<T> result = new ArrayList<>();

        for (T element : array) {

            T filteredElement = (T) filter.apply(element);
            if (filteredElement != null) {
                result.add(filteredElement);
            }

        }
        return result.toArray((T[]) new Object[0]);
    }

    public static <T> Map<T, Integer> countOccurrences(T[] array) {
        Map<T, Integer> elementCountMap = new HashMap<>();

        for (T element : array) {
            elementCountMap.put(element, elementCountMap.getOrDefault(element, 0) + 1);
        }

        return elementCountMap;
    }
}
