import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.attribute.standard.NumberOfDocuments;

public class FindFiles {
    private ArrayList<File> filesList = new ArrayList<>();
    private File foundFile;

    private void findFiles(File file) {
        if (file.isFile()) {
            System.out.println(file.getAbsolutePath());
            filesList.add(file);
        } else {
            File[] files = file.listFiles();
            for (File file1 : files) {
                findFiles(file1);
            }
        }
    }

    private void findEnteredWord(File file, String searchWord) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = "";
            str = bufferedReader.readLine();

            if (str.contains(searchWord)) {
                foundFile = file;
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        FindFiles findFiles = new FindFiles();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a searchword: ");
        String searchWord = scanner.nextLine();
        scanner.close();

        String userDirectory = new File("").getAbsolutePath();
        File file = new File(userDirectory.concat("//bin"));

        findFiles.findFiles(file);

        for (File currentFile : findFiles.filesList) {

            findFiles.findEnteredWord(currentFile, searchWord);
        }

        if (findFiles.foundFile == null) {
            System.out.println("Found no file");
        } else if (findFiles.foundFile.length() > 0) {
            System.out.println("Search word \"" + searchWord + "\" was found in:");
            System.out.println(findFiles.foundFile);
        } else {
            System.out.println("Found no file");
        }
    }
}
