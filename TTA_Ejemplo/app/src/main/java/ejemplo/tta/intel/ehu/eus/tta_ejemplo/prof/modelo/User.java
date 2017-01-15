package ejemplo.tta.intel.ehu.eus.tta_ejemplo.prof.modelo;

/**
 * Created by mikel on 13/01/17.
 */
public class User {
    private int id;
    private String user;
    private int lessonNumber;
    private String lessonTitle;
    private int nextTest;
    private int nextExercise;

    public User()
    {

    }

    public User(int id,String user,int lessonNumber, String lessonTitle, int nextTest, int nextExercise )
    {
        this.setId(id);
        this.setUser(user);
        this.setLessonNumber(lessonNumber);
        this.setLessonTitle(lessonTitle);
        this.setNextTest(nextTest);
        this.setNextExercise(nextExercise);
    }

    public int getId()
    {
        return this.id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getUser()
    {
        return this.user;
    }
    public void setUser(String user)
    {
        this.user=user;
    }
    public int getLessonNumber()
    {
        return this.lessonNumber;
    }
    public void setLessonNumber(int lessonNumber)
    {
        this.lessonNumber=lessonNumber;
    }
    public String getLessonTitle()
    {
        return this.lessonTitle;
    }

    public void setLessonTitle(String lessonTitle)
    {
        this.lessonTitle=lessonTitle;
    }
    public int getNextTest()
    {
        return this.nextTest;
    }
    public void setNextTest(int nextTest)
    {
        this.nextTest=nextTest;
    }
    public int getNextExercise()
    {
        return this.nextExercise;
    }
    public void setNextExercise(int nextExercise)
    {
        this.nextExercise=nextExercise;
    }
}
