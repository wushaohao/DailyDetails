package xmlparse;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * @author:wuhao
 * @description:
 * @date:18/6/2
 */
public class ConfigRuleSet extends RuleSetBase {
    @Override
    public void addRuleInstances(Digester digester) {
        digester.setValidating(false);
        digester.addObjectCreate("web", WebConfig.class);
        digester.addSetProperties("web");

        digester.addObjectCreate("web/root", Root.class);
        digester.addSetProperties("web/root");
        digester.addSetNext("web/root", "addRoot");

        digester.addObjectCreate("web/root/bar", Bar.class);
        digester.addSetProperties("web/root/bar");
        digester.addSetNext("web/root/bar", "addBar");
    }
}
