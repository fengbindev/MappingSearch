package mapping.service;


import com.intellij.ide.util.gotoByName.ChooseByNameBase;
import com.intellij.ide.util.gotoByName.DefaultChooseByNameItemProvider;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.psi.PsiElement;
import com.intellij.util.Processor;
import mapping.util.ToolkitUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GotoRequestMappingProvider extends DefaultChooseByNameItemProvider {

    @NotNull
    @Override
    public List<String> filterNames(@NotNull ChooseByNameBase base, @NotNull String[] names, @NotNull String pattern) {
        return super.filterNames(base, names, pattern);
    }

    public GotoRequestMappingProvider(@Nullable PsiElement context) {
        super(context);
    }

    @Override
    public boolean filterElements(@NotNull ChooseByNameBase base, @NotNull String pattern, boolean everywhere, @NotNull ProgressIndicator indicator, @NotNull Processor<Object> consumer) {

        pattern = ToolkitUtil.removeRedundancyMarkup(pattern);

        return super.filterElements(base, pattern, everywhere, indicator, consumer);
    }
}
