package org.example.zhuangshinew;


public class MainTest {
    public static void main(String[] args) {

//        IwatchFilm iwatchFilm = new HaoHuaFilm();
//        iwatchFilm.watch();

//        IwatchFilm iwatchFilm = new HaoHuaFilm();
//
//        IwatchFilm foodDecorator = new FoodDecorator(iwatchFilm) ;
//        foodDecorator.watch();


        IwatchFilm iwatchFilm = new HaoHuaFilm();
        IwatchFilm foodDecorator = new FoodDecorator(iwatchFilm) ;
        IwatchFilm girlFriendDecorator = new GirlFriendDecorator(foodDecorator);
        girlFriendDecorator.watch();

    }
}
