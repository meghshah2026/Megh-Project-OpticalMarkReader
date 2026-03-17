import FileIO.PDFHelper;
import Filters.DisplayInfoFilter;
import core.DImage;
import processing.core.PImage;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class OpticalMarkReaderMain {



    public static void main(String[] args) {
        String pathToPdf = fileChooser();

        ArrayList<String> key = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            DisplayInfoFilter filter = new DisplayInfoFilter();
            PImage in = PDFHelper.getPageImage("assets/OfficialOMRSampleDoc.pdf", i);
            DImage img = new DImage(in);

            if (i == 1) {
                System.out.print("page, # right, ");
                key = filter.AnswerKey(img);


                for (int j = 1; j <= key.size(); j++) {
                    System.out.print("q" + j + ", ");
                }
                System.out.println();
            } else {
                ArrayList<String> answer = filter.StudentAnswer(img);
                int counter = 0;

                for (int k = 0; k <= answer.size()-2; k++) {

                    if (key.get(k).equals(answer.get(k))) {
                        counter++;
                    }
                }

                System.out.print(i + ", " + counter + ", ");

                for (int l = 0; l <= answer.size()-2; l++) {
                    if (key.get(l).equals(answer.get(l))) {
                        System.out.print("right, ");

                    }
                    else {
                        System.out.print("wrong, ");
                    }
                }
                System.out.println();
            }




        }
        

    }

    private static String fileChooser() {
        String userDirLocation = System.getProperty("user.dir");
        File userDir = new File(userDirLocation);
        JFileChooser fc = new JFileChooser(userDir);
        int returnVal = fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        return file.getAbsolutePath();
    }
}
