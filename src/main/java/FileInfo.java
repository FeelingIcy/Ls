import java.io.File;
import java.text.SimpleDateFormat;

public class FileInfo {
    File file;
    String name;
    String rights = "";
    String size = "";
    String time = "";

    @Override
    public String toString() {
        return name + " " + rights + " " + size + " " + time;
    }

    public FileInfo(File fl) {
        file = fl;
        name = file.getName();
    }

    public void toLongFormat() {
        if (file.canRead()) rights += 1;
        else rights += 0;
        if (file.canWrite()) rights += 1;
        else rights += 0;
        if (file.canExecute()) rights += 1;
        else rights += 0;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        time = sdf.format(file.lastModified());

        size = String.valueOf(file.length());
    }

    public void toHumanReadable() {
        if (file.canRead()) rights = "r";
        else rights = "-";
        if (file.canWrite()) rights += "w";
        else rights += "-";
        if (file.canExecute()) rights += "x";
        else rights += "-";

        double longSize = Double.parseDouble(size);
        String end = "B";
        while (longSize / 1024 > 1) {
            longSize /= 1024;
            switch (end) {
                case "B":
                    end = "KB";
                    break;
                case "KB":
                    end = "MB";
                    break;
                case "MB":
                    end = "GB";
                    break;
            }
        }
        String result;
        if (end.equals("B")) result = String.format("%.0f", longSize);
        else result = String.format("%.1f", longSize);
        size = result + end;
    }
}
