package Netty.rpc.impl;

import Netty.rpc.service.HelloService;
import org.apache.commons.lang.StringUtils;

/**
 * @author:wuhao
 * @description:业务实现类
 * @date:2022/9/15
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String SayHello(String msg) {
        System.out.println("provider accept msg:" + msg);
        if (StringUtils.isBlank(msg)) {
            return "provider return msg:hello";
        }
        return "Hello Client,I had already received your msg =>>{" + msg + "}";
    }
}
