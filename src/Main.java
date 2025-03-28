import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Main extends JFrame {
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
    private JLabel timeLabel;
    private JTextArea listViewTextArea;
    private final DefaultTableModel calendarTableModel;
    private JTable calendarTable;
    private JScrollPane scrollPane;
    private JLabel logFileLabel;
    private JScrollPane scrollPane2;
    private JPanel examPanel;
    private JButton openWindow;
    private JButton Window2;
    private JButton Window3;
    private JButton Window4;
    private JButton logoutButton;
    private JLabel examLabel;
    private Map<String, String> moodLogs = new HashMap<>();
    private String logFile = "mood_log.txt"; // Default log file name

    private JLabel getMoodIcon(String mood, String date) {
        String imagePath = "";
        switch (mood.toLowerCase()) {
            case "happy": imagePath = "happy.png"; break;
            case "sad": imagePath = "sad.png"; break;
            case "angry": imagePath = "angry.png"; break;
            case "anxious": imagePath = "anxious.png"; break;
            case "neutral": imagePath = "neutral.png"; break;
            case "nervous": imagePath = "nervous.png"; break;
            default: imagePath = "neutral.png";
        }
        JLabel label = new JLabel(new ImageIcon(imagePath));
        label.setToolTipText(date);
        return label;
    } //Function to convert mood selection to corresponding image
    private void updateDisplayedTime() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        timeLabel.setText("Current Time: " + timeStamp);
    } //function to update the date and time
    void setTheme(int x) {
        if (x == 0) {
            //Light Mode
            basePanel.setBackground(new Color(-13303888)); //green background
            logPanel.setBackground(new Color(-13303888));
            historyPanel.setBackground(new Color(-13303888));
            settingsPanel.setBackground(new Color(-13303888));
            CalendarView.setBackground(new Color(-13303888));
            ListView.setBackground(new Color(-13303888));
            calendarTable.setBackground(new Color(-13303888));
            listViewTextArea.setBackground(new Color(-13303888));
            scrollPane.setForeground(new Color(-13303888));
            label1.setForeground(new Color(-15978496)); // purple
            logTxt.setBackground(new Color(-12415003)); // blue-ish gray
            logTxt.setForeground(new Color(-16777216)); // Black
            fileTxt.setBackground(new Color(-12415003));
            fileTxt.setForeground(Color.BLACK);

            // Restore JRadioButtons to original colors
            lightRadioButton.setBackground(new Color(-13303888));
            lightRadioButton.setForeground(Color.BLACK);
            darkRadioButton.setBackground(new Color(-13303888));
            darkRadioButton.setForeground(Color.BLACK);
            updateButtonTheme(new Color(-10799269), new Color(-394241));
        }

        if (x == 1) {
            // Dark Mode
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
            calendarTable.setBackground(panelColor);
            listViewTextArea.setBackground(panelColor);
            calendarTable.setForeground(textColor);
            listViewTextArea.setForeground(textColor);
            scrollPane.setForeground(panelColor);
            label1.setForeground(textColor);
            logTxt.setBackground(inputFieldColor);
            logTxt.setForeground(textColor);
            fileTxt.setBackground(inputFieldColor);
            fileTxt.setForeground(textColor);
            lightRadioButton.setBackground(radioButtonBg);
            lightRadioButton.setForeground(radioButtonFg);
            darkRadioButton.setBackground(radioButtonBg);
            darkRadioButton.setForeground(radioButtonFg);
            updateButtonTheme(buttonColor, buttonTextColor);
        }
    } //function to change the colors of program
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
    } //also changes the colors of the program
    private void updateCalendar(String date, String mood) {
        try {
            Date logDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(logDate);

            int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
            calendar.set(java.util.Calendar.DAY_OF_MONTH, 1);
            int startDay = calendar.get(java.util.Calendar.DAY_OF_WEEK) - 1;

            int row = (day + startDay - 1) / 7;
            int col = (day + startDay - 1) % 7;

            calendarTableModel.setValueAt(getMoodIcon(mood, date), row, col);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //function to update calendar with new logs
    private void loadMoodHistory() {
        File file = new File(logFile);
        if (!file.exists()) return;

        // Clear in-memory history before loading new data
        moodLogs.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip blank lines
                if (line.trim().isEmpty()) continue;

                // Process the line containing the timestamp and mood
                if (line.contains(" - Mood: ")) {
                    String logLine = line;
                    // Read the next non-empty line which should contain the notes
                    String notesLine = reader.readLine();
                    while (notesLine != null && notesLine.trim().isEmpty()) {
                        notesLine = reader.readLine();
                    }

                    String[] parts = logLine.split(" - Mood: ");
                    if (parts.length == 2) {
                        String fullTimestamp = parts[0];
                        // Extract only the date portion (yyyy-MM-dd)
                        String date = fullTimestamp.split(" ")[0];
                        String mood = parts[1].replace("Mood: ", "").trim();
                        String notes = "None";
                        if (notesLine != null && notesLine.contains("Notes: ")) {
                            notes = notesLine.split("Notes: ")[1].trim();
                        }
                        String logEntry = "Mood: " + mood + "\nNotes: " + notes;

                        // Merge logs if multiple entries exist for the same date
                        moodLogs.merge(date, logEntry, (existing, newEntry) -> existing + "\n---\n" + newEntry);
                        // Update the calendar with the latest mood for that date
                        updateCalendar(date, mood);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error loading history: " + ex.getMessage());
        }
    } // function to load history when program starts
    private void updateListView() {
        listViewTextArea.setText(""); // Clear previous text
        StringBuilder historyText = new StringBuilder();

        for (Map.Entry<String, String> entry : moodLogs.entrySet()) {
            historyText.append("Date: ").append(entry.getKey()).append("\n")
                    .append(entry.getValue()).append("\n\n");
        }

        if (historyText.length() == 0) {
            historyText.append("No mood history available.");
        }

        listViewTextArea.setText(historyText.toString());
    } //function to update the list view of mood history

    public Main() {

        lightRadioButton.setSelected(true);
        listViewTextArea.setVisible(true);
        listViewTextArea.setEditable(false);

        //Set up Calendar Table as a 7-day format
        String[] columnNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        calendarTableModel = new DefaultTableModel(columnNames, 6);
        calendarTable.setModel(calendarTableModel);
        calendarTable.setRowHeight(60);
        calendarTable.setCellSelectionEnabled(true);
        calendarTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                if (value instanceof JLabel) {
                    return (JLabel) value;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });

    //Initialize Time and History
        updateDisplayedTime();
        loadMoodHistory();
        updateListView();


        //Action listeners for all buttons
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
                updateListView();
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
                fileTxt.setText(logFile);
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
                fileTxt.setText("mood_log.txt");
                logFile = "";
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        saveLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileWriter writer = new FileWriter(logFile, true)) {
                    String mood = (String) comboBox1.getSelectedItem();
                    String notes = logTxt.getText().trim();
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                    if (mood == null || mood.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please select a mood.");
                        return;
                    }

                    String logEntry = "Mood: " + mood + "\nNotes: " + (notes.isEmpty() ? "None" : notes);
                    writer.write(timeStamp + " - " + logEntry + "\n\n");

                    moodLogs.merge(timeStamp.split(" ")[0], logEntry, (existing, newEntry) -> existing + "\n---\n" + newEntry);
                    updateCalendar(timeStamp.split(" ")[0], mood); // Update calendar using only the date

                    JOptionPane.showMessageDialog(null, "Mood logged successfully!");
                    logTxt.setText(""); // Clear after saving
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving log: " + ex.getMessage());
                }
            }
        });

        logTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(logTxt.getText().equals("Enter Text Here")) {
                    logTxt.setText("");
                }
            }
        });

        clearLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logTxt.setText("");
                comboBox1.setSelectedIndex(0);
            }
        });

        refreshTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplayedTime();
            }
        });

        exportHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(logFile);
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(null, "No history found to export.");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Mood history exported to: " + file.getAbsolutePath());
            }
        });

        clearHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(logFile);
                if (file.exists() && file.delete()) {
                    // Clear in-memory logs:
                    moodLogs.clear();
                    // Clear each cell in the calendar table:
                    for (int row = 0; row < calendarTableModel.getRowCount(); row++) {
                        for (int col = 0; col < calendarTableModel.getColumnCount(); col++) {
                            calendarTableModel.setValueAt(null, row, col);
                        }
                    }
                    listViewTextArea.setText("");
                    JOptionPane.showMessageDialog(null, "Mood history cleared.");
                } else {
                    JOptionPane.showMessageDialog(null, "No history to clear.");
                }
            }
        });

        clearHistoryButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(logFile);
                if (file.exists() && file.delete()) {
                    // Clear in-memory logs:
                    moodLogs.clear();
                    // Clear each cell in the calendar table:
                    for (int row = 0; row < calendarTableModel.getRowCount(); row++) {
                        for (int col = 0; col < calendarTableModel.getColumnCount(); col++) {
                            calendarTableModel.setValueAt(null, row, col);
                        }
                    }
                    listViewTextArea.setText("");
                    JOptionPane.showMessageDialog(null, "Mood history cleared.");
                } else {
                    JOptionPane.showMessageDialog(null, "No history to clear.");
                }
            }
        });

        calendarTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = calendarTable.getSelectedRow();
                int col = calendarTable.getSelectedColumn();
                Object value = calendarTableModel.getValueAt(row, col);

                //Show notes when emoji is clicked
                if (value instanceof JLabel) {
                    JLabel label = (JLabel) value;
                    String date = label.getToolTipText();
                    if (moodLogs.containsKey(date)) {
                        JOptionPane.showMessageDialog(null, "Date: " + date + "\n" + moodLogs.get(date),
                                "Mood Details", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

//EXAM
        openWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyPanel.setVisible(false);
                logPanel.setVisible(false);
                settingsPanel.setVisible(false);
                basePanel.setVisible(false);

                examPanel.setVisible(true);

            }
        });

        Window2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Window2().setVisible(true);
            }
        });

        Window3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Window3().setVisible(true); // Clean, simple call to Window3
            }
        });

        Window4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Window4().setVisible(true);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main (String[]args){

        JFrame frame = new JFrame("Mood Tracker");
        frame.setContentPane(new Main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 600);
        frame.setLocation(400, 200);
        frame.setVisible(true);



    }
}


