import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey_Stefoglo on 8/30/2016.
 */
public class FileRunnable implements Runnable {
    private File file;
    private long folderCount;
    private long fileCount;
    private long countSize;
    private boolean active;
    public static final String START_MESSAGE = "Please show path to folder";
    public static final String COUNT_FOLDERS = "count of folders: ";
    public static final String COUNT_FILES = "count of files: ";
    public static final String COMMON_SIZE = "common files size: ";
    List<File> all = new ArrayList<File>();

    public FileRunnable(String path) {
        file = new File(path);


    }

    public void run() {
        active = true;
        addTree(file, this.all);
        showInfo();
        active = false;

    }

    void addTree(File file, List<File> all) {
        try {

            // for test
            //Thread.sleep(500);
            File[] children = file.listFiles();
            if (children != null) {
                folderCount++;
                for (File child : children) {

                    countSize = countSize + child.length();

                    all.add(child);
                    addTree(child, all);
                }
            } else {
                fileCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<File> getAll() {
        return all;
    }

    public void setAll(List<File> all) {
        this.all = all;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public void showInfo() {
        if (!Thread.interrupted()) {
            System.out.println(COUNT_FOLDERS + folderCount);
            System.out.println(COUNT_FILES + fileCount);
            System.out.println(COMMON_SIZE + countSize);
            System.out.println(START_MESSAGE);
            fileCount = 0;
            folderCount = 0;
            countSize = 0;
        }
    }

}

