package com.sanju.remoteapilibrary.core.repository;

import com.sanju.remoteapilibrary.core.entity.YoutubeVideoStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeVideoStatisticsRepository extends JpaRepository<YoutubeVideoStatistics,Long> {
}
