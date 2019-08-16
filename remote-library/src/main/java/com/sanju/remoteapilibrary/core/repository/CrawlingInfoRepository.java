package com.sanju.remoteapilibrary.core.repository;

import com.sanju.remoteapilibrary.core.entity.CrawlingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlingInfoRepository extends JpaRepository<CrawlingInfo,Long> {
    CrawlingInfo findBySearchKey(String searchKey);
}
