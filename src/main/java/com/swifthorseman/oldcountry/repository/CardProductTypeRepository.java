package com.swifthorseman.oldcountry.repository;

import com.swifthorseman.oldcountry.domain.CardProductType;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CardProductType entity.
 */
public interface CardProductTypeRepository extends JpaRepository<CardProductType,Long> {

}
