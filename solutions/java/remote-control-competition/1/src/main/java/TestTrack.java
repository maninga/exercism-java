import java.util.Collections;
// import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
// import java.util.stream.Collectors;

public class TestTrack {

    public static void race(RemoteControlCar car) {
        car.drive();
    }

    public static List<ProductionRemoteControlCar> getRankedCars(List<ProductionRemoteControlCar> cars) {
        List<ProductionRemoteControlCar> sortedCars = new ArrayList<>(cars);
        // method 1. sort inplace, then reverse order (default is natural order)
        Collections.sort(sortedCars); // or sortedCars.sort(null); as List implements sort
        Collections.reverse(sortedCars);
        // method 2. sort inplace in reverse order
        // sortedCars.sort(Collections.reverseOrder());
        return sortedCars;
        // method 2. modern method
        // return cars.stream()
        //            .sorted(Comparator.reverseOrder())
        //            .collect(Collectors.toList());
    }
}
