import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sergey_Stefoglo on 8/30/2016.
 */
public class Run {
    private static FileRunnable myRunner;
    public static final String REGEX_FORMAT = "(([C,D,E,F,G,H,c,d,e,f,h,g][:][\\\\])([\\w,\\W]{0,}))";
    public static final String STOP = "stop";
    public static final String DOESNT_WORK = "Thread doesn't work";
    public static final String STOP_COMMAND = "Process is start, for stopping process please enter 'stop'";
    public static final String ACTIVE_THREAD = "Thread is active now, for stop Theard enter stop";
    public static final String INCORRECT = "incorrect command";

    public static void main(String[] args) throws InterruptedException {
        Pattern pattern = Pattern.compile(REGEX_FORMAT);
        Scanner sc = new Scanner(System.in);
        Thread myThread = null;
        System.out.println(FileRunnable.START_MESSAGE);
        while (sc.hasNextLine()) {


            String command = sc.nextLine();
            Matcher nameMatcher = pattern.matcher(command);
            if (command.equalsIgnoreCase(STOP)) {
                if (myThread != null && myRunner.isActive()) {
                    myThread.stop();
                    myRunner.showInfo();
                    myRunner.setActive(false);
                } else {
                    System.out.println(DOESNT_WORK);
                }

            } else if (nameMatcher.matches()) {
                if (myRunner != null && myRunner.isActive()) {
                    System.out.println(ACTIVE_THREAD);
                } else {
                    myRunner = new FileRunnable(command);
                    myThread = new Thread(myRunner);
                    myThread.start();
                    System.out.println(STOP_COMMAND);
                }
            } else {
                System.out.println(INCORRECT);
            }


        }

    }

}