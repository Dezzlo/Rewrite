import java.io.*;
import java.util.ArrayList;

public class CopyText {
    public static void main(String[] args) throws InterruptedException {

        ArrayList<String> arrayList = new ArrayList<String>();
        final long before = System.currentTimeMillis();

        Thread copy_Text = new Thread() {
            @Override
            public void run() {
                try {
                    BufferedReader bufferedReader = new BufferedReader
                            (new FileReader("C:\\Programs Gorshka\\Programming\\IntelliJ IDEA\\" +
                                            "Java coding\\Rewrite\\src\\Text_File.txt"));

                    String string;
                    while ((string = bufferedReader.readLine()) != null) {
                        arrayList.add(string);
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        };
        copy_Text.start();
        Thread rewrite = new Thread() {

            @Override
            public void run() {
                try{
                    FileWriter writer = new FileWriter("File_Without_Text.txt");

                    for(String line : arrayList)
                    {
                        writer.write(line);
                        writer.write(System.getProperty("line.separator"));

                    }

                    writer.close();
                }catch (IOException ex){ex.printStackTrace();}
            }
        };
        rewrite.start();
        copy_Text.join();
        rewrite.join();
        final long after = System.currentTimeMillis();
        System.out.printf("Колличество мс %d", (after - before) );
    }
}
