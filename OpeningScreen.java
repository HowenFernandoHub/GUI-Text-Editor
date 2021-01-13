import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpeningScreen extends JPanel implements ActionListener {


    private JButton open = new JButton("Open File");
    private JButton newFile = new JButton("New File");

    private CardLayout cardLayout;

    public OpeningScreen() {
        setLayout(new CardLayout());

        JLabel welcomeLabel = new JLabel("    Welcome to my Text Editor ");
        JPanel griddedPanel1 = new JPanel(new GridLayout(1,2));
        JPanel griddedPanel2 = new JPanel(new GridLayout(2,1));
        JPanel mainPanel = new JPanel();

        open.addActionListener(this);
        newFile.addActionListener(this);

        griddedPanel1.add(open);
        griddedPanel1.add(newFile);

        griddedPanel2.add(welcomeLabel);
        griddedPanel2.add(griddedPanel1);
        mainPanel.add(griddedPanel2);
        add(mainPanel, "opening");

        cardLayout = (CardLayout) getLayout();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == open) {
            add(new FileBrowser("C:\\Users\\jrhaf\\IdeaProjects\\TextEditor\\files"), "fileBrowser");
            cardLayout.show(this, "fileBrowser");
        }
        else if (actionEvent.getSource() == newFile) {
            add(new CreateFile(), "createFile");
            cardLayout.show(this, "createFile");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Text Editor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setResizable(true);

        OpeningScreen openingScreen = new OpeningScreen();

        frame.add(openingScreen);
        frame.setVisible(true);
    }
}
