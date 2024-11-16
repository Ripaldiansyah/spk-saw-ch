package chloro.util;

import java.io.File;

public class Modules {
    // Aktifin kalo
    // mau generate aja

    public static void main(String[] args) {
        generateModule();
    }

    private static void generateModule() {
        String[] modules = {
                "login",
                "user",
                "home",
                "alternative",
                "criteria",
                "spk",
                "report",
                "dashboard",
                "spk_count",
                "spk_result"
        };

        for (String module : modules) {
            createDirectories("app/src/main/java/chloro/" + module + "/model");
            createDirectories("app/src/main/java/chloro/" + module + "/view");
            createDirectories("app/src/main/java/chloro/" + module + "/controller");
            createDirectories("app/src/main/java/chloro/" + module + "/dao");
            createDirectories("app/src/main/java/chloro/" + module + "/service");
        }

    }

    private static void createDirectories(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directories created: " + path);
            } else {
                System.out.println("Failed to create directories: " + path);
            }
        } else {
            System.out.println("Directories already exist: " + path);
        }
    }
}
