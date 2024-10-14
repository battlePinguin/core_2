package сollectionFilter;

@FunctionalInterface
public interface Filter {
    Object apply(Object o);
}