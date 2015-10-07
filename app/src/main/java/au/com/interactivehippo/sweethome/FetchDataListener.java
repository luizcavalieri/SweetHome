package au.com.interactivehippo.sweethome;

import java.util.List;

public interface FetchDataListener {
    public void onFetchComplete(List<TaskListFields> data);
    public void onFetchFailure(String msg);
}