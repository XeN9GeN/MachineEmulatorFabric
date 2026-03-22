package Model.FactoryComponents.Suppliers;


import Model.FactoryComponents.Details.Detail;

@FunctionalInterface
public interface Deliver<T> {
    T create(int id);
}
