import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.kohsuke.args4j.*;

public class Main {
    public static void main(String[] args) throws IOException, CmdLineException {
        Args arguments = new Args(args);
        boolean l = arguments.longFormat;
        boolean h = arguments.humanReadable;
        boolean r = arguments.reverse;
        String o = arguments.out;
        String dir = arguments.dir;
        start(l, h, r, o, dir);
    }

    public static void start(boolean l, boolean h, boolean r, String o, String dir) throws IOException {
        FileInfoList listOfFiles = new FileInfoList(dir);
        if (!l && h) throw new IllegalArgumentException();
        if (l) listOfFiles.toLongFormat();
        if (h) listOfFiles.toHumanReadable();
        if (r) listOfFiles.reverse();
        if (o == null) {
            System.out.println(listOfFiles);
        } else {
            BufferedWriter writer = new BufferedWriter(new FileWriter(o));
            writer.write(listOfFiles.toString());
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
}
