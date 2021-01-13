import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CreateFile extends JPanel implements ActionListener {

    private JButton createFile = new JButton("Create File");

    private JTextField newFileText = new JTextField();

    private CardLayout cardLayout;

    public CreateFile() {
        setLayout(new CardLayout());

        JLabel newFileLabel = new JLabel("Enter New File Name (w/ \".txt\": ");

        JPanel newFilePanel = new JPanel(new GridLayout(1,2));
        JPanel createFilePanel = new JPanel(new GridLayout(2,1));
        JPanel mainPanel = new JPanel();

        newFilePanel.add(newFileLabel);
        newFilePanel.add(newFileText);

        createFilePanel.add(newFilePanel);

        createFile.addActionListener(this);

        createFilePanel.add(createFile);
        mainPanel.add(createFilePanel);
        add(mainPanel, "createFile");

        cardLayout = (CardLayout) getLayout();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == createFile) {
            File file = new File("C:\\Users\\jrhaf\\IdeaProjects\\TextEditor\\files\\" + newFileText.getText());
            add(new Editor(file, 1), "editor");
            cardLayout.show(this, "editor");
        }
    }
}
