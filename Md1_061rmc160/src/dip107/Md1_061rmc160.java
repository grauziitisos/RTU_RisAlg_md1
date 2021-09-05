
package dip107;

import java.io.InputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

public class Md1_061rmc160 {
    // region utils
    private static String makeFloatString(String inputString) {
        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        char sep = symbols.getDecimalSeparator();
        if (inputString.indexOf(sep) > -1)
            return inputString;
        else {
            char otherSep = sep == ',' ? '.' : ',';
            return inputString.replace(otherSep, sep);
        }
    }

    // no passing by reference possible in Java at all?? aww...
    private static float getInput(Scanner sc, PrintStream outputStream, char varName) {
        for (int i = 0; i < 4; i++) {
            outputStream.print(varName + "=");
            // infinity is an invalid value legal float value example for coordinates!
            if (sc.hasNext("[+-]?[\\d]+([.,]\\d+)*")) {
                return Float.parseFloat(makeFloatString(sc.next()));
            } else {
                sc.next();
                outputStream.println();
                outputStream.println("nepareiza formāta ievade! Lūdzu ievadiet reālu skaitli - "
                        + varName + " koordinātu!");
                        outputStream.println("Mēģinājums #" + (i + 1) + " no 3");
            }
        }
        System.exit(-1);
        // system is exiting.. But the function must have return statement for lint.. therefore
        return -11111111.222222f;
    }
    // endregion

    // region main
    public static void main(String[] args) {
        testableMain(System.in, System.out);
    }

    public static void testableMain(InputStream inputStream, PrintStream outputStream){
        Scanner sc = new Scanner(inputStream);
        float x = 0, y = 0;
        outputStream.println("061RMC160 Oskars Grauzis 4");
        x = getInput(sc, outputStream, 'x');
        y = getInput(sc, outputStream, 'y');
        outputStream.println();
        outputStream.println("result:");
        if ((x >= 1 && x <= 3 || x >= 11 && x <= 13) && y >= 1 && y <= 8)
            outputStream.println("red");
        else if ((x - 7) * (x - 7) + (y - 4) * (y - 4) <= 9)
            outputStream.println("green");
        else if (y >= 8 && y <= x + 6 && y <= 20 - x)
            outputStream.println("blue");
        else
            outputStream.println("white");
    }
    // endregion
}
