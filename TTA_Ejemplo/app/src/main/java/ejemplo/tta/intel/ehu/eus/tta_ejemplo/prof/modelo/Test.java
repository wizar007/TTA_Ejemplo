package ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikel on 15/01/17.
 */


public class Test {
    private String wording;
    public List<Choice> choices=new ArrayList<>();

    public Test ()
    {

    }

    public Test (List<Choice> list, String enunciado)
    {
        choices.addAll(list);
        this.setWording(enunciado);

    }


    public List<Choice> getChoice() {
        return choices;
    }

    public void addChoice(Choice choice) {
        this.choices.add(choice);
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording=wording;
    }

    public int getCorrect(){
        int correct = -1;
        for(int i=0;i<choices.size();i++){
            if(choices.get(i).isCorrect())
                correct=i;
        }
        return correct;
    }

}