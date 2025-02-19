import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JPanel panel1;
    private JPanel basePanel;
    private JPanel logPanel;
    private JPanel historyPanel;
    private JPanel settingsPanel;
    private JButton settingsButton;
    private JButton moodHistoryButton;
    private JButton exitButton;
    private JButton logMoodButton;
    private JLabel label1;
    private JComboBox comboBox1;
    private JTextArea textArea1;
    private JButton saveLogButton;
    private JButton exitButton1;
    private JButton clearLogButton;
    private JButton refreshTimeButton;
    private JTabbedPane Options;
    private JPanel CalendarView;
    private JPanel ListView;
    private JButton backButton;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button1;
    private JButton button5;
    private JButton button6;
    private JButton backButton1;

    public Main(){


        //For Log Panel
        logMoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                basePanel.setVisible(false);
                logPanel.setVisible(true);
            }
        });

        exitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                basePanel.setVisible(true);
                logPanel.setVisible(false);
            }
        });

        moodHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                basePanel.setVisible(false);
                historyPanel.setVisible(true);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                basePanel.setVisible(true);
                historyPanel.setVisible(false);
            }
        });
        backButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                basePanel.setVisible(true);
                historyPanel.setVisible(false);
            }
        });
    }


    public static void main(String[] args){
        JFrame frame = new JFrame("Mood Tracker");
        frame.setContentPane(new Main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 600);
        frame.setLocation(400, 200);
        frame.setVisible(true);

    }
}
