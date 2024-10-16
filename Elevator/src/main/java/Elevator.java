/**
 * A Class that simulates the basic functionality of an elevator
 *
 * @author Jean Collette
 * @version 1.0 October 16, 2024
 */
public class Elevator {
    private final int id;
    private final int maxFloor;
    private final int minFloor;
    private final float maxWeight;

    private String name;
    private double currentWeight;
    private int currentFloor;
    private boolean isLocked;

    //constructors
    Elevator(){
        this.id = 1;
        this.maxFloor = 1;
        this.minFloor = 0;
        this.maxWeight = 5000;
        this.name = "default elevator";

        this.currentWeight = 0;
        this.currentFloor = 0;
        this.isLocked = false;
    }
    Elevator(String name, int id, int maxFloor, int minFloor, float maxWeight){
        this.name = name;
        this.id = id;
        this.maxFloor = maxFloor;
        this.minFloor = minFloor;
        this.maxWeight = maxWeight;

        this.currentWeight = 0;
        this.currentFloor = 0;
        this.isLocked = false;
    }

    // getters
    public int getCurrentFloor() {
        return currentFloor;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public int getId() {
        return id;
    }

    public boolean getIsLocked(){
        return isLocked;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public int getMinFloor() {
        return minFloor;
    }

    public String getName() {
        return name;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Determines if the elevator is currently over-weight
     * @return A boolean indicating whether the elevator is over-weight
     */
    private boolean isOverweight(){
        if(this.currentWeight > this.maxWeight)
        {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Moves the elevator up one floor provided it is not locked
     * @throws java.lang.IllegalArgumentException if elevator is on the top floor
     */
    public void moveUp(){
        if(this.currentFloor == this.getMaxFloor()){
            throw new IllegalArgumentException("Cannot move up because elevator is on top floor");
        }
        if(!this.isLocked){
            this.currentFloor = this.currentFloor + 1;
        }
    }

    /**
     * Moves the elevator down one floor provided it is not locked
     * @throws java.lang.IllegalArgumentException if elevator is on the bottom floor
     */
    public void moveDown(){
        if(this.currentFloor == this.getMinFloor()){
            throw new IllegalArgumentException("Cannot move down because elevator is on bottom floor");
        }
        if(!this.isLocked){
            this.currentFloor = this.currentFloor - 1;
        }
    }

    /**
     * Adds the given weight to the overall weight on the elevator
     * @param weightKg The amount of weight in kg
     */
    public void addWeightKg(double weightKg) {
        this.currentWeight = this.currentWeight + weightKg;
        if(this.isOverweight()) {
            this.isLocked =true;
            System.out.println("Locking Elevator because it is overweight");
        }
    }

    /**
     * Subtracts the given amount of weight from the overall weight on the elevator
     * @param weightKg The amount of weight in kg to subtract
     */
    public void subtractWeightKg(double weightKg){
        this.currentWeight = currentWeight - weightKg;
        if(this.currentWeight < 0) {
            this.currentWeight = 0;
        }
    }

    /**
     * Locks the elevator on the current floor, preventing it from moving
     */
    public void lockElevator(){
        this.isLocked = true;
    }

    /**
     * Unlocks the elevator, allow it to be moved
     * @throws java.lang.IllegalArgumentException if the elevator is over-weight
     */
    public void unLockElevator(){
        if(this.isOverweight()) {
            throw new IllegalArgumentException("Cannot unlock because elevator is over weight");
        }
        this.isLocked = false;
    }
}
