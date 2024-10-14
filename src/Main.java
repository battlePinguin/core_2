import сollectionFilter.Filter;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
//      Collection - фильтрация
        filterArray();
//      Collection - count of elements
        countElements();


    }

    public static void filterArray() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        Filter evenFilter = o -> {
            if ((Integer) o % 2 == 0) {
                return o;
            }
            return null;
        };

        Object[] filteredArray = CollectionUtils.filter(array, evenFilter);

        printIt(filteredArray);
    }

    public static void countElements() {
        String[] array = {"java", "banana", "java", "banana", "code"};

        Map<String, Integer> elementCount = CollectionUtils.countOccurrences(array);

        for (Map.Entry<String, Integer> entry : elementCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static void printIt(Object[] array) {
        for (Object element : array) {
            System.out.println(element);
        }
    }

}
