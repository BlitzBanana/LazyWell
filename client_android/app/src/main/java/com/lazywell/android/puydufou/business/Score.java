package com.lazywell.android.puydufou.business;

/**
 * Created by dherent on 17/06/2015.
 */
public enum Score
{
        NOT_SATISFED(1, "Non satisfait"),
        PASSABLE(2, "Moyen"),
        INTERSTING(3, "Interessant"),
        WELL(4, "Bien"),
        EXCELLENT(5, "Excellent !");

        private int value;
        private String text;

        Score(int value, String text)
        {
            this.value = value;
            this.text = text;
        }

        public int getValue() {
                return value;
        }

        public String getText() {
                return text;
        }
}
