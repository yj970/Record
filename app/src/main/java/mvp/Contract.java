package mvp;

/**
 * Created by yj on 2018/6/12.
 */

public class Contract {
  public   interface MainView {
      void startTask(String nowDate, String cardCook);
  }

   public interface MainPresenter{
       void startTask(String taskType);
   }

   public interface CountTimeView {
       void init(String taskType, String nowDate);

       void setTime(String time);
   }

   public interface CountTimePresenter {
       void init(String taskType, String nowDate);

       void recovery();
   }
}
