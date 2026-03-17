package Filters;

import Interfaces.PixelFilter;
import core.DImage;
import core.DisplayWindow;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class DisplayInfoFilter implements PixelFilter {
    private int threshold;
    private String letter;


    public DisplayInfoFilter() {

        threshold = 166;
    }

    @Override
    public DImage processImage(DImage img) {
        return img;
    }

    public ArrayList<String> StudentAnswer(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        grid = crop(grid, 0, 0, 700, 500);
        ArrayList<String> StudentAnswer = new ArrayList<>();
        int filledIn = 0;
        int counter = 0;
        for (int r = 120; r < grid.length; r += 48) {
            for (int j = 114; j <= 215; j += 25) {
                int bubble = (grid[r][j] + grid[r - 1][j - 1] + grid[r - 1][j] + grid[r - 1][j + 1] + grid[r][j - 1] + grid[r][j + 1] + grid[r + 1][j - 1] + grid[r + 1][j] + grid[r + 1][j + 1]) / 9;
                if (bubble > threshold) {
                    filledIn++;
                } else if (bubble <= threshold) {
                    filledIn++;
                    if (filledIn == 1) {
                        StudentAnswer.add("A");
                        filledIn++;
                        counter++;
                    } else if (filledIn == 2) {
                        StudentAnswer.add("B");
                        filledIn++;
                        counter++;
                    } else if (filledIn == 3) {
                        StudentAnswer.add("C");
                        filledIn++;
                        counter++;
                    } else if (filledIn == 4) {
                        StudentAnswer.add("D");
                        filledIn++;
                        counter++;
                    } else if (filledIn == 5) {
                        StudentAnswer.add("E");
                        filledIn++;
                        counter++;
                    }
                }
            }
            if (counter != 1) {
                for (int k = 0; k < counter; k++) {
                    StudentAnswer.remove(StudentAnswer.size() - 1);
                }
                StudentAnswer.add("F");
                filledIn = 0;
                counter = 0;
            } else {
                filledIn = 0;
                counter = 0;
            }
        }
        img.setPixels(grid);
        return StudentAnswer;
    }

    public ArrayList<String> AnswerKey(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        grid = crop(grid, 0, 0, 700, 500);
        ArrayList<String> AnswerKey = new ArrayList<>();
        int filledIn = 0;
        int questionsRight = 0;


        for (int r = 117; r < grid.length; r += 48) {
            boolean nextroww = false;

            for (int j = 115; j <= 215 && !nextroww; j += 25) {
                if (grid[r][j] > threshold) {
                    filledIn++;
                } else if (grid[r][j] <= threshold) {
                    filledIn++;
                    if (filledIn == 1) {
                        AnswerKey.add("A");
                        questionsRight++;
                    } else if (filledIn == 2) {
                        AnswerKey.add("B");
                        questionsRight++;
                    } else if (filledIn == 3) {
                        AnswerKey.add("C");
                        questionsRight++;
                    } else if (filledIn == 4) {
                        AnswerKey.add("D");
                        questionsRight++;
                    } else if (filledIn == 5) {
                        AnswerKey.add("E");
                        questionsRight++;
                    }

                    filledIn = 0;
                    nextroww = true;


                }
            }

        }
        img.setPixels(grid);
        return AnswerKey;

    }


    private short[][] crop(short[][] grid, int startRow, int startCol, int height, int width) {
        short[][] cropped = new short[height][width];

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                cropped[r][c] = grid[startRow + r][startCol + c];
            }
        }

        return cropped;
    }


}


