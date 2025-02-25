public class Rods {

    int slot1;
    int slot2;
    int slot3;
    int slot4;
    int slot5;

    int topSlot;  //the number of the top slot with a disk
    int Disk; //size of the disk that is being moved
    int toDisk; //size of the top disk on the toRod

    //Rod constructor
    public Rods (int slot1, int slot2, int slot3, int slot4,int slot5){

        this.slot1 = slot1;
        this.slot2 = slot2;
        this.slot3 = slot3;
        this.slot4 = slot4;
        this.slot5 = slot5;
    }

    // getter methods
    public int getSlot1() {
        return this.slot1;
    }
    public int getSlot2() {
        return slot2;
    }
    public int getSlot3() {
        return slot3;
    }
    public int getSlot4() {
        return slot4;
    }
    public int getSlot5() {
        return slot5;
    }

    // setter methods
    public void setSlot1(int slot1) {
        this.slot1 = slot1;
    }
    public void setSlot2(int slot2) {
        this.slot2 = slot2;
    }
    public void setSlot3(int slot3) {
        this.slot3 = slot3;
    }
    public void setSlot4(int slot4) {
        this.slot4 = slot4;
    }
    public void setSlot5(int slot5) {
        this.slot5 = slot5;
    }

    //find top slot with a disk method
    //returns 0 if the rode is empty
    public int findTopSlot(){
        if (this.getSlot1() == 0){
            topSlot = 0;
        }
        else if (this.getSlot2() == 0){
            topSlot = 1;
        }
        else if (this.getSlot3() == 0){
            topSlot = 2;
        }
        else if (this.getSlot4() == 0){
            topSlot = 3;
        }
        else if (this.getSlot5() == 0){
            topSlot = 4;
        }
        else if (this.getSlot5() != 0){
            topSlot = 5;
        }
        return topSlot;
    }

}
