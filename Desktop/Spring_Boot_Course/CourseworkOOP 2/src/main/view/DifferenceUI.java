package main.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DifferenceUI extends JPanel {

    private final JTextField otherImagePath = new JTextField(60);
    private final JButton fileChooserButton = new JButton("Open");

    private final JFileChooser chooser;
    private File file;

    public DifferenceUI(final JFileChooser chooser) {
        super(new BorderLayout());

        this.chooser = chooser;

        final JPanel pathPanel = new JPanel();
        pathPanel.add(this.otherImagePath);
        pathPanel.add(this.fileChooserButton);
        pathPanel.setBorder(BorderFactory.createTitledBorder("Image to difference"));

        add(pathPanel, BorderLayout.NORTH);

        this.otherImagePath.setEditable(false);

        this.fileChooserButton.addActionListener(ev -> showChooser());
    }

    private void showChooser() {
        if (this.chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.file = this.chooser.getSelectedFile();
            this.otherImagePath.setText(this.file.getPath());
        }
    }

    public File getOtherImagePath() {
        return this.file;
    }
}
