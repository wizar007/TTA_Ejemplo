package ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo;

/**
 * Created by mikel on 15/01/17.
 */
public class Exercise {
    private int id;
    private String wording;
    private LessonBean lesson;

    public Exercise(int id, String wording,LessonBean lesson)
    {
        this.setId(id);
        this.setWording(wording);
        this.setLesson(lesson);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }
    public LessonBean getLesson()
    {
        return lesson;
    }
    public void setLesson(LessonBean lesson)
    {
        this.lesson=lesson;
    }

}
