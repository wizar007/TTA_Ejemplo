package ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo;

/**
 * Created by mikel on 15/01/17.
 */
public class Choice {
    private int id;
    private String adviseType;
    private String advise;
    private String enunciado;
    private boolean correct;

    public Choice(int id,String advise,String enunciado,boolean correct)
    {
        this.setId(id);
        this.setAdviseType(adviseType);
        this.setAdvise(advise);
        this.setEnunciado(enunciado);
        this.setCorrect(correct);
    }

    public String getAdviseType() {
        return adviseType;
    }

    public void setAdviseType(String AdviseType) {
        this.adviseType = AdviseType;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }


    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean Correct) {
        this.correct = Correct;
    }
}
