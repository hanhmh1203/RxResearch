package com.example.fcs.rx_research.webservice;

import com.example.fcs.rx_research.Constant;
import com.example.fcs.rx_research.entity.BestSellerResults;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hanhmai1203 on 11/4/16.
 */

public interface BookService {
    @GET(Constant.WS_BEST_SELLER)
    Observable<BestSellerResults> getBestSeller(@Query("api-key") String apiKey);

}
