package Model.FactoryComponents.Suppliers;


@FunctionalInterface
public interface Deliver<T> {
    T create(int id);
}
