package model;

public class Customer {
    private int stampCount = 0;
    private int couponCount = 0;

    public void addStamp() {
        stampCount++;
        if (stampCount >= 10) {
            stampCount = 0;
            couponCount++;
        }
    }

    public int getStampCount() {
        return stampCount;
    }

    public void useStamps() {
        stampCount = 0;
    }

    public void addCoupon() {
        couponCount++;
    }

    public int getCouponCount() {
        return couponCount;
    }

    public void useCoupon() {
        couponCount = 0;
    }
}
