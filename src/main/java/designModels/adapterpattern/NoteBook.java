package designModels.adapterpattern;

import designModels.adapterpattern.combinetype.GBTwoPlugin;
import designModels.adapterpattern.combinetype.ThreePlugIf;
import designModels.adapterpattern.combinetype.TwoPlugAdapter;

/**
 * @author:wuhao
 * @description:笔记本
 * @date:18/8/20
 */
public class NoteBook {

    private ThreePlugIf threePlugIf;

    public NoteBook(ThreePlugIf threePlugIf) {
        this.threePlugIf = threePlugIf;
    }

    public void charge() {
        System.out.println("是用插座充电");
        threePlugIf.powerWithThree();
    }

    public static void main(String[] args) {
        GBTwoPlugin gbTwoPlugin = new GBTwoPlugin();
        // 采用组合方式实现适配
        ThreePlugIf ComThreePlugIf = new TwoPlugAdapter(gbTwoPlugin);
        NoteBook noteBook = new NoteBook(ComThreePlugIf);
        noteBook.charge();

        // 采用继承方式实现适配
        ThreePlugIf ExtendsThreePlugIf = new TwoPlugAdapter(gbTwoPlugin);
        NoteBook noteBook2 = new NoteBook(ExtendsThreePlugIf);
        noteBook2.charge();


    }
}
