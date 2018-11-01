package google.guava.parametercheck;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author:wuhao
 * @description:参数检查
 * @date:18/11/1
 */
public class CheckParameters {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        String str = "abd";
        //use java
        if (list != null && list.size() > 0) {

        }
        if (str != null && str.length() > 0) {

        }

        if (str != null && !str.isEmpty()) {

        }
        //use guava
        if (!Strings.isNullOrEmpty(str)) {

        }

        int count = 20;
        //use java
        if (count <= 0) {
            throw new IllegalArgumentException("must be positive: " + count);
        }

        //use guava
        Preconditions.checkArgument(count > 0, "must be positive: %s", count);
        String state = "open";
        Preconditions.checkState(state.equals("open"),"must be open:%s",state);

    }
}
