package com.teeny.wms.service.impl;

import com.teeny.wms.config.WmsException;
import com.teeny.wms.constant.ConstantContract;
import com.teeny.wms.core.domain.RecBillD;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.RecBillRepository;
import com.teeny.wms.dto.*;
import com.teeny.wms.service.AcceptanceService;
import com.teeny.wms.utils.CollectionsUtils;
import com.teeny.wms.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilei on 2017/7/26.
 */
@Service
@Transactional
public class AcceptanceServiceImpl implements AcceptanceService {

    private final RecBillRepository recBillRepository;

    @Autowired
    public AcceptanceServiceImpl(RecBillRepository recBillRepository) {
        this.recBillRepository = recBillRepository;
    }

    /**
     * 根据仓库ID获取单位
     *
     * @param account
     * @param sId     仓库ID
     * @return
     */
    @Override
    public BaseEntity<List<CommonDTO>> getUnit(String account, int sId) {
        List<CommonDTO> unitList = recBillRepository.getUnitList(account, sId);
        return new BaseEntity<>(unitList);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getOrderWithUnitId(int unitId, int sId, String account) {
        return new BaseEntity<List<CommonDTO>>(recBillRepository.getOrderBillWithUnitId(unitId, sId, account));
    }

    /**
     * 根据往来单位ID获取订单详情
     *
     * @param account
     * @param unitId
     * @return
     */
    @Override
    public BaseEntity<List<OrderDetailDTO>> getOrderListWithUnitId(String account, int unitId) {
        List<RecBillDTO> list = recBillRepository.getOrderList(account, unitId);
        List<OrderDetailDTO> result = new ArrayList<>();
        if (CollectionsUtils.sizeOf(list) == 0) {
            return new BaseEntity<>(result);
        }
        for (RecBillDTO dto : list) {
            OrderDetailDTO o = getOrderDetailWithUnitId(account, dto);
            result.add(o);
        }
        return new BaseEntity<>(result);
    }

    private OrderDetailDTO getOrderDetailWithUnitId(String account, RecBillDTO bill) {
        OrderDetailDTO result = new OrderDetailDTO();
        result.setBillNo(bill.getBillNo());
        result.setBuyer(bill.getBuyer());
        result.setBuyerId(bill.getBuyerId());
        result.setOrderId(bill.getOrderId());
        result.setUnitName(bill.getUnitName());
        int orderId = bill.getOrderId();
        List<GoodsDTO> list = recBillRepository.getGoodsByBillId(orderId, account);
        result.setGoodsList(list);
        recBillRepository.updateBillPdaStatus(orderId, ConstantContract.PDA_STATE_READ, account);
        return result;
    }

    @Override
    public BaseEntity<String> updateGoodsByOrderId(List<Integer> ids, String account) {
        for (Integer id : ids) {
            recBillRepository.updateGoodsByOrderId(id, account);
            int count = recBillRepository.countByDealType(id, account);
            if (count == 0) {
                recBillRepository.updateBillByGoodsId(id, account);
            }
        }
        return new BaseEntity<>();
    }


    /**
     * 更新子数据的状态
     *
     * @param recUpdateDTO
     * @param account
     * @return
     */
    @Override
    public BaseEntity<String> updateGoodsByGoodsId(RecUpdateDTO recUpdateDTO, String account, int userId) {
        List<AcceptAddDTO> param = recUpdateDTO.getParam();
        if (Validator.isEmpty(param)) {
            BaseEntity<String> result = new BaseEntity<>();
            result.setResult(1);
            result.setMsg("未添加批号.");
            throw new WmsException(result);
        }

        RecBillD original = recBillRepository.getOriginal(account, recUpdateDTO.getId());

        recBillRepository.deleteById(recUpdateDTO.getId(), account);

        for (AcceptAddDTO dto : param) {
            RecBillD in = new RecBillD(original);
            in.DealStates = 1;
            in.Batchno = dto.getLotNo();
            in.Validdate = dto.getValidityDate();
            in.EligibleQty = dto.getAmount();
            in.TaxPrice = dto.getPrice();
            in.rownumber = dto.getSerialNo();
            in.retailQty = dto.getLhAmount();
            in.WholeQty = dto.getZhAmount();
            recBillRepository.addData(account, in, userId);
        }

        int count = recBillRepository.countByDealType(recUpdateDTO.getId(), account);
        if (count == 0) {
            recBillRepository.updateBillByGoodsId(recUpdateDTO.getId(), account);
        }
        return new BaseEntity<>();
    }


    //单个完成
    @Override
    public BaseEntity<String> completeOne(int id, String account) {
        recBillRepository.completeOne(id, account);
        int count = recBillRepository.countByDealType(id, account);
        if (count == 0) {
            recBillRepository.updateBillByGoodsId(id, account);
        }
        return new BaseEntity<String>();
    }

    @Override
    public BaseEntity<List<AcceptAddDTO>> getLotList(int id, String account) {
        return new BaseEntity<>(recBillRepository.getLotList(id, account));
    }


    /**
     * 根据订单号获取订单数据
     *
     * @param billNo
     * @param account
     * @param sId
     * @return
     */
    @Override
    public BaseEntity<List<OrderDetailDTO>> getBillsByBillNo(String billNo, String account, int sId) {
        Integer unitId = recBillRepository.findUnitByBillNo(billNo, account);
        if (unitId == null || unitId == 0) {
            BaseEntity<String> result = new BaseEntity<>();
            result.setResult(1);
            result.setMsg("没有此单号数据！");
            throw new WmsException(result);
        }
        return getOrderListWithUnitId(account, unitId);
    }

    @Override
    public void test() {

        int a = recBillRepository.test(1, "2012-1-2", "批号123", 34.3, 5435, "yyt");
        System.out.print(a);

    }


}
