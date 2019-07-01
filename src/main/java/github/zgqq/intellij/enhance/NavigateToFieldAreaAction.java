package github.zgqq.intellij.enhance;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

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
            PsiUtils.navigate(fields[fields.length - 1]);
        } else {
            PsiUtils.navigate(psiClass);
        }
    }
}
