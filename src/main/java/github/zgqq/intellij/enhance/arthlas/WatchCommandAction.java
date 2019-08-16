package github.zgqq.intellij.enhance.arthlas;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import github.zgqq.intellij.enhance.CommonUtils;
import github.zgqq.intellij.enhance.CopyPasteUtils;
import org.jetbrains.annotations.NotNull;

public class WatchCommandAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        Editor editor = CommonUtils.getEditorFrom(e);
        PsiJavaFile data = (PsiJavaFile) e.getData(LangDataKeys.PSI_FILE);

        PsiElement element = data.findElementAt(editor.getCaretModel().getOffset());
        PsiElement parentOfType = element;
        PsiElement mostOuterMethod;
        do {
            mostOuterMethod = parentOfType;
            parentOfType = PsiTreeUtil.getParentOfType(parentOfType, PsiMethod.class);
        } while (parentOfType != null);

        if (mostOuterMethod != parentOfType) {
            final PsiMethod mostOuterMethod1 = (PsiMethod) mostOuterMethod;
            final PsiClass containingClass = mostOuterMethod1.getContainingClass();
            final String qualifiedName = containingClass.getQualifiedName();
            StringBuilder sb = new StringBuilder();
            sb.append("watch ");
            sb.append(qualifiedName + " ");
            sb.append(mostOuterMethod1.getName() + " ");
            sb.append("\"{params,returnObj,target}\" -x 1");
            CopyPasteUtils.copy(sb.toString());
        }
    }
}
