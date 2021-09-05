
package dip107;

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
    private static float getInput(Scanner sc, char varName) {
        for (int i = 0; i < 4; i++) {
            System.out.print(varName + "=");
            // infinity is an invalid value legal float value example for coordinates!
            if (sc.hasNext("[+-]?[\\d]+([.,]\\d+)*")) {
                return Float.parseFloat(makeFloatString(sc.next()));
            } else {
                sc.next();
                System.out.println("nepareiza formāta ievade! Lūdzu ievadiet reālu skaitli - "
                        + varName + " koordinātu!");
                System.out.println("Mēģinājums #" + (i + 1) + " no 3");
            }
        }
        System.exit(-1);
        // system is exiting.. But the function must have return statement for lint.. therefore
        return -11111111.222222f;
    }
    // endregion

    // region main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float x = 0, y = 0;
        System.out.println("061RMC160 Oskars Grauzis 4");
        x = getInput(sc, 'x');
        y = getInput(sc, 'y');
        System.out.print("y=" + y);
        System.out.println();
        System.out.println("result:");
        if ((x >= 1 && x <= 3 || x >= 11 && x <= 13) && y >= 1 && y <= 8)
            System.out.println("red");
        else if ((x - 7) * (x - 7) + (y - 4) * (y - 4) <= 9)
            System.out.println("green");
        else if (y >= 8 && y <= x + 6 && y <= 20 - x)
            System.out.println("blue");
        else
            System.out.println("white");
    }
    // endregion
}
