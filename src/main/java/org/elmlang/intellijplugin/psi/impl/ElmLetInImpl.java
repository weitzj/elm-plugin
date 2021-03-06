package org.elmlang.intellijplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.elmlang.intellijplugin.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;


public class ElmLetInImpl extends ElmPsiElement implements ElmLetIn {
    public ElmLetInImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof ElmVisitor) {
            ((ElmVisitor)visitor).visitPsiElement(this);
        }
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<ElmInnerValueDeclaration> getInnerValuesList() {
        return PsiTreeUtil.getChildrenOfTypeAsList(this, ElmInnerValueDeclaration.class);
    }

    @NotNull
    @Override
    public Stream<ElmValueDeclarationBase> getValueDeclarations() {
        return ElmPsiImplUtil.getValueDeclarations(this);
    }
}
