package github.zgqq.intellij.enhance;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class DeleteAndCopyMethodAction extends AnAction {
    private static final Logger logger = Logger.getInstance(JumpAndChangeWordAction.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final PsiElement currentMethodPsiElement = MethodUtils.getCurrentMethodPsiElement(e);
        ClipboardUtils.setContents(currentMethodPsiElement.getText());
        EditorUtils.deleteText(e, currentMethodPsiElement);

    }
}
