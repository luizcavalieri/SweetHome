package au.com.interactivehippo.sweethome;

import java.util.List;

/**
 * Created by luizcavalieri on 19/07/2016.
 */
public interface FetchDataListenerUser {
    public void onFetchCompleteUser(List<User> data);
    public void onFetchFailureUser(String msg);
}
