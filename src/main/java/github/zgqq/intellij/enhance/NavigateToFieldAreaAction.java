package github.zgqq.intellij.enhance;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class NavigateToFieldAreaAction extends AnAction {
    private static final Logger logger = Logger.getInstance(JumpAndChangeWordAction.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = CommonUtils.getEditorFrom(e);
        PsiJavaFile data = (PsiJavaFile) e.getData(LangDataKeys.PSI_FILE);
        PsiElement pe = data.findElementAt(editor.getCaretModel().getOffset());

        final PsiClass psiClass = PsiTreeUtil.getParentOfType(pe, PsiClass.class);

        final PsiField[] fields = psiClass.getFields();
        if (fields != null && fields.length > 0) {
            final PsiMethod[] constructors = psiClass.getConstructors();
            PsiField psiField;
            if (constructors != null && constructors.length > 0) {
                psiField = PsiUtils.getMostNearBeforeElement(
                        fields,
                        constructors[0].getTextOffset()
                );
            } else {
                psiField = fields[fields.length - 1];
            }

            PsiUtils.navigate(psiField);
        } else {
            PsiUtils.navigate(psiClass);
        }
    }
}
