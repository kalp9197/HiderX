import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

class FileManipulator {

    private String currentDirectory;
    private Scanner scanner;

    public FileManipulator() {
        this.currentDirectory = System.getProperty("user.home");
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Select your operating system:");
        System.out.println("1. macOS");
        System.out.println("2. Windows");
        System.out.println("3. Ubuntu/Linux");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.println("Running for macOS...");
                macManipulator();
                break;
            case 2:
                System.out.println("Running for Windows...");
                windowsManipulator();
                break;
            case 3:
                System.out.println("Running for Ubuntu/Linux...");
                linuxManipulator();
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                break;
        }
    }

    private void macManipulator() {
        while (true) {
            printMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the file name: ");
                    hideFile(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter the file name: ");
                    unhideFile(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter the file name: ");
                    encryptDecryptFile(scanner.nextLine());
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void windowsManipulator() {
        // Windows specific implementation
    }

    private void linuxManipulator() {
        // Linux specific implementation
    }

    private void printMenu() {
        System.out.println("┌──────────────────────────┐");
        System.out.println("│         Main Menu         │");
        System.out.println("├──────────────────────────┤");
        System.out.println("│ 1. Hide a file           │");
        System.out.println("│ 2. Unhide a file         │");
        System.out.println("│ 3. Encrypt/Decrypt a file│");
        System.out.println("│ 4. Exit                  │");
        System.out.println("└──────────────────────────┘");
    }

    private void hideFile(String fileName) {
        String filePath = currentDirectory + File.separator + fileName;

        try {
            // Construct the command to set the hidden flag
            String[] command = {"chflags", "hidden", filePath};

            // Execute the command
            Process process = Runtime.getRuntime().exec(command);

            // Wait for the process to finish
            int exitCode = process.waitFor();

            // Check the exit code to see if the command was successful
            if (exitCode == 0) {
                System.out.println("File hidden successfully.");
            } else {
                System.out.println("Failed to hide the file.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void unhideFile(String fileName) {
        String filePath = currentDirectory + File.separator + fileName;

        try {
            // Construct the command to remove the hidden flag
            String[] command = {"chflags", "nohidden", filePath};

            // Execute the command
            Process process = Runtime.getRuntime().exec(command);

            // Wait for the process to finish
            int exitCode = process.waitFor();

            // Check the exit code to see if the command was successful
            if (exitCode == 0) {
                System.out.println("File unhidden successfully.");
            } else {
                System.out.println("Failed to unhide the file.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void encryptDecryptFile(String fileName) {
        String filePath = currentDirectory + File.separator + fileName;

        try {
            FileInputStream fis = new FileInputStream(filePath);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();

            // Encryption/Decryption key (change this to a different value for real-world use)
            char key = 'X';

            // Perform XOR encryption/decryption
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (data[i] ^ key);
            }

            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(data);
            fos.close();

            System.out.println("File encrypted/decrypted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileManipulator fileManipulator = new FileManipulator();
        fileManipulator.start();
    }
}