//Window 1
class Window1 extends JFrame {
    public Window1() {
        setTitle("Window 1");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("This is Window 1", SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }
}

//Window 2
class Window2 extends JFrame {
    public Window2() {
        setTitle("New Window");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only closes this window
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel label = new JLabel("New Window added", SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Go Back Button
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(goBackButton);

        add(panel);
        setVisible(true);
    }
}

//Window 3
class Window3 extends JFrame {
    public Window3() {
        setTitle("Select Window System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Select window to display");
        JComboBox<String> comboBoxWindows = new JComboBox<>(new String[]{
                "Window 1", "Window 2", "Window 3", "Window 4"
        });
        JButton openWindowButton = new JButton("Select window");

        add(label);
        add(comboBoxWindows);
        add(openWindowButton);

        openWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedWindow = (String) comboBoxWindows.getSelectedItem();
                if (selectedWindow.equals("Window 1")) {
                    new Window1().setVisible(true);
                } else if (selectedWindow.equals("Window 2")) {
                    new Window2().setVisible(true);
                } else if (selectedWindow.equals("Window 3")) {
                    new Window3().setVisible(true);
                } else if (selectedWindow.equals("Window 4")) {
                    new Window4().setVisible(true);
                }
            }
        });


        setLocationRelativeTo(null);
        setVisible(true);
    }
}

