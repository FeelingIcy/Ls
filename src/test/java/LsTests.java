import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LsTests {
    public void testOutput(boolean l, boolean h, boolean r, String expected) throws IOException {
        String dir = "C:\\Users\\SVETLANA\\IdeaProjects\\Ls\\files";
        String o = "output.txt";
        Main.start(l, h, r, o, dir);
        File output = new File(o);
        assertEquals(Files.readString(output.toPath()), expected);
        output.delete();
    }

    @Test
    public void testL() throws IOException {
        testOutput(true, false, false,
                "Vopros_10.docx 111 88041 31/01/2022 22:32:54\n" +
                        "Vopros_21.docx 111 55720 31/01/2022 22:33:00\n" +
                        "Vopros_3.docx 111 160181 29/01/2022 17:14:12\n" +
                        "Vopros_9.docx 111 74587 31/01/2022 22:32:50");
    }

    @Test
    public void testH() throws IOException {
        testOutput(true, true, false,
                "Vopros_10.docx rwx 86,0KB 31/01/2022 22:32:54\n" +
                        "Vopros_21.docx rwx 54,4KB 31/01/2022 22:33:00\n" +
                        "Vopros_3.docx rwx 156,4KB 29/01/2022 17:14:12\n" +
                        "Vopros_9.docx rwx 72,8KB 31/01/2022 22:32:50");
    }

    @Test
    public void testR() throws IOException {
        testOutput(false, false, true,
                "Vopros_9.docx   \n" +
                        "Vopros_3.docx   \n" +
                        "Vopros_21.docx   \n" +
                        "Vopros_10.docx   ");
    }

    @Test
    public void testWithoutArguments() throws IOException {
        testOutput(false, false, false,
                "Vopros_10.docx   \n" +
                        "Vopros_21.docx   \n" +
                        "Vopros_3.docx   \n" +
                        "Vopros_9.docx   ");
    }

    @Test
    public void testLR() throws IOException {
        testOutput(true, false, true,
                "Vopros_9.docx 111 74587 31/01/2022 22:32:50\n" +
                        "Vopros_3.docx 111 160181 29/01/2022 17:14:12\n" +
                        "Vopros_21.docx 111 55720 31/01/2022 22:33:00\n" +
                        "Vopros_10.docx 111 88041 31/01/2022 22:32:54");
    }
}
