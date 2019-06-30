package github.zgqq.intellij.enhance;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

public class GotoOutermostMethodNameAction extends AnAction {
    private static final Logger logger = Logger.getInstance(JumpAndChangeWordAction.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = CommonUtils.getEditorFrom(e);
        PsiJavaFile data = (PsiJavaFile) e.getData(LangDataKeys.PSI_FILE);
        PsiElement pe = data.findElementAt(editor.getCaretModel().getOffset());
        PsiMethod psiMethod = PsiTreeUtil.getParentOfType(pe, PsiMethod.class);
        PsiMethod mayOutermostMethod = psiMethod;
        while (psiMethod != null) {
            mayOutermostMethod = psiMethod;
            psiMethod = PsiTreeUtil.getParentOfType(psiMethod, PsiMethod.class);
        }
        psiMethod = mayOutermostMethod;

        if (psiMethod != null) {
            PsiUtils.navigate(psiMethod.getNameIdentifier().getNavigationElement());
        }
    }
}
