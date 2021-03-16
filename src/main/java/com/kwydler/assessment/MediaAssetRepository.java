package com.kwydler.assessment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MediaAssetRepository extends JpaRepository<MediaAsset, Long> {

    Optional<MediaAsset> findByAssetId(String assetId);

}
