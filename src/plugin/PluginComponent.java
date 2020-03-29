package plugin;

import com.intellij.openapi.project.Project;
import com.intellij.util.xml.ui.*;
import plugin.extend.ExtendActionControl;
import plugin.extend.ExtendItemControl;
import plugin.extend.ExtendPointControl;
import plugin.extend.ExtendServiceControl;
import plugin.xml.Plugin;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class PluginComponent extends BasicDomElementComponent<Plugin> {
    private JPanel myRootPane;
    private TextPanel myId;
    private TextPanel myName;
    private TextPanel myAuthor;
    private TextPanel myProvider;
    private TextPanel myVersion;
    private TextPanel myDescription;
    private PsiClassPanel myClass;
    private Project project;
    private DomTableView extendPointTable;
    private DomTableView extendActionTable;
    private DomTableView extendServiceTable;
    private DomTableView extendItemTable;

    public PluginComponent(Project project, Plugin domElement) {
        super(domElement);
        this.project = project;
        bindProperties();
        myId.setBorder(LineBorder.createGrayLineBorder());
        myAuthor.setBorder(LineBorder.createGrayLineBorder());
        myProvider.setBorder(LineBorder.createGrayLineBorder());
        myVersion.setBorder(LineBorder.createGrayLineBorder());
        myVersion.setBorder(LineBorder.createGrayLineBorder());
        myDescription.setBorder(LineBorder.createGrayLineBorder());
        myClass.setBorder(LineBorder.createGrayLineBorder());
        // extendPoint
        ChildGenericValueColumnInfo pointId = new ChildGenericValueColumnInfo("id", new ExtendPointControl.ExtendPointID(), new DefaultCellEditor(new JTextField()));
        ChildGenericValueColumnInfo pointClasses = new ChildGenericValueColumnInfo("class",new ExtendPointControl.ExtendPointClass(), DomUIFactory.createCellEditor(domElement.getClasses()));
        ChildGenericValueColumnInfo pointDescription = new ChildGenericValueColumnInfo("description", new ExtendPointControl.ExtendPointDescription(), new DefaultCellEditor(new JTextField()));
        ExtendPointControl extendPointControl = new ExtendPointControl(domElement, "extend-point", true, pointId, pointClasses, pointDescription);
        doBind(extendPointControl, extendPointTable);
        // extendAction
        ChildGenericValueColumnInfo actionId = new ChildGenericValueColumnInfo("id", new ExtendActionControl.ExtendActionID(), new DefaultCellEditor(new JTextField()));
        ChildGenericValueColumnInfo actionClasses = new ChildGenericValueColumnInfo("class",new ExtendActionControl.ExtendActionClass(), DomUIFactory.createCellEditor(domElement.getClasses()));
        ChildGenericValueColumnInfo actionDescription = new ChildGenericValueColumnInfo("description", new ExtendActionControl.ExtendActionDescription(), new DefaultCellEditor(new JTextField()));
        ChildGenericValueColumnInfo actionExtendPoint = new ChildGenericValueColumnInfo("extendPointId", new ExtendActionControl.ExtendActionExtendPoint(), new DefaultCellEditor(new JTextField()));
        ExtendActionControl extendActionControl = new ExtendActionControl(domElement, "extend-action", true, actionId, actionClasses, actionDescription, actionExtendPoint);
        doBind(extendActionControl, extendActionTable);
        // extendService
        ChildGenericValueColumnInfo serviceId = new ChildGenericValueColumnInfo("id", new ExtendServiceControl.ExtendServiceID(), new DefaultCellEditor(new JTextField()));
        ChildGenericValueColumnInfo serviceClasses = new ChildGenericValueColumnInfo("class",new ExtendServiceControl.ExtendServiceClass(), DomUIFactory.createCellEditor(domElement.getClasses()));
        ChildGenericValueColumnInfo serviceDescription = new ChildGenericValueColumnInfo("description", new ExtendServiceControl.ExtendServiceDescription(), new DefaultCellEditor(new JTextField()));
        ChildGenericValueColumnInfo serviceItemClass = new ChildGenericValueColumnInfo("itemClass", new ExtendServiceControl.ExtendServiceItemClass(), DomUIFactory.createCellEditor(domElement.getClasses()));
        ExtendServiceControl extendServiceControl = new ExtendServiceControl(domElement, "extend-service", true, serviceId, serviceClasses, serviceDescription, serviceItemClass);
        doBind(extendServiceControl, extendServiceTable);
        // extendItem
        ChildGenericValueColumnInfo itemId = new ChildGenericValueColumnInfo("id", new ExtendItemControl.ExtendItemID(), new DefaultCellEditor(new JTextField()));
        ChildGenericValueColumnInfo itemClasses = new ChildGenericValueColumnInfo("class",new ExtendItemControl.ExtendItemClass(), DomUIFactory.createCellEditor(domElement.getClasses()));
        ChildGenericValueColumnInfo itemDescription = new ChildGenericValueColumnInfo("description", new ExtendItemControl.ExtendItemDescription(), new DefaultCellEditor(new JTextField()));
        ChildGenericValueColumnInfo itemExtendService = new ChildGenericValueColumnInfo("extendServiceId", new ExtendItemControl.ExtendItemExtendService(),new DefaultCellEditor(new JTextField()));
        ExtendItemControl extendItemControl = new ExtendItemControl(domElement, "extend-item", true, itemId, itemClasses, itemDescription, itemExtendService);
        doBind(extendItemControl, extendItemTable);


    }

    @Override
    public JComponent getComponent() {
        return myRootPane;
    }

    public void createUIComponents() {
        extendPointTable = new DomTableView(project);
        extendServiceTable = new DomTableView(project);
        extendItemTable = new DomTableView(project);
        extendActionTable = new DomTableView(project);
    }
}
