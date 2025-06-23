/*
7.4 Parking Lot: Design a parking lot using object-oriented principles.
*/

package ch7_oop_design;
import java.util.*;


public class ParkingLot {
    public enum VehicleSize {
        MOTORCYCLE, COMPACT, LARGE
    }

    public abstract class Vehicle {
        protected ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();
        protected VehicleSize size;
        protected int spotsNeeded;

        public int getSpotsNeeded() {
            return spotsNeeded;
        }

        public VehicleSize getSize() {
            return size;
        }

        public void parkInSpot(ParkingSpot spot) {
            parkingSpots.add(spot);
        }

        public void clearSpots() {
            for (ParkingSpot spot : parkingSpots) {
                spot.removeVehicle();
            }
            parkingSpots.clear();
        }

        public abstract boolean canFitInSpot(ParkingSpot spot);
    }

    public class Motorcycle extends Vehicle {
        public Motorcycle() {
            spotsNeeded = 1;
            size = VehicleSize.MOTORCYCLE;
        }

        public boolean canFitInSpot(ParkingSpot spot) {
            return true; // fits anywhere
        }
    }

    public class Car extends Vehicle {
        public Car() {
            spotsNeeded = 1;
            size = VehicleSize.COMPACT;
        }

        public boolean canFitInSpot(ParkingSpot spot) {
            return spot.getSize() != VehicleSize.MOTORCYCLE;
        }
    }

    public class Bus extends Vehicle {
        public Bus() {
            spotsNeeded = 5;
            size = VehicleSize.LARGE;
        }

        public boolean canFitInSpot(ParkingSpot spot) {
            return spot.getSize() == VehicleSize.LARGE;
        }
    }

    public class ParkingSpot {
        private Vehicle vehicle;
        private VehicleSize size;
        private int spotNumber;
        private Level level;

        public ParkingSpot(Level lvl, int num, VehicleSize size) {
            this.level = lvl;
            this.spotNumber = num;
            this.size = size;
        }

        public boolean isAvailable() {
            return vehicle == null;
        }

        public boolean canFitVehicle(Vehicle v) {
            return isAvailable() && v.canFitInSpot(this);
        }

        public boolean park(Vehicle v) {
            if (!canFitVehicle(v))
                return false;
            this.vehicle = v;
            v.parkInSpot(this);
            return true;
        }

        public void removeVehicle() {
            if (vehicle != null) {
                vehicle = null;
            }
        }

        public VehicleSize getSize() {
            return size;
        }
    }

    public class Level {
        private int floor;
        private List<ParkingSpot> spots;

        public Level(int floor, int numSpots) {
            this.floor = floor;
            spots = new ArrayList<>();
            for (int i = 0; i < numSpots; i++) {
                VehicleSize size = (i < 5) ? VehicleSize.MOTORCYCLE
                        : (i < 15) ? VehicleSize.COMPACT : VehicleSize.LARGE;
                spots.add(new ParkingSpot(this, i, size));
            }
        }

        public boolean parkVehicle(Vehicle v) {
            int spotsNeeded = v.getSpotsNeeded();
            int spotsFound = 0;

            for (int i = 0; i < spots.size(); i++) {
                ParkingSpot spot = spots.get(i);

                if (spot.canFitVehicle(v)) {
                    spotsFound++;
                    if (spotsFound == spotsNeeded) {
                        // Park the vehicle
                        for (int j = i - spotsNeeded + 1; j <= i; j++) {
                            spots.get(j).park(v);
                        }
                        return true;
                    }
                } else {
                    spotsFound = 0;
                }
            }

            return false;
        }
    }
 public class SingleParkingLot {
        private List<Level> levels;

        public SingleParkingLot(int numLevels, int spotsPerLevel) {
            levels = new ArrayList<>();
            for (int i = 0; i < numLevels; i++) {
                levels.add(new Level(i, spotsPerLevel));
            }
        }

        public boolean parkVehicle(Vehicle v) {
            for (Level level : levels) {
                if (level.parkVehicle(v)) {
                    System.out.println("Vehicle parked on level " + levels.indexOf(level));
                    return true;
                }
            }
            System.out.println("No available spot for vehicle");
            return false;
        }
    }

}