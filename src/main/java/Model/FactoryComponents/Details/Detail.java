package Model.FactoryComponents.Details;

public abstract class Detail {
    private final int id;
    //private final double creation_time;

    protected Detail(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
}
