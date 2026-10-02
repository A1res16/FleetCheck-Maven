package pt.upt.fleetcheck;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FleetServiceTest {

    private final FleetService service = new FleetService();

    @Test
    void vehicleBelowIntervalDoesNotNeedService() {
        Vehicle vehicle = new Vehicle("V1", "Model A", 10_000, 0, 15_000);
        assertFalse(service.needsService(vehicle));
    }

    @Test
    void vehicleExactlyAtIntervalNeedsService() {
        Vehicle vehicle = new Vehicle("V2", "Model B", 15_000, 0, 15_000);
        assertTrue(service.needsService(vehicle));
    }

    @Test
    void vehicleAboveIntervalNeedsService() {
        Vehicle vehicle = new Vehicle("V3", "Model C", 20_000, 0, 15_000);
        assertTrue(service.needsService(vehicle));
    }

    @Test
    void countsOnlyVehiclesThatNeedService() {
        List<Vehicle> vehicles = List.of(
                new Vehicle("V1", "Model A", 10_000, 0, 15_000),
                new Vehicle("V2", "Model B", 15_000, 0, 15_000),
                new Vehicle("V3", "Model C", 20_000, 0, 15_000)
        );
        assertEquals(2, service.countVehiclesNeedingService(vehicles));
    }

    @Test
    void averageMileageIsComputedOverAllVehicles() {
        List<Vehicle> vehicles = List.of(
                new Vehicle("V1", "Model A", 10_000, 0, 15_000),
                new Vehicle("V2", "Model B", 20_000, 0, 15_000)
        );
        assertEquals(15_000.0, service.averageMileage(vehicles));
    }

    @Test
    void averageMileageOfEmptyFleetIsZero() {
        assertEquals(0.0, service.averageMileage(List.of()));
    }
}
