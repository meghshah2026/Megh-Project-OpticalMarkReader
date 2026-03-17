import FileIO.PDFHelper;
import Filters.DisplayInfoFilter;
import core.DImage;
import core.DisplayWindow;
import processing.core.PImage;

public class VisualFilterTest {
    public static String currentFolder = System.getProperty("user.dir") + "/";
    public static String pdf_filename = "OfficialOMRSampleDoc.pdf";
    public static String output_name = pdf_filename.substring(0, pdf_filename.length()-4);

    public static void main(String[] args) {
        int pageToDisplay = 1;


        PImage img = PDFHelper.getPageImage("assets/" + pdf_filename,pageToDisplay);


        String save_name = currentFolder + "assets/" + output_name + " page" + pageToDisplay + ".png";
        img.save(save_name);


        DisplayWindow.showFor(save_name);
    }
}