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
    private static double TIME_CONSTANT = 1;
    private static final double TIME_STEPS = 100;

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
                // B, R, C, time_start, time_end
                String[] coordinates = line.split(" ");
                Parameter parameter = new Parameter(coordinates[0], coordinates[1], coordinates[2], coordinates[3], coordinates[4]);
                parameters.add(parameter);
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

    public static void calculateTimeConstant(double start, double end) {
        TIME_CONSTANT = (end - start) / TIME_STEPS;
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
            Parameter parameter = parameters.get(i);
            calculateTimeConstant(parameter.getTimeStart(), parameter.getTimeEnd());
            output += "Parameter " + (i + 1) + " " + parameter.toString() + ": \n";
            double[] outputArray = new double[(int) ((parameter.getTimeEnd() - parameter.getTimeStart()) / TIME_CONSTANT) + 1];
            for (double time = parameter.getTimeStart(); time <= parameter.getTimeEnd(); time += TIME_CONSTANT) {
                output += time + " " + parameter.getVTime(time) + " \n";
                outputArray[(int) ((time - parameter.getTimeStart()) / TIME_CONSTANT)] = parameter.getVTime(time);
            }

            // get the value closes to 0.05 B and 0.95 B
            double B = parameter.getB();
            double closestTo05 = 0;
            double closestTo95 = 0;
            for (int j = 0; j < outputArray.length; j++) {
                if (outputArray[j] >= 0.05 * B) {
                    // check if the one lower is closer
                    if (Math.abs(outputArray[j] - 0.05 * B) < Math.abs(outputArray[j - 1] - 0.05 * B)) {
                        closestTo05 = j;
                    } else {
                        closestTo05 = j - 1;
                    }
                    break;
                }
            }
            for (int j = 0; j < outputArray.length; j++) {
                if (outputArray[j] >= 0.95 * B) {
                    // check if the one lower is closer
                    if (Math.abs(outputArray[j] - 0.95 * B) < Math.abs(outputArray[j - 1] - 0.95 * B)) {
                        closestTo95 = j;
                    } else {
                        closestTo95 = j - 1;
                    }
                    break;
                }
            }
            output += "Time to reach 0.05 B: " + closestTo05 * TIME_CONSTANT + " microseconds\n";
            output += "Time to reach 0.95 B: " + closestTo95 * TIME_CONSTANT + " microseconds\n";
            output += "Rise Time in microseconds: " + (closestTo95 - closestTo05) * TIME_CONSTANT + " microseconds\n";
        }
        System.out.println(output);
        return output;
    }
}