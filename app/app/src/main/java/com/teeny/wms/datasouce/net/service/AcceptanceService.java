package com.teeny.wms.datasouce.net.service;

import com.teeny.wms.model.AcceptanceLotEntity;
import com.teeny.wms.model.EmptyEntity;
import com.teeny.wms.model.KeyValueEntity;
import com.teeny.wms.model.ReceivingAcceptanceEntity;
import com.teeny.wms.model.ResponseEntity;
import com.teeny.wms.model.request.AcceptanceRequestEntity;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AcceptanceService
 * @since 2017/8/3
 */

public interface AcceptanceService {

    @GET("acceptance/unit")
    Flowable<ResponseEntity<List<KeyValueEntity>>> getUnitList();

    /**
     * 获取详情
     *
     * @param id 单位id
     * @return List<ReceivingAcceptanceEntity>
     */
    @GET("acceptance/detail/{id}")
    Flowable<ResponseEntity<List<ReceivingAcceptanceEntity>>> getDetail(@Path("id") int id);

    /**
     * 通过单号获取详情
     *
     * @param billNo 单位id
     * @return List<ReceivingAcceptanceEntity>
     */
    @GET("acceptance/detail")
    Flowable<ResponseEntity<List<ReceivingAcceptanceEntity>>> getDetailOrderNo(@Query("billNo") String billNo);

    @GET("acceptance/lots/{id}")
    Flowable<ResponseEntity<List<AcceptanceLotEntity>>> getLotList(@Path("id") int id);

    @POST("acceptance/complete")
    Flowable<ResponseEntity<EmptyEntity>> complete(@Body AcceptanceRequestEntity entity);
}
