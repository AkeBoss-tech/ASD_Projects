import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader("points.txt"));
            writer = new BufferedWriter(new FileWriter("output.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into individual coordinates
                String[] coordinates = line.split(" ");
                if (coordinates.length == 3) {
                    // Parse coordinates as double values
                    double x1 = Double.parseDouble(coordinates[0]);
                    double y1 = Double.parseDouble(coordinates[1]);
                    double z1 = Double.parseDouble(coordinates[2]);

                    // Read the next line for the second set of coordinates
                    line = reader.readLine();
                    if (line != null) {
                        // Split the second line into individual coordinates
                        coordinates = line.split(" ");
                        if (coordinates.length == 3) {
                            // Parse the second set of coordinates as double values
                            double x2 = Double.parseDouble(coordinates[0]);
                            double y2 = Double.parseDouble(coordinates[1]);
                            double z2 = Double.parseDouble(coordinates[2]);

                            // Calculate the distance using the distance formula
                            double distance = calculateDistance(x1, y1, z1, x2, y2, z2);

                            // Write the distance to the output file
                            writer.write(Double.toString(distance));
                            writer.newLine();
                        }
                    }
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    private static double calculateDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
    }
}