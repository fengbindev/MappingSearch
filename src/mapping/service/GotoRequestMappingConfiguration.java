package mapping.service;

import com.intellij.ide.util.gotoByName.ChooseByNameFilterConfiguration;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.project.Project;
import mapping.util.HttpMethod;

/**
 * 注册哟个项目级服务，用于搜索
 */
@State(name = "GotoRequestMappingConfiguration", storages = @Storage(StoragePathMacros.WORKSPACE_FILE))
public class GotoRequestMappingConfiguration extends ChooseByNameFilterConfiguration<HttpMethod> {
  /**
   * Get configuration instance
   *
   * @param project a project instance
   * @return a configuration instance
   */
  public static GotoRequestMappingConfiguration getInstance(Project project) {
    return ServiceManager.getService(project, GotoRequestMappingConfiguration.class);
  }

  @Override
  protected String nameForElement(HttpMethod type) {
    return type.name();
  }
}
