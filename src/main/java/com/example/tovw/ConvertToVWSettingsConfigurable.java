package com.example.tovw;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class ConvertToVWSettingsConfigurable implements SearchableConfigurable {

    private final Project project;
    private JPanel mainPanel;
    private JTextField designWidthField;

    public ConvertToVWSettingsConfigurable(Project project) {
        this.project = project;
    }

    @NotNull
    @Override
    public String getId() {
        return "toVW.Settings";
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "toVW";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("\u8bbe\u8ba1\u7a3f\u5bbd\u5ea6 (px):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        designWidthField = new JTextField(10);
        mainPanel.add(designWidthField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        JLabel hintLabel = new JLabel("<html><font color='gray' size='2'>\u8bbe\u8ba1\u7a3f\u5bbd\u5ea6\u5bf9\u5e94 100vw\uff0c\u4f8b\u5982 375 \u6216 390</font></html>");
        mainPanel.add(hintLabel, gbc);

        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return !designWidthField.getText().equals(
                String.valueOf(ConvertToVWSettings.getInstance(project).getDesignWidth())
        );
    }

    @Override
    public void apply() throws ConfigurationException {
        try {
            double width = Double.parseDouble(designWidthField.getText());
            if (width <= 0) {
                throw new ConfigurationException("\u8bbe\u8ba1\u7a3f\u5bbd\u5ea6\u5fc5\u987b\u5927\u4e8e 0");
            }
            ConvertToVWSettings.getInstance(project).setDesignWidth(width);
        } catch (NumberFormatException e) {
            throw new ConfigurationException("\u8bf7\u8f93\u5165\u6709\u6548\u7684\u6570\u5b57");
        }
    }

    @Override
    public void reset() {
        designWidthField.setText(
                String.valueOf(ConvertToVWSettings.getInstance(project).getDesignWidth())
        );
    }
}
