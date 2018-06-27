package com.hs.order.data.api;



import com.hs.base.data.net.protocol.BaseResp;
import com.hs.order.data.protocol.AddShipAddreReq;
import com.hs.order.data.protocol.DeleteShipAddreReq;
import com.hs.order.data.protocol.EditShipAddreReq;
import com.hs.order.model.ShipAddre;
import com.hs.order.model.ShipAddreResp;
import com.hs.order.model.respon.ShipResp;
import com.hs.order.model.respon.ShipListAddre;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 运送地址Api
 */
public interface ShipApi {

    /**
         获取收货信息
     */
    @POST("ship/getShipList")
    Observable<ShipAddreResp> getShipList();


    /**
        添加收货地址
     */
    @POST("ship/addShip")
    Observable<BaseResp<Boolean>> addShipAddre(@Body AddShipAddreReq req);


    /**
        编辑收货地址
     */
    @POST("ship/editShip")
    Observable<BaseResp<Boolean>> editShipAddre(@Body EditShipAddreReq req);


    /**
        删除收货地址
     */
    @POST("ship/deleteShip")
    Observable<BaseResp<Boolean>> deleteShipAddre(@Body DeleteShipAddreReq req);

}
