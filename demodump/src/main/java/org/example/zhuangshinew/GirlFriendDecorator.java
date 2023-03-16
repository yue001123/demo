package org.example.zhuangshinew;


/**
 * 女朋友装饰类
 *
 * @author xiaomage
 * @date 2022/12/21
 */
public class GirlFriendDecorator extends FoodDecorator {

    public GirlFriendDecorator(IwatchFilm iwatchFilm) {
        super(iwatchFilm);
    }

    @Override
    public void watch() {
        System.out.println("带着女朋友看电影");
        super.watch();
    }


}
