package mobile.core.utils;


import com.sun.jna.Platform;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CommandLineUtils {


    public static final String KILL_NODE_PROCESS_WINDOWS = "taskkill /IM node.exe /F";

    public static List<String> execConsoleCommand(String command) {
        String[] consoleCommand = System.getProperty("os.name").contains("Windows") ?
                new String[]{"cmd", "/Q", "/C " + command} :
                new String[]{"bash", "-l", "-c", command};

        List<String> deviceList = new ArrayList<>();
        try {
            String line;
            Process process = Runtime.getRuntime().exec(consoleCommand);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            try {
                while ((line = reader.readLine()) != null) {
                    if (line != null) {
                        deviceList.add(line);
                    }
                }
                line = stdError.readLine();
                if (line != null) {
                    log.error("Error response for command -> " + command);
                    log.error(line);
                }
                while ((line = stdError.readLine()) != null) {
                    log.error(line);
                }
            } finally {
                reader.close();
            }
            return deviceList;
        } catch (Exception e) {
            log.error("Error executing console command: " + e.getMessage());
        }
        return deviceList;
    }

    public static void killNodeProcess() {
        if (Platform.isWindows()) {
            execConsoleCommand(KILL_NODE_PROCESS_WINDOWS);
            log.info("Killing node process on Windows");
        } else {
            //TODO:
        }
    }
}
