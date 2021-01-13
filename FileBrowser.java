import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class FileBrowser extends JPanel implements ActionListener {

    private JButton open = new JButton("Open");
    private ButtonGroup buttonGroup;
    private File directory;

    private CardLayout cardLayout;


    public FileBrowser(String dir) {
        setLayout(new CardLayout());

        JLabel fileListLabel = new JLabel("File List: ");

        open.addActionListener(this);;
        directory = new File(dir);
        directory.mkdir();

        JPanel fileListPanel = new JPanel(new GridLayout((directory.listFiles().length + 2), 1));
        fileListPanel.add(fileListLabel);

        buttonGroup = new ButtonGroup();

        // Adds a button for each of the files in the directory
        for (File file : directory.listFiles()) {
            JRadioButton radioButton = new JRadioButton(file.getName());
            radioButton.setActionCommand(file.getName());
            buttonGroup.add(radioButton);
            fileListPanel.add(radioButton);
        }

        fileListPanel.add(open);
        JPanel mainPanel = new JPanel();
        mainPanel.add(fileListPanel);
        add(mainPanel);

        cardLayout = (CardLayout) getLayout();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == open) {
            File file = new File(directory.getName() + "\\" + buttonGroup.getSelection().getActionCommand());
            add(new Editor(file),"editor");
            cardLayout.show(this, "editor");

        }
    }
}