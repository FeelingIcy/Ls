import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.kohsuke.args4j.*;

public class Main {
    public static void main(String[] args) throws IOException, CmdLineException {
        Args arguments = new Args(args);
        start(arguments);
    }

    public static void start(Args args) throws IOException {
        FileInfoList listOfFiles = new FileInfoList(args.dir);
        if (!args.longFormat && args.humanReadable) throw new IllegalArgumentException();
        if (args.out == null) {
            System.out.println(listOfFiles);
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
