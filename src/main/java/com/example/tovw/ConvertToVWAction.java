package com.example.tovw;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ConvertToVWAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        Editor editor = event.getData(CommonDataKeys.EDITOR);

        if (editor == null || project == null) {
            return;
        }

        // 从设置中获取设计稿宽度
        double designWidth = ConvertToVWSettings.getInstance(project).getDesignWidth();

        SelectionModel selectionModel = editor.getSelectionModel();
        Document document = editor.getDocument();

        if (!selectionModel.hasSelection()) {
            return;
        }

        String selectedText = selectionModel.getSelectedText();
        if (selectedText == null) {
            return;
        }

        // 移除可能的单位（rem, px等），只保留数字
        String numberStr = selectedText.replaceAll("[^0-9.]", "");

        try {
            double value = Double.parseDouble(numberStr);

            // 转换为 vw：value / designWidth * 100
            double vwValue = (value / designWidth) * 100;

            // 格式化：保留2位小数，去掉末尾的0
            String vwStr = formatVW(vwValue);

            // 替换选中的文本
            int startOffset = selectionModel.getSelectionStart();
            int endOffset = selectionModel.getSelectionEnd();

            WriteCommandAction.runWriteCommandAction(project, () -> {
                document.replaceString(startOffset, endOffset, vwStr);
            });

        } catch (NumberFormatException e) {
            // 如果解析失败，不做处理
        }
    }

    /**
     * 格式化 vw 值
     */
    private String formatVW(double value) {
        String formatted = String.format("%.2f", value);

        // 去掉末尾的0和小数点
        if (formatted.endsWith(".00")) {
            formatted = formatted.substring(0, formatted.length() - 3);
        } else {
            while (formatted.endsWith("0")) {
                formatted = formatted.substring(0, formatted.length() - 1);
            }
            if (formatted.endsWith(".")) {
                formatted = formatted.substring(0, formatted.length() - 1);
            }
        }

        return formatted + "vw";
    }
}
