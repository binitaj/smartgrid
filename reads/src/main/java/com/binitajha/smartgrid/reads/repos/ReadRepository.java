package com.binitajha.smartgrid.reads.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.binitajha.smartgrid.reads.repos.model.Read;

public interface ReadRepository extends PagingAndSortingRepository<Read, String> {

	@Query("SELECT r FROM Read r LEFT JOIN FETCH r.registers LEFT JOIN FETCH r.intervals WHERE r.deviceId = :deviceId")
	@EntityGraph(value = "Read.detail", type = EntityGraphType.LOAD)
	List<Read> findPageByDeviceId(Long deviceId, Pageable pageable);

	List<Read> findByDeviceId(Long deviceId);

	List<Read> findByRadioId(long radioId);

}
