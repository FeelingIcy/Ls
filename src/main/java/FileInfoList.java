import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FileInfoList {
    private final List<FileInfo> files = new ArrayList<>();

    public FileInfoList(String way) {
        File dir = new File(way);
        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                files.add(new FileInfo(file));
            }
        } else files.add(new FileInfo(dir));
    }

    public String toString(Args args) {
        if (args.reverse) Collections.reverse(files);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < files.size(); i++) {
            result.append(files.get(i).toString(args));
            if (i < files.size() - 1) result.append("\n");
        }
        return result.toString();
    }
}
