package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by vadivelansr on 12/1/2015.
 */
public class JokeTest extends ApplicationTestCase<Application> implements OnJokeReceivedListener {
    CountDownLatch signal;
    String mJoke;
    public JokeTest(){
        super(Application.class);
    }
    public void testJoke(){
        try{
            signal = new CountDownLatch(1);
            new JokeEndpointAsyncTask().execute(this);
            signal.await(10, TimeUnit.SECONDS);
            assertNotNull("Joke is NULL", mJoke);
            assertFalse("Joke is Empty", mJoke.isEmpty());

        }catch(Exception e){
            fail();
        }
    }

    @Override
    public void onReceive(String joke) {
        mJoke = joke;
        signal.countDown();
    }
}
