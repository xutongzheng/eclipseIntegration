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
    public Belts(item A){
        this.itemOnBelt = A;
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
    public item(String name, String location, int i){
        this.name = name;
        this.location = location;
        this.ispackaged = false;
        this.size = i;
    }
}

class bin{
    item itemInBin = new item();
    int index;
    public bin(int i){
        index = i;
    }
}

class destination{
    String coordination;
}

class picker extends destination{
    String coordinationOfPicker;
    bin Bin;
    boolean picked = false;
    public picker(String coordination){
        this.coordinationOfPicker = coordination;
    }
    void pickitem(Belts b, int i){
        this.Bin = new bin(i);
        Bin.itemInBin = b.itemOnBelt;
        b.BinOnBelt = this.Bin;
        picked = true;
    }
}

class box{
    int size;
    item itemInBox;
    boolean isSealed;
    public box(int i){
        this.size = i;
    }
}
class packer extends destination{
    String coordinationOfPacker;
    item itemAtPicker;
    box Box;
    boolean packaged;
    public packer(String coordination){
        this.coordinationOfPacker = coordination;
    }
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
    void lable(String s){
        this.itemAtPicker.lable = s;
    }
    void seal(Belts b){
        this.Box.isSealed = true;
        b.BoxOnBelt = this.Box;
    }
}

class dock{
    String coordinationOfDock;
    box BoxToShip;
    public dock(String coordination){
        this.coordinationOfDock = coordination;
    }
    void getBox(Belts b){
        this.BoxToShip = b.BoxOnBelt;
    }
}
