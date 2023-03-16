package org.example.zhuangshinew;


/**
 * 食物装饰类
 *
 * @author xiaomage
 * @date 2022/12/21
 */
public class FoodDecorator implements IwatchFilm {

    private IwatchFilm iwatchFilm;

    public FoodDecorator(IwatchFilm iwatchFilm) {
        this.iwatchFilm = iwatchFilm;
    }

    @Override
    public void watch() {
        System.out.println("我买了好多零食！");
        iwatchFilm.watch();
    }

}
