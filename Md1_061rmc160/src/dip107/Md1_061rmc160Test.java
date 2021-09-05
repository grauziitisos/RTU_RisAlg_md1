package dip107;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class Md1_061rmc160Test {

    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream console;

    @Test
    public void shouldPrintAplnrVardsUzvardsGrupasNr() throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput("1", "1"), "dip107.Md1_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
        assertEquals(output[0], "061RMC160 Oskars Grauzis 4");
        assertEquals(output[output.length - 1], "red");
    }

    @Test
    public void shouldRetryOnWrongInput() throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput("a", "1", "1"), "dip107.Md1_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
        assertEquals(output[1], "x=");
        assertEquals(output[output.length - 1], "red");
    }

    // region valTests
    @ParameterizedTest
    @CsvFileSource(resources = "/positive-tests.csv", numLinesToSkip = 1)
    void shouldPassAllColorMappingTests(String... inputs) throws Exception {
        String lastOne = inputs[inputs.length - 1];
        byteArrayOutputStream = new ByteArrayOutputStream();
        int cnt = 1;
        String[] varargsMinusLastOne = new String[inputs.length - 1];
        for (String s : inputs)
            if (cnt != inputs.length) {
                varargsMinusLastOne[cnt - 1] = s;
                cnt++;
            }
        runTest(getSimulatedUserInput(varargsMinusLastOne), "dip107.Md1_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
        assertEquals(output[output.length - 1], lastOne);
    }

    // endregion
    // region utils
    private String getSimulatedUserInput(String... inputs) {
        return String.join(System.getProperty("line.separator"), inputs)
                + System.getProperty("line.separator");
    }

    private void runTest(final String data, final String className) throws Exception {

        final InputStream input = new ByteArrayInputStream(data.getBytes("UTF-8"));;

        final Class<?> cls = Class.forName(className);
        final Method meth =
                cls.getDeclaredMethod("testableMain", InputStream.class, PrintStream.class);
        meth.invoke(null, input, new PrintStream(byteArrayOutputStream));
    }
    // endregion
}
