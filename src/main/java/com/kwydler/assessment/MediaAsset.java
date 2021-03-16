package com.kwydler.assessment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "media_asset")
public class MediaAsset {

    // Surrogate Primary Key
    @JsonIgnore
    @Id
    @GeneratedValue
    private Long id;

    // Natural Primary Key
    @NotNull
    @Column(name = "asset_id", unique = true, nullable = false)
    private String assetId;

    @NotNull
    @Column(nullable = false)
    private String channel;

    @NotNull
    @Column(name = "asset_type", nullable = false)
    private String assetType;

    @NotNull
    @JsonProperty("SOM")
    @Column(name = "start_of_material", nullable = false)
    private String startOfMaterial;

    @NotNull
    @Column(nullable = false)
    private String duration;

    @NotNull
    @Column(nullable = false)
    private String title;

    @NotNull
    @JsonFormat(pattern = "MM/dd/yyyy")
    @Column(name = "air_date", nullable = false)
    private LocalDate airDate;

    @NotNull
    @JsonProperty("CC")
    @Column(name = "closed_captioning", nullable = false)
    private boolean closedCaptioning;

    @NotNull
    @Column(nullable = false)
    private String audio;

    @NotNull
    @Column(name = "frame_rate", nullable = false)
    private String frameRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getStartOfMaterial() {
        return startOfMaterial;
    }

    public void setStartOfMaterial(String som) {
        this.startOfMaterial = som;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getAirDate() {
        return airDate;
    }

    public void setAirDate(LocalDate airDate) {
        this.airDate = airDate;
    }

    public boolean isClosedCaptioning() {
        return closedCaptioning;
    }

    public void setClosedCaptioning(boolean closedCaptioning) {
        this.closedCaptioning = closedCaptioning;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(String frameRate) {
        this.frameRate = frameRate;
    }

}
