package ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo;

/**
 * Created by mikel on 15/01/17.
 */
public class LessonBean {
    private int id;
    private int number;
    private String title;
    public LessonBean(int id, int number,String title)
    {
        this.setId(id);
        this.setNumber(number);
        this.setTitle(title);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
