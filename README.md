# Mera Physics Ka Calculator

Name: Akash Dubey

For a particular RC circuit, the voltage (in volts) across the capacitor is represented by the equation
$v(t) = B(1 - e^-t/(RC))$

Suppose the parameters of the electric circuit are B = $12 V$, R = $500Ω$, and C = $0.25µF$.  

Consequently 
$v(t) = 12(1 - e^(-0.008t))$
where t has units of µs.

Create and then read a file params.txt containing the values for B, R, C, and the starting and ending values for t.  Write a file `rc.txt` of values for the time t and the corresponding capacitor voltage $v(t)$, where $t$ goes from the given starting value to the given ending value in 100 microsecond steps.  In this example, if t goes from $0$ to $1000 µs$, the twelfth entry in the output file would be 

110  7.02261


Then, read the file rc.txt and use the data to calculate the rise time.  The rise time is defined as the time required for the capacitor voltage to change from $0.06 B$ to $0.95 B$. Get the value for B, and find the data points that are closest to 0.05 B and 0.95 B
