package com.teeny.wms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see DocumentDTO
 * @since 2017/9/1
 */
public class DocumentDTO implements Serializable {

    private List<QueryDocumentDTO> acceptanceList;
    private List<QueryDocumentDTO> putawayList;
    private List<QueryDocumentDTO> allotList;
    private List<QueryDocumentDTO> reviewList;

    public List<QueryDocumentDTO> getAcceptanceList() {
        return acceptanceList;
    }

    public void setAcceptanceList(List<QueryDocumentDTO> acceptanceList) {
        this.acceptanceList = acceptanceList;
    }

    public List<QueryDocumentDTO> getPutawayList() {
        return putawayList;
    }

    public void setPutawayList(List<QueryDocumentDTO> putawayList) {
        this.putawayList = putawayList;
    }

    public List<QueryDocumentDTO> getAllotList() {
        return allotList;
    }

    public void setAllotList(List<QueryDocumentDTO> allotList) {
        this.allotList = allotList;
    }

    public List<QueryDocumentDTO> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<QueryDocumentDTO> reviewList) {
        this.reviewList = reviewList;
    }
}
