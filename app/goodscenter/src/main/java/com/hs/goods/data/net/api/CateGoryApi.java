package com.hs.goods.data.net.api;

import com.hs.base.data.net.protocol.BaseResp;
import com.hs.goods.data.net.protocol.GetCateGoryReq;
import com.hs.goods.data.net.protocol.GetCateGorysReq;
import com.hs.goods.model.category.CateGoryp;
import com.hs.goods.model.category.GateGorysResp;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CateGoryApi {
    /**
     * 获取总分类的的数据
     */
    @POST("cate/getCateGoryp")
    Observable<BaseResp<List<CateGoryp>>> getCatepGory(@Body GetCateGoryReq req);

    /**
     * 获取总分类的的数据
     */
    @POST("cate/getCateGorys")
    Observable<GateGorysResp> getCatesGory(@Body GetCateGorysReq req);
}
