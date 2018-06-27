package com.hs.hsj.ext;

import com.hs.hsj.common.MainConstant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/13.
 *
 */

public class AppExt {
    public static List<String> imgUrlList;
    public static List<String> hotsList;
    public static List<String> titles;
    //折扣
    public static List<String> discList;
    public static List<String> topcList;

    public static void getHomeList(){
        imgUrlList = new ArrayList<>();
        hotsList = new ArrayList<>();
        titles = new ArrayList<>();
        discList = new ArrayList<>();
        topcList = new ArrayList<>();

        imgUrlList.add(MainConstant.HOME_BANNER_ONE);
        imgUrlList.add(MainConstant.HOME_BANNER_TWO);
        imgUrlList.add(MainConstant.HOME_BANNER_THREE);
        imgUrlList.add(MainConstant.HOME_BANNER_FOUR);

        discList.add(MainConstant.HOME_DISCOUNT_ONE);
        discList.add(MainConstant.HOME_DISCOUNT_TWO);
        discList.add(MainConstant.HOME_DISCOUNT_THREE);
        discList.add(MainConstant.HOME_DISCOUNT_FOUR);
        discList.add(MainConstant.HOME_DISCOUNT_FIVE);

        topcList.add(MainConstant.HOME_TOPIC_ONE);
        topcList.add(MainConstant.HOME_TOPIC_TWO);
        topcList.add(MainConstant.HOME_TOPIC_THREE);
        topcList.add(MainConstant.HOME_TOPIC_FOUR);
        topcList.add(MainConstant.HOME_TOPIC_FIVE);

        titles.add(MainConstant.bannerTitle1);
        titles.add(MainConstant.bannerTitle2);
        titles.add(MainConstant.bannerTitle3);
        titles.add(MainConstant.bannerTitle4);

        hotsList.add("夏日炎炎，第一波福利还有30秒到达战场");
        hotsList.add("新用户立领1000元优惠券");
    }
}
