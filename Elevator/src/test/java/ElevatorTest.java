import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ElevatorTest {

    @Test
    public void defaultConstructor_WorksAsExpected(){
        Elevator testElevator = new Elevator();

        assertAll(
                () -> assertEquals("default elevator", testElevator.getName(), "Name should be 'default elevator'"),
                () -> assertEquals(1, testElevator.getId(), "Id should be 1"),
                () -> assertEquals(1, testElevator.getMaxFloor(), "Max floor should be 1"),
                () -> assertEquals(0, testElevator.getMinFloor(), "Min floor should be 0"),
                () -> assertEquals(5000, testElevator.getMaxWeight(), "Max weight should be 5000"),
                () -> assertEquals(0, testElevator.getCurrentFloor(), "Current floor should default to 0"),
                () -> assertEquals(0, testElevator.getCurrentWeight(), "Current weight should default to 0"),
                () -> assertFalse(testElevator.getIsLocked(), "IsLocked should default to false")
        );
    }

    @Test
    public void constructor_WorksAsExpected(){
        Elevator testElevator = new Elevator("Test Elevator", 100, 3, -1, 4000);

        assertAll(
                () -> assertEquals("Test Elevator", testElevator.getName(), "Name should be 'Test Elevator'"),
                () -> assertEquals(100, testElevator.getId(), "Id should be 100"),
                () -> assertEquals(3, testElevator.getMaxFloor(), "Max floor should be 3"),
                () -> assertEquals(-1, testElevator.getMinFloor(), "Min floor should be -1"),
                () -> assertEquals(4000, testElevator.getMaxWeight(), "Max weight should be 4000"),
                () -> assertEquals(0, testElevator.getCurrentFloor(), "Current floor should default to 0"),
                () -> assertEquals(0, testElevator.getCurrentWeight(), "Current weight should default to 0"),
                () -> assertFalse(testElevator.getIsLocked(), "IsLocked should default to false")
        );
    }

    @Test
    public void lockElevator_WorksAsExpected(){
        Elevator testElevator = new Elevator();

        testElevator.lockElevator();

        assertTrue(testElevator.getIsLocked(), "IsLocked should be true");
    }

    @Test
    public void unLockElevator_WeightUnderLimitUnlocksElevator(){
        Elevator testElevator = new Elevator();
        testElevator.lockElevator();

        testElevator.unLockElevator();

        assertFalse(testElevator.getIsLocked(), "IsLocked should be false");
    }

    @Test
    public void unLockElevator_WeightEqualToLimitUnlocksElevator(){
        Elevator testElevator = new Elevator();
        testElevator.lockElevator();
        testElevator.addWeightKg(5000);

        testElevator.unLockElevator();

        assertFalse(testElevator.getIsLocked(), "IsLocked should be false");
    }

    @Test
    public void unLockElevator_WeightOverLimitThrowsExceptionAndDoesNotUnlockElevator(){
        Elevator testElevator = new Elevator();
        testElevator.lockElevator();
        testElevator.addWeightKg(5001);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> testElevator.unLockElevator());

        assertAll(
                () -> assertEquals("Cannot unlock because elevator is over weight", exception.getMessage()),
                () -> assertTrue(testElevator.getIsLocked(), "IsLocked should be true")
        );
    }

    @Test
    public void addWeightKg_weightAddedCorrectly(){
        Elevator testElevator = new Elevator();

        testElevator.addWeightKg(120.99);

        assertEquals(120.99, testElevator.getCurrentWeight(), "Current Weight should be 120.99");
    }

    @Test
    public void addWeightKg_weightOverMaxLocksElevator(){
        Elevator testElevator = new Elevator();

        testElevator.addWeightKg(6000);

        assertTrue(testElevator.getIsLocked(), "IsLocked should be true");
    }

    @Test
    public void subtractWeightKg_weightSubtractedWhenPositiveResult(){
        Elevator testElevator = new Elevator();
        testElevator.addWeightKg(120.99);

        testElevator.subtractWeightKg(20.99);

        assertEquals(100, testElevator.getCurrentWeight(), "Current Weight should be 100");
    }

    @Test
    public void subtractWeightKg_weightSetToZeroWhenNegativeResult(){
        Elevator testElevator = new Elevator();
        testElevator.addWeightKg(120.99);

        testElevator.subtractWeightKg(200);

        assertEquals(0, testElevator.getCurrentWeight(), "Current Weight should be 0");
    }

    @Test
    public void moveUp_whenElevatorNotatTopMovesUpCorrectly(){
        Elevator testElevator = new Elevator();

        testElevator.moveUp();

        assertEquals(1, testElevator.getCurrentFloor(), "Current floor should be 1");
    }

    @Test
    public void moveUp_whenElevatorAtTopThrowsException(){
        Elevator testElevator = new Elevator();
        testElevator.moveUp();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> testElevator.moveUp());

        assertAll(
                () -> assertEquals("Cannot move up because elevator is on top floor", exception.getMessage()),
                () -> assertEquals(1, testElevator.getCurrentFloor(), "Current floor should be 1")
        );
    }

    @Test
    public void moveUp_whenElevatorLockedDoesNotMove(){
        Elevator testElevator = new Elevator();
        testElevator.lockElevator();

        testElevator.moveUp();

        assertEquals(0, testElevator.getCurrentFloor(), "Current floor should be 0");
    }

    @Test
    public void moveDown_whenElevatorNotatBottomMovesDownCorrectly(){
        Elevator testElevator = new Elevator();
        testElevator.moveUp();

        testElevator.moveDown();

        assertEquals(0, testElevator.getCurrentFloor(), "Current floor should be 0");
    }

    @Test
    public void moveDown_whenElevatorAtBottomThrowsException(){
        Elevator testElevator = new Elevator();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> testElevator.moveDown());

        assertAll(
                () -> assertEquals("Cannot move down because elevator is on bottom floor", exception.getMessage()),
                () -> assertEquals(0, testElevator.getCurrentFloor(), "Current floor should be 0")
        );
    }

    @Test
    public void moveDown_whenElevatorLockedDoesNotMove(){
        Elevator testElevator = new Elevator();
        testElevator.moveUp();
        testElevator.lockElevator();

        testElevator.moveDown();

        assertEquals(1, testElevator.getCurrentFloor(), "Current floor should be 1");
    }
}
