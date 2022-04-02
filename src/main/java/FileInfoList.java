import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FileInfoList {
    List<FileInfo> files = new ArrayList<>();

    public FileInfoList(String way) {
        File dir = new File(way);
        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                files.add(new FileInfo(file));
            }
        } else files.add(new FileInfo(dir));
    }

    public void toHumanReadable() {
        for (FileInfo file : files) {
            file.toHumanReadable();
        }
    }

    public void toLongFormat() {
        for (FileInfo file : files) {
            file.toLongFormat();
        }
    }

    public void reverse() {
        Collections.reverse(files);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < files.size(); i++) {
            result.append(files.get(i).toString());
            if (i < files.size() - 1) result.append("\n");
        }
        return result.toString();
    }
}
