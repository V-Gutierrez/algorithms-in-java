
import java.util.Scanner;

public class GeoHashTest {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int precision = 12;

            System.out.print("Precision is set to " + precision + "\n");

            System.out.print("\n\nEnter latitude (-90 to 90): ");
            double latitude = scanner.nextDouble();

            while (latitude < -90 || latitude > 90) {
                System.out.print("\n\nInvalid latitude. Enter latitude (-90 to 90): ");
                latitude = scanner.nextDouble();
            }

            System.out.print("\n\nEnter longitude (-180 to 180): ");
            double longitude = scanner.nextDouble();

            while (longitude < -180 || longitude > 180) {
                System.out.print("\n\nInvalid longitude. Enter longitude (-180 to 180): ");
                longitude = scanner.nextDouble();
            }

            String geoHash = GeoHash.encode(latitude, longitude, precision);
            double[] decodedGeoHash = GeoHash.decode(geoHash);

            System.out.println("Geohashed geolocation: " + geoHash);
            System.out.println("Decoded geolocation: " + decodedGeoHash[0] + ", " + decodedGeoHash[1]);
        }
    }
}
