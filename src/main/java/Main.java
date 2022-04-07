import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import org.kohsuke.args4j.*;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            try {
                Args arguments;
                if (i == 0) arguments = new Args(args);
                else {
                    Scanner scanner = new Scanner(System.in);
                    String stringArgs = scanner.nextLine();
                    if (Objects.equals(stringArgs, "exit")) return;
                    arguments = new Args(stringArgs.split("\\s+"));
                }
                start(arguments);
                return;
            } catch (CmdLineException e) {
                System.out.println("Неправильно введены аргументы");
            } catch (IOException e) {
                System.out.println("Что-то пошло не так с директорией");
            } catch (IllegalArgumentException e) {
                System.out.println("Флаг -h не может быть без -l");
            }
        }
    }

    public static void start(Args args) throws IOException {
        FileInfoList listOfFiles = new FileInfoList(args.dir);
        if (!args.longFormat && args.humanReadable) throw new IllegalArgumentException();
        if (args.out == null) {
            System.out.println(listOfFiles.toString(args));
        } else {
            BufferedWriter writer = new BufferedWriter(new FileWriter(args.out));
            writer.write(listOfFiles.toString(args));
            writer.close();
        }
    }
}

class Args {
    @Option(name = "-l", usage = "switches the output to a long format")
    boolean longFormat;

    @Option(name = "-h", usage = "switches the output to a human-readable format")
    boolean humanReadable;

    @Option(name = "-r", usage = "reverse")
    boolean reverse;

    @Option(name = "-o", usage = "output to this file <output.file> )", metaVar = "OUTPUT")
    String out;

    @Argument(metaVar = "dir")
    String dir;

    Args(String[] args) throws CmdLineException {
        CmdLineParser parser = new CmdLineParser(this);
        parser.parseArgument(args);
        if (dir == null) {
            dir = System.getProperty("user.dir");
        }
    }

    Args(boolean l, boolean h, boolean r, String o, String dir) {
        longFormat = l;
        humanReadable = h;
        reverse = r;
        out = o;
        this.dir = dir;
    }
}
