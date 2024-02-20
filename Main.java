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
    private static double VAKT_KA_NUM = 1;
    private static final double TIME_STEPS = 100;

    public static void main(String args[]) throws IOException {
        BufferedReader reader = null;
        BufferedWriter katchara = null;

        ArrayList<Parameter> parameters = new ArrayList<Parameter>();

        try {
            reader = new BufferedReader(new FileReader("params.txt"));
            katchara = new BufferedWriter(new FileWriter("katchara.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into individual coordinates
                // B, R, C, time_start, time_end
                String[] coordinates = line.split(" ");
                Parameter parameter = new Parameter(coordinates[0], coordinates[1], coordinates[2], coordinates[3], coordinates[4]);
                parameters.add(parameter);
            }

            printParameters(parameters);

            writeTextToFile(getTimes(parameters), katchara);

            // Write parameters to the katchara file
            writeTextToFile(getTimes(parameters), katchara);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (katchara != null) {
                katchara.close();
            }
        }
    }

    public static void calculateTimeConstant(double start, double end) {
        VAKT_KA_NUM = (end - start) / TIME_STEPS;
    }

    public static void writeTextToFile(String text, BufferedWriter file) throws IOException {
        String[] printer = text.split("\n");
        for (String line : printer) {
            file.write(line);
            file.newLine();
        }
    }

    private static String printParameters(ArrayList<Parameter> parameters) {
        String katchara = "";
        for (Parameter p : parameters) {
            katchara += p.toString() + '\n';
        }
        System.out.println("Parameters: ");
        System.out.println(katchara);
        return katchara;
    }

    private static String getTimes(ArrayList<Parameter> parameters) {
        String katchara = "";
        for (int i = 0; i < parameters.size(); i++) {
            Parameter parameter = parameters.get(i);
            calculateTimeConstant(parameter.getTimeStart(), parameter.getTimeEnd());
            katchara += "Parameter " + (i + 1) + " " + parameter.toString() + ": \n";
            double[] katcharaArray = new double[(int) ((parameter.getTimeEnd() - parameter.getTimeStart()) / VAKT_KA_NUM) + 1];
            for (double time = parameter.getTimeStart(); time <= parameter.getTimeEnd(); time += VAKT_KA_NUM) {
                katchara += time + " " + parameter.getVTime(time) + " \n";
                katcharaArray[(int) ((time - parameter.getTimeStart()) / VAKT_KA_NUM)] = parameter.getVTime(time);
            }

            // get the value closes to 0.05 B and 0.95 B
            double B = parameter.getB();
            double paas05 = 0;
            double paas95 = 0;
            for (int j = 0; j < katcharaArray.length; j++) {
                if (katcharaArray[j] >= 0.05 * B) {
                    // check if the one lower is closer
                    if (Math.abs(katcharaArray[j] - 0.05 * B) < Math.abs(katcharaArray[j - 1] - 0.05 * B)) {
                        paas05 = j;
                    } else {
                        paas05 = j - 1;
                    }
                    break;
                }
            }
            for (int j = 0; j < katcharaArray.length; j++) {
                if (katcharaArray[j] >= 0.95 * B) {
                    // check if the one lower is closer
                    if (Math.abs(katcharaArray[j] - 0.95 * B) < Math.abs(katcharaArray[j - 1] - 0.95 * B)) {
                        paas95 = j;
                    } else {
                        paas95 = j - 1;
                    }
                    break;
                }
            }
            katchara += "Time to reach 0.05 B: " + paas05 * VAKT_KA_NUM + " microseconds\n";
            katchara += "Time to reach 0.95 B: " + paas95 * VAKT_KA_NUM + " microseconds\n";
            katchara += "Rise Time in microseconds: " + (paas95 - paas05) * VAKT_KA_NUM + " microseconds\n";
        }
        System.out.println(katchara);
        return katchara;
    }
}