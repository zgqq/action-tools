package github.zgqq.intellij.enhance;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class GotoMethodEndAction extends AnAction {
    private static final Logger logger = Logger.getInstance(JumpAndChangeWordAction.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final PsiElement currentMethodPsiElement = MethodUtils.getCurrentMethodPsiElement(e);
        final int endOffset = currentMethodPsiElement.getTextRange().getEndOffset();
        Editor editor = CommonUtils.getEditorFrom(e);
        editor.getCaretModel().moveToOffset(endOffset);
        editor.getScrollingModel().scrollToCaret(ScrollType.CENTER);
    }
}
