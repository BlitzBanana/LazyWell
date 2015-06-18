package com.lazywell.android.puydufou.business;

/**
 * Created by dherent on 17/06/2015.
 */
public enum Rate
{
        NOT_SATISFED(1),
        PASSABLE(2),
        INTERSTING(3),
        WELL(4),
        EXCELLENT(5);

        private int mValue;

        Rate(int value)
        {
            mValue = value;
        }
}

