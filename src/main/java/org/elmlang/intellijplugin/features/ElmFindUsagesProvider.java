package org.elmlang.intellijplugin.features;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.elmlang.intellijplugin.ElmLexerAdapter;
import org.elmlang.intellijplugin.ElmParserDefinition;
import org.elmlang.intellijplugin.psi.ElmLowerCaseId;
import org.elmlang.intellijplugin.psi.ElmNamedElement;
import org.elmlang.intellijplugin.psi.ElmTypes;
import org.elmlang.intellijplugin.psi.ElmUpperCaseId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@link ElmFindUsagesProvider} allows using the `Find Usages` command.
 * This is only supported for lowerCaseIdentifier and upperCaseIdentifier.
 */
public class ElmFindUsagesProvider
        implements FindUsagesProvider {

    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new ElmLexerAdapter(),
                TokenSet.create(ElmTypes.LOWER_CASE_IDENTIFIER, ElmTypes.UPPER_CASE_IDENTIFIER),
                ElmParserDefinition.COMMENTS, TokenSet.EMPTY);
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof ElmNamedElement;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        // TODO: show more detailed messages using getContext()
        // to allow printing, e.g. "Function|Type|Alias".
        if (element instanceof ElmLowerCaseId) {
            return "element";
        }
        if (element instanceof ElmUpperCaseId) {
            return "element";
        }
        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        if (element instanceof ElmLowerCaseId) {
            return ((ElmLowerCaseId) element).getName();
        }
        if (element instanceof ElmUpperCaseId) {
            return ((ElmUpperCaseId) element).getName();
        }
        return "";
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        if (element instanceof ElmLowerCaseId) {
            return ((ElmLowerCaseId) element).getName();
        }
        if (element instanceof ElmUpperCaseId) {
            return ((ElmUpperCaseId) element).getName();
        }
        return "";
    }
}
