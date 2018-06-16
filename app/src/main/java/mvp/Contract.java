package mvp;

import android.graphics.Bitmap;

import java.util.List;

import mvp.m.Record;

/**
 * Created by yj on 2018/6/12.
 */

public class Contract {
  public   interface MainView {
      void startTask(String nowDate, String cardCook);

      void initBg(Bitmap bgBitmap);
  }

   public interface MainPresenter{
       void startTask(String taskType);

       void init();
   }

   public interface CountTimeView {
       void init(String taskType, String nowDate);

       void setTime(String time);

       void gotoComplete(String nowDate, String type, String time);
   }

   public interface CountTimePresenter {
       void init(String taskType, String nowDate);

       void stopCount();

       void recovery();

       void complete();
   }

    public interface HistoryRecordListView {
        void setHistoryRecordList(List<Record> list);

        void deleteRecord(Record record, int position);
    }

    public interface HistoryRecordListPresenter {
        void init();

        void deleteRecord(Record record, int position);
    }
}
