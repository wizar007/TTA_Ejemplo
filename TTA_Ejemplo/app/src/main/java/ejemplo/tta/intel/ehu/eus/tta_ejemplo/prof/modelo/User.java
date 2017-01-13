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

    public User(int id,String user,int lessonNumber, String lessonTitle, int nextTest, int nextExercise )
    {
        this.setId(id);
        this.setUser(user);
        this.setLessonNumber(lessonNumber);
        this.setLessonTitle(lessonTitle);
        this.setNextTest(nextTest);
        this.setNextExercise(nextExercise);
    }

    private int getId()
    {
        return this.id;
    }
    private void setId(int id)
    {
        this.id=id;
    }
    private String getUser()
    {
        return this.user;
    }
    private void setUser(String user)
    {
        this.user=user;
    }
    private int getLessonNumber()
    {
        return this.lessonNumber;
    }
    private void setLessonNumber(int lessonNumber)
    {
        this.lessonNumber=lessonNumber;
    }
    private String getLessonTitle()
    {
        return this.lessonTitle;
    }

    private void setLessonTitle(String lessonTitle)
    {
        this.lessonTitle=lessonTitle;
    }
    private int getNextTest()
    {
        return this.nextTest;
    }
    private void setNextTest(int nextTest)
    {
        this.nextTest=nextTest;
    }
    private int getNextExercise()
    {
        return this.nextExercise;
    }
    private void setNextExercise(int nextExercise)
    {
        this.nextExercise=nextExercise;
    }
}