//Window 4
class Window4 extends JFrame {
    private JPanel formPanel, tablePanel;
    private JTextField designerNameField, dateField, emailField, contactField, versionField;
    private DefaultTableModel tableModel;
    private CardLayout cardLayout;

    public Window4() {
        setTitle("Profile History");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        formPanel = new JPanel(new BorderLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("THE PROJECT DETAILS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        formPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        inputPanel.add(new JLabel("DESIGNER NAMES:"));
        designerNameField = new JTextField();
        inputPanel.add(designerNameField);

        inputPanel.add(new JLabel("DATE (YEAR, MONTH, DAY):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("CONTACT EMAIL ADDRESS:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("CONTACT NUMBER:"));
        contactField = new JTextField();
        inputPanel.add(contactField);

        inputPanel.add(new JLabel("VERSION NUMBER:"));
        versionField = new JTextField();
        inputPanel.add(versionField);

        formPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton addButton = new JButton("Add to the Profile");
        JButton formBackButton = new JButton("Back");
        buttonPanel.add(addButton);
        buttonPanel.add(formBackButton);

        formPanel.add(buttonPanel, BorderLayout.SOUTH);

        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Profile Table"));

        String[] columnNames = {"Names", "Date", "Email", "Contact no.", "Version"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton tableBackButton = new JButton("Back");
        JPanel tableButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tableButtonPanel.add(tableBackButton);

        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(tableButtonPanel, BorderLayout.SOUTH);

        mainPanel.add(formPanel, "FormPanel");
        mainPanel.add(tablePanel, "TablePanel");

        add(mainPanel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = designerNameField.getText();
                String date = dateField.getText();
                String email = emailField.getText();
                String contact = contactField.getText();
                String version = versionField.getText();

                tableModel.addRow(new Object[]{name, date, email, contact, version});

                designerNameField.setText("");
                dateField.setText("");
                emailField.setText("");
                contactField.setText("");
                versionField.setText("");

                JOptionPane.showMessageDialog(Window4.this, "Profile added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                cardLayout.show(mainPanel, "TablePanel");
            }
        });

        formBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        tableBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "FormPanel");
            }
        });

        setVisible(true);
    }
}
