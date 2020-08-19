package ru.vologda.testfragment;

import java.util.ArrayList;
import java.util.Random;

public class Questions {
    public ArrayList<Question> questions = new ArrayList<>();
    Questions(){
        addQuestion("Можно ли переходить улицу на красный сигнал светофора?", false, "Переходить можно только на зелёный сигнал пешеходного светофора");
        addQuestion("Правила дорожного движения нужно знать только водителям, а пешеходам они совершенно ни к чему, правильно?",false,"Правила дорожного движения должны знать все");
        addQuestion("Все знают, что «красный свет – дороги нет», а на зелёный сигнал светофора можно начинать движение. А можно ли идти пешеходу на жёлтый свет?", false,"Нет, потому что желтый сигнал на транспортном светофоре, то есть для водителей");
        addQuestion("Можно ли выезжать на проезжую часть на скейтах и роликовых коньках?", false,"Скейт и коньки можно использовать только на тротуаре");
        addQuestion("Автобусы и троллейбусы нужно обходить сзади", true,"Общественный транспорт нельзя обходить сзади. Нужно пользоваться только пешеходным переходом");
        addQuestion("Можно ли переходить дорогу на постоянно мигающий жёлтый сигнал светофора?", true, "Светофор неисправен, поэтому можно, убедившись в полной безопасности перехода");
        addQuestion("Можно ли переходить дорогу вне пешеходного перехода, если она просматривается только в одном направлении?", false,"Нельзя, надо дойти до перехода");
        addQuestion("Какой пешеходный переход считается самым безопасным: наземный, надземный или подземный?", false, "Подземный");
        addQuestion("Если движение перекрестка регулирует светофор и одновременно регулировщик,  следует ли подчиняться командам регулировщика?", false, "Следует подчиняться командам регулировщика");
        addQuestion("Человек, который везёт коляску с малышом - пешеход?", true,"Пешеход");
        addQuestion("Дорогу переходить нужно как можно быстрее, лучше - бегом. Правильно?", false, "Дорогу нельзя перебегать, водитель может не успеть среагировать");
    }
    public  void addQuestion(String text, boolean answer, String answerText){
        questions.add(new Question(text,answer,answerText));
    }
    public ArrayList<Question> getQuestions(int num){
        ArrayList<Question> myQuestions = new ArrayList<>();
        int q =0;
        Random random = new Random();
        while(q<num){
            Question question = questions.get(random.nextInt(questions.size()-1));
            if(!myQuestions.contains(question)){
                myQuestions.add(question);
                q++;
            }
        }
        return myQuestions;
    }
}
