import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Point {
    double x, y, z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(String x, String y, String z) {
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.z = Double.parseDouble(z);
    }

    public double distance(Point p) {
        return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) + Math.pow(p.z - z, 2));
    }

    public Point center(Point p) {
        return new Point((x + p.x) / 2, (y + p.y) / 2, (z + p.z) / 2);
    }

    public Point add(Point p) {
        return new Point(x + p.x, y + p.y, z + p.z);
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }

    public Point divide(int n) {
        return new Point(x / n, y / n, z / n);
    }
}

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = null;
        BufferedWriter output = null;

        ArrayList<Point> points = new ArrayList<Point>();

        try {
            reader = new BufferedReader(new FileReader("points.txt"));
            output = new BufferedWriter(new FileWriter("output.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into individual coordinates
                String[] coordinates = line.split(" ");
                Point point = new Point(coordinates[0], coordinates[1], coordinates[2]);
                points.add(point);
            }

            printPoints(points);
            printMagnitudes(points);

            writeTextToFile(printMagnitudes(points), output);

            System.out.println("Center: " + calculateCenter(points));
            
            Scanner scanner = new Scanner(System.in);

            // Allow users to add points
            System.out.println("Do you want to add more points? (y/n): ");
            String userInput = scanner.next().toLowerCase();
            boolean addMorePoints = userInput.equals("y");
            while (addMorePoints) {
                Point newPoint = getUserInput(scanner);
                points.add(newPoint);

                System.out.println("Do you want to add more points? (y/n): ");
                userInput = scanner.next().toLowerCase();
                addMorePoints = userInput.equals("y");
            }

            // Perform operations on points
            System.out.println("Center: " + calculateCenter(points));

            // Write points to the output file
            writeToFile(points);
            writeTextToFile(printMagnitudes(points), output);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }

    private static Point calculateCenter(ArrayList<Point> points) {
        Point center = new Point(0,0,0);
        for (Point p : points) {
            center = center.add(p);
        }

        center = center.divide(points.size());
        return center;
    }

    private static Point getUserInput(Scanner scanner) {
        System.out.println("Enter the x coordinate of the point: ");
        double x = scanner.nextDouble();
        System.out.println("Enter the y coordinate of the point: ");
        double y = scanner.nextDouble();
        System.out.println("Enter the z coordinate of the point: ");
        double z = scanner.nextDouble();
        return new Point(x, y, z);
    }

    private static void writeToFile(ArrayList<Point> points) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter("points.txt"));
        for (Point p : points) {
            output.write(p.toString());
            output.newLine();
        }
        output.close();
    }

    public static void writeTextToFile(String text, BufferedWriter file) throws IOException {
        
        String[] printer = text.split("\n");
        for (String line : printer) {
            file.write(line);
            file.newLine();
        }
    }

    private static String printPoints(ArrayList<Point> points) {
        String output = "";
        for (Point p : points) {
            output += p.toString() + '\n';
        }
        System.out.println("Points: ");
        System.out.println(output);
        return output;
    }

    private static String printMagnitudes(ArrayList<Point> points) {
        String output = "";
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                output += "Distance between " + points.get(i) + " and " + points.get(j) + " is " + points.get(i).distance(points.get(j)) + '\n';
            }
        }
        System.out.println(output);
        return output;
    }
}