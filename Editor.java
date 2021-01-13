import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Editor extends JPanel implements ActionListener {

    private File theFile;

    private JButton save = new JButton("Save");
    private JButton delete = new JButton("Delete");
    private JTextArea mainTextArea = new JTextArea(20, 40);

    // Constructor for NewFile
    public Editor(File theFile, int thisIsANewFile) {
        this.theFile = theFile;

        save.addActionListener(this);
        delete.addActionListener(this);

        add(save);
        add(delete);
        add(mainTextArea);
    }

    // Constructor for OpenFile
    public Editor(File theFile) {
        this.theFile = theFile;

        save.addActionListener(this);
        delete.addActionListener(this);

        copyTextFromFile(theFile);

        add(save);
        add(delete);
        add(mainTextArea);
    }

    private void copyTextFromFile(File theFile) {
        try {
            BufferedReader inputReader = new BufferedReader(new FileReader(theFile));

            String line = inputReader.readLine();
            while (line != null) {
                mainTextArea.append(line + "\n");
                line = inputReader.readLine();
            }

            inputReader.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found!!! " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("IO Exception!!! " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == save) {
            try {
                FileWriter output = new FileWriter(theFile);

                output.write(mainTextArea.getText());
                output.close();
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found!!! " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO Exception!!! " + e.getMessage());
                e.printStackTrace();
            }
        }
        else if (actionEvent.getSource() == delete) {
            theFile.delete();
        }


    }
}
