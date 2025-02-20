import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

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
    private JTextField fileTxt;
    private JButton saveButton;
    private JButton resetButton;
    private JButton backButton2;

    String logFile;

    void setTheme(int x) {
        if (x == 0) {
            // 🌞 Light Mode (Restore Original Colors)
            basePanel.setBackground(new Color(-13303888)); // Original greenish background
            logPanel.setBackground(new Color(-13303888));
            historyPanel.setBackground(new Color(-13303888));
            settingsPanel.setBackground(new Color(-13303888));
            CalendarView.setBackground(new Color(-13303888));
            ListView.setBackground(new Color(-13303888));

            label1.setForeground(new Color(-15978496)); // Original purple text
            logTxt.setBackground(new Color(-12415003)); // Original blue-ish gray
            logTxt.setForeground(new Color(-16777216)); // Black text

            fileTxt.setBackground(new Color(-12415003));
            fileTxt.setForeground(Color.BLACK);

            // Restore JRadioButtons to original colors
            lightRadioButton.setBackground(new Color(-13303888));
            lightRadioButton.setForeground(Color.BLACK);
            darkRadioButton.setBackground(new Color(-13303888));
            darkRadioButton.setForeground(Color.BLACK);

            // Restore button colors (original mix of blue, green, and gray)
            updateButtonTheme(new Color(-10799269), new Color(-394241));
        }

        if (x == 1) {
            // 🌙 Dark Mode
            Color backgroundColor = new Color(30, 30, 30);    // Deep dark gray
            Color panelColor = new Color(40, 40, 40);         // Slightly lighter gray for panels
            Color textColor = new Color(220, 220, 220);       // Light gray text for contrast
            Color buttonColor = new Color(60, 60, 60);        // Dark gray buttons
            Color buttonTextColor = new Color(255, 255, 255); // White text
            Color inputFieldColor = new Color(50, 50, 50);    // Dark gray text fields
            Color radioButtonBg = new Color(40, 40, 40);      // Dark gray background for radio buttons
            Color radioButtonFg = new Color(200, 200, 200);   // Light gray text for radio buttons

            basePanel.setBackground(backgroundColor);
            logPanel.setBackground(backgroundColor);
            historyPanel.setBackground(backgroundColor);
            settingsPanel.setBackground(backgroundColor);
            CalendarView.setBackground(panelColor);
            ListView.setBackground(panelColor);

            label1.setForeground(textColor);
            logTxt.setBackground(inputFieldColor);
            logTxt.setForeground(textColor);

            fileTxt.setBackground(inputFieldColor);
            fileTxt.setForeground(textColor);

            // Update JRadioButtons for dark mode
            lightRadioButton.setBackground(radioButtonBg);
            lightRadioButton.setForeground(radioButtonFg);
            darkRadioButton.setBackground(radioButtonBg);
            darkRadioButton.setForeground(radioButtonFg);

            // Update button colors
            updateButtonTheme(buttonColor, buttonTextColor);
        }
    }
    void updateButtonTheme(Color bgColor, Color fgColor) {
        JButton[] buttons = {
                settingsButton, moodHistoryButton, exitButton, logMoodButton,
                saveLogButton, exitButton1, clearLogButton, refreshTimeButton,
                backButton, exportHistoryButton, clearHistoryButton,
                logMoodButton1, logMoodButton2, clearHistoryButton1,
                exportHistoryButton1, backButton1, saveButton, resetButton, backButton2
        };

        for (JButton btn : buttons) {
            btn.setBackground(bgColor);
            btn.setForeground(fgColor);
            btn.setFocusPainted(false); // Removes blue focus outline
            btn.setBorderPainted(false); // Removes border for a modern look
        }
    }





    public Main(){

        lightRadioButton.setSelected(true);

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

        lightRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                darkRadioButton.setSelected(false);

            }
        });

        darkRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                lightRadioButton.setSelected(false);


            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lightRadioButton.isSelected()) setTheme(0); //Set to Light Mode
                if (darkRadioButton.isSelected()) setTheme(1); //Set to Dark Mode
                logFile = fileTxt.getText();
                JOptionPane.showMessageDialog(null, "Settings Saved!");

            }
        });


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lightRadioButton.setSelected(true);
                darkRadioButton.setSelected(false);
                fileTxt.setText("Enter File Name");
                logFile = "";
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
