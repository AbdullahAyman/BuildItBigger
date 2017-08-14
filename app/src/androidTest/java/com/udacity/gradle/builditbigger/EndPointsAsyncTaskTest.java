package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.util.Pair;

import com.example.SayJoke;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

/**
 * Created by aayman on 8/9/2017.
 */

@RunWith(AndroidJUnit4.class)
public class EndPointsAsyncTaskTest {

    Context context;

    @Test
    public void testDisplayJoke() throws InterruptedException {
        Assert.assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);
        context = InstrumentationRegistry.getContext();
        EndpointsAsyncTask testTask = new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                Assert.assertNotNull(result);
                if (result != null) {
                    Assert.assertTrue(result.length() > 0);
                    latch.countDown();
                }
            }
        };
        testTask.execute(new Pair<Context, String>(context, SayJoke.tellJoke(" Reviewer")));
        latch.await();
    }
}
