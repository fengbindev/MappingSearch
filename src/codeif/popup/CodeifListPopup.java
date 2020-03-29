package codeif.popup;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CodeifListPopup extends BaseListPopupStep<String> {
    private AnActionEvent e;


    public CodeifListPopup(@Nullable String title, List<String> values, AnActionEvent e) {
        super(title, values);
        this.e = e;
    }

    @NotNull
    @Override
    public String getTextFor(String value) {
        return super.getTextFor(value);
    }

    @Nullable
    @Override
    public PopupStep onChosen(String selectedValue, boolean finalChoice) {
        final Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        final Editor mEditor = e.getData(PlatformDataKeys.EDITOR);
        assert mEditor != null;
        SelectionModel model = mEditor.getSelectionModel();
        final Document document = mEditor.getDocument();
        final int start = model.getSelectionStart();
        final int end = model.getSelectionEnd();
        Runnable runnable = () -> document.replaceString(start, end, selectedValue);// 替换选中的字符
        WriteCommandAction.runWriteCommandAction(project, runnable);
        return null;
    }
}
