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
    private JTextArea logTxt;
    private JButton saveLogButton;
    private JButton exitButton1;
    private JButton clearLogButton;
    private JButton refreshTimeButton;
    private JTabbedPane Options;
    private JPanel CalendarView;
    private JPanel ListView;
    private JButton backButton;
    private JButton exportHistoryButton;
    private JButton clearHistoryButton;
    private JButton logMoodButton1;
    private JButton logMoodButton2;
    private JButton clearHistoryButton1;
    private JButton exportHistoryButton1;
    private JButton backButton1;
    private JRadioButton lightRadioButton;
    private JRadioButton darkRadioButton;
    private JTextField enterFileHereTextField;
    private JButton saveButton;
    private JButton resetButton;
    private JButton backButton2;

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

        logMoodButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyPanel.setVisible(false);
                logPanel.setVisible(true);
            }
        });

        logMoodButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyPanel.setVisible(false);
                logPanel.setVisible(true);
            }
        });

        backButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsPanel.setVisible(false);
                basePanel.setVisible(true);
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsPanel.setVisible(true);
                basePanel.setVisible(false);
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
