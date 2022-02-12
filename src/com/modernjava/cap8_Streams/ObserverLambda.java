package com.modernjava.cap8_Streams;

import java.util.ArrayList;
import java.util.List;

public class ObserverLambda {

    public static void main(String[] args) {
            Feed feed = new Feed();
            feed.registerObserver(new Guardian());
            feed.registerObserver(new NyTimes());

            feed.notifyObservers("queen");

            Feed feedWithLambda = new Feed();
            feedWithLambda.registerObserver((String tweet) ->
            {
                if(tweet != null && tweet.contains("queen"))
                {
                    System.out.println("Long live the " + tweet);
                }
                else if(tweet != null && tweet.contains("money"))
                {
                    System.out.println("ny " + tweet);
                }
            });

            feedWithLambda.notifyObservers("queen");
    }

    interface Observer{
        void inform(String tweet);
    }

    interface Subject
    {
        void registerObserver(Observer o);
        void notifyObservers(String tweet);
    }

    static private class NyTimes implements Observer
    {
        @Override
        public void inform(String tweet) {
            if(tweet != null && tweet.contains("money"))
            {
                System.out.println("Breaking news with: " + tweet);
            }
        }
    }

    static private class Guardian implements Observer
    {
        @Override
        public void inform(String tweet) {
            if(tweet != null && tweet.contains("queen"))
            {
                System.out.println("Long live the " + tweet);
            }
        }
    }

    static private class Feed implements Subject
    {
        private List<Observer> observerList = new ArrayList<>();
        @Override
        public void registerObserver(Observer o) {
            this.observerList.add(o);
        }

        @Override
        public void notifyObservers(String tweet) {
            this.observerList.forEach(o -> o.inform(tweet));
        }
    }

}
