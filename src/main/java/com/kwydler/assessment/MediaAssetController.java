package com.kwydler.assessment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class MediaAssetController {

    private final MediaAssetRepository repository;

    public MediaAssetController(MediaAssetRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/media-assets")
    public ResponseEntity<List<MediaAsset>> getAll() {
        var assets = repository.findAll();
        return ResponseEntity.ok(assets);
    }

    @GetMapping(value = "/media-assets/{assetId}")
    public ResponseEntity<MediaAsset> getOne(@PathVariable("assetId") String assetId) {
        var asset = repository.findByAssetId(assetId);
        if(asset.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asset.get());
    }

    @PostMapping("/media-assets")
    public ResponseEntity<MediaAsset> create(@Valid @RequestBody MediaAsset body) {
        var asset = new MediaAsset();
        asset.setAssetId(body.getAssetId());
        asset.setChannel(body.getChannel());
        asset.setAssetType(body.getAssetType());
        asset.setStartOfMaterial(body.getStartOfMaterial());
        asset.setDuration(body.getDuration());
        asset.setTitle(body.getTitle());
        asset.setAirDate(body.getAirDate());
        asset.setClosedCaptioning(body.isClosedCaptioning());
        asset.setAudio(body.getAudio());
        asset.setFrameRate(body.getFrameRate());
        var created = repository.save(asset);
        return ResponseEntity.created(URI.create("/media-assets/" + asset.getAssetId())).body(created);
    }

    @PutMapping(value = "/media-assets/{assetId}")
    public ResponseEntity<MediaAsset> update(@PathVariable("assetId") String assetId, @Valid @RequestBody MediaAsset body) {
        var opt = repository.findByAssetId(assetId);
        if(opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var asset = opt.get();
        // Does it make sense to allow the AssetId value to be updated? To make things simple for this assessment we'll
        // assume that it can be updated.
        asset.setAssetId(body.getAssetId());
        asset.setChannel(body.getChannel());
        asset.setAssetType(body.getAssetType());
        asset.setStartOfMaterial(body.getStartOfMaterial());
        asset.setDuration(body.getDuration());
        asset.setTitle(body.getTitle());
        asset.setAirDate(body.getAirDate());
        asset.setClosedCaptioning(body.isClosedCaptioning());
        asset.setAudio(body.getAudio());
        asset.setFrameRate(body.getFrameRate());
        var updated = repository.save(asset);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = "/media-assets/{assetId}")
    public ResponseEntity<?> delete(@PathVariable("assetId") String assetId) {
        var asset = repository.findByAssetId(assetId);
        if(asset.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(asset.get());
        return ResponseEntity.noContent().build();
    }

}
