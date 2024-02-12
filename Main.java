import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Parameter {
    double B, R, C; // Volts, Ohms, Micro Farads
    double time_start, time_end; // Micro Seconds

    public Parameter(double B, double R, double C, double time_start, double time_end) {
        this.B = B;
        this.R = R;
        this.C = C;
        this.time_start = time_start;
        this.time_end = time_end;
    }

    public Parameter(String B, String R, String C, String time_start, String time_end) {
        this.B = Double.parseDouble(B);
        this.R = Double.parseDouble(R);
        this.C = Double.parseDouble(C);
        this.time_start = Double.parseDouble(time_start);
        this.time_end = Double.parseDouble(time_end);
    }

    public double getVTime(double time) {
        return B * (1 - Math.exp(-time / R / C));
    }

    @Override
    public String toString() {
        return "B: " + B + ", R: " + R + ", C: " + C + ", time_start: " + time_start + ", time_end: " + time_end;
    }

    public double getB() {
        return B;
    }

    public double getR() {
        return R;
    }

    public double getC() {
        return C;
    }

    public double getTimeStart() {
        return time_start;
    }

    public double getTimeEnd() {
        return time_end;
    }
}

public class Main {
    private static final double TIME_CONSTANT = 0.1;

    public static void main(String args[]) throws IOException {
        BufferedReader reader = null;
        BufferedWriter output = null;

        ArrayList<Parameter> parameters = new ArrayList<Parameter>();

        try {
            reader = new BufferedReader(new FileReader("params.txt"));
            output = new BufferedWriter(new FileWriter("output.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into individual coordinates
                String[] coordinates = line.split(" ");
                Parameter point = new Parameter(coordinates[0], coordinates[1], coordinates[2], coordinates[3], coordinates[4]);
                parameters.add(point);
            }

            printParameters(parameters);

            writeTextToFile(getTimes(parameters), output);

            // Write parameters to the output file
            writeTextToFile(getTimes(parameters), output);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }

    public static void writeTextToFile(String text, BufferedWriter file) throws IOException {
        String[] printer = text.split("\n");
        for (String line : printer) {
            file.write(line);
            file.newLine();
        }
    }

    private static String printParameters(ArrayList<Parameter> parameters) {
        String output = "";
        for (Parameter p : parameters) {
            output += p.toString() + '\n';
        }
        System.out.println("Parameters: ");
        System.out.println(output);
        return output;
    }

    private static String getTimes(ArrayList<Parameter> parameters) {
        String output = "";
        for (int i = 0; i < parameters.size(); i++) {
            output += "Parameter " + (i + 1) + ": ";
            for (double time = parameters.get(i).getTimeStart(); time <= parameters.get(i).getTimeEnd(); time += TIME_CONSTANT) {
                output += parameters.get(i).getVTime(time) + " ";
            }
        }
        System.out.println(output);
        return output;
    }
}