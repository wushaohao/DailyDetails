package Reflect;

/**
 * Created by wuhao on 17/2/24.
 */
public class CsfSrvRegisterCSVImpl implements ICsfSrvRegisterCSV {

    @Override
    public void getName(String name, int age) {

    }


    public static void main(String[] args) {
        String interName="com.chinaunicom.card.busi.ResCardService";
        int index=interName.lastIndexOf(".");
        String packageName=interName.substring(0,index);
        String interfaceName=interName.substring(index+1,interName.length());
        String implName=packageName+".impl."+interfaceName+"Impl";
        System.out.println("实现类的名称是:"+implName);
    }
}
