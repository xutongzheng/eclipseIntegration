/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belts;

/**
 *
 * @author xutzheng
 */
public class Belts{
    item itemOnBelt = new item();
    bin BinOnBelt;
    box BoxOnBelt;
    /**
     * 
     * @param A 
     */
    public Belts(item A){
        this.itemOnBelt = A;
    }
    /**
     * 
     * @param d 
     */
    void moveItem(destination d){
        this.itemOnBelt.location = d.coordination;
    }
}

class item{
    String name;
    String location;
    boolean ispackaged;
    String lable;
    int size;
    public item(){
        this.ispackaged = false;
    }
    /**
     * 
     * @param name
     * @param location
     * @param i 
     */
    public item(String name, String location, int i){
        this.name = name;
        this.location = location;
        this.ispackaged = false;
        this.size = i;
    }
    /**
     * 
     * @return the name of the item
     */
    String getName(){
        return this.name;
    }
    /**
     * 
     * @return the location of the item
     */
    String getLocation(){
        return this.location;
    }
    /**
     * 
     * @return the lable of the item
     */
    String getLable(){
        return this.lable;
    }
    /**
     * 
     * @return the size of the item
     */
    int getSize(){
        return this.size;
    }
    /**
     * 
     * @return To report if the item is packaged
     */
    boolean isPackaged(){
        return this.ispackaged;
    }
}
class bin{
    item itemInBin = new item();
    int index;
    /**
     * 
     * @param i 
     */
    public bin(int i){
        index = i;
    }
}

class destination{
    String coordination;
}

class picker extends destination{
    bin Bin;
    boolean picked = false;
    item itemAtPicker;
    /**
     * 
     * @param coordination 
     */
    public picker(String coordination){
        this.coordination = coordination;
    }
    /**
     * 
     * @param b
     * @param i 
     * This method is to put the item into a new bin with number i and put the bin on the belt. 
     * Then mark the item as marked.
     */
    void pickitem(Belts b, int i){
        this.Bin = new bin(i);
        Bin.itemInBin = itemAtPicker;
        b.BinOnBelt = this.Bin;
        picked = true;
    }
    /**
     * 
     * @return if the item at the picker is picked
     */
    boolean checkitem(){
        return picked;
    }
    /**
     * 
     * @param i 
     */
    void setItem(item i){
        this.itemAtPicker = i;
    }
}

class box{
    int size;
    item itemInBox;
    boolean isSealed;
    /**
     * 
     * @param i 
     */
    public box(int i){
        this.size = i;
    }
}
class packer extends destination{
    item itemAtPicker;
    box Box;
    boolean packaged;
    /**
     * 
     * @param coordination 
     */
    public packer(String coordination){
        this.coordination = coordination;
    }
    /**
     * 
     * @param b 
     */
    void getitem(Belts b){
        this.itemAtPicker = b.BinOnBelt.itemInBin; 
        b.BinOnBelt.itemInBin = null;
    }
    void findbox(){
        this.Box = new box(this.itemAtPicker.size);
    }
    void Package(){
        this.Box.itemInBox = this.itemAtPicker;
        this.itemAtPicker.ispackaged = true;
    }
    /**
     * 
     * @param s 
     */
    void lable(String s){
        this.itemAtPicker.lable = s;
    }
    /**
     * 
     * @param b 
     */
    void seal(Belts b){
        this.Box.isSealed = true;
        b.BoxOnBelt = this.Box;
    }
}

class dock extends destination{
    box BoxToShip;
    /**
     * 
     * @param coordination 
     */
    public dock(String coordination){
        this.coordination = coordination;
    }
    /**
     * 
     * @param b 
     */
    void getBox(Belts b){
        this.BoxToShip = b.BoxOnBelt;
    }
}
