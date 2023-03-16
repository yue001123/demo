package org.example.strategy;

import java.util.Objects;

/**
 * 程序员
 * 分享用策略模式去消除代码中的if else
 * @author xiaomage
 * @date 2022/12/18
 */
public class Programmer {

    private Program program;

    public Programmer(Program program) {
        this.program = program;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

//    /**
//     * 编码
//     *
//     * @param codeType 类型
//     */
//    public void coding(String codeType) {
//        if (Objects.equals("IDEA",codeType)) {
//            System.out.println("用IDEA编码");
//        }else if(Objects.equals("Eclipse",codeType)){
//            System.out.println("用Eclipse编码");
//        }
////    ......
//    }

    /**
     * 用策略模式编码
     */
    public void codingStrategy( ) {
        program.coding();
    }

    public static void main(String[] args) {
        Programmer programmerI = new Programmer(new IDEA());
        programmerI.codingStrategy();

        Programmer programmerE = new Programmer(new Eclipse());
        programmerE.codingStrategy();
    }


}
