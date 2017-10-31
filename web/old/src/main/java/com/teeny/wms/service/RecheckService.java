package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.Putaway.RecheckCompleteDTO;
import com.teeny.wms.dto.RecipientDTO;
import com.teeny.wms.dto.ReviewDTO;
import com.teeny.wms.dto.ReviewUpdateDTO;

import java.util.List;

/**
 * Created by bao on 2017/7/27.
 */
public interface RecheckService {

    void updateRecheckBill(String account, ReviewUpdateDTO reviewUpdateDTO);

    BaseEntity<ReviewDTO> getWarehouseReview(String account, String billNo, int userId);

    BaseEntity<List<CommonDTO>> getBills(int sId, String account);

    BaseEntity<String> complete(RecheckCompleteDTO recheckCompleteDTO, String account);

    BaseEntity<List<RecipientDTO>> getRecipients(String account, int sId);

    BaseEntity<Integer> getReplenishmentCount(String account, int sId);
}
