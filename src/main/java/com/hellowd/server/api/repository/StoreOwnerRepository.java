package com.hellowd.server.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-08
 * Time : 오후 2:38
 * To change this template use File | Settings | File and Code Templates.
 */
public interface StoreOwnerRepository extends PagingAndSortingRepository<StoreOwner, Long> {
    StoreOwner findBySeq(Long seq);
}
