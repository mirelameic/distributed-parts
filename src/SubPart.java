public class SubPart{
    private Part subPart;
    private int quantity;

    public SubPart(Part subPart, int quantity){
        this.subPart = subPart;
        this.quantity = quantity;
    }

    public Part getSubPart(){
        return subPart;
    }

    public int getQuantity(){
        return quantity;
    }
}