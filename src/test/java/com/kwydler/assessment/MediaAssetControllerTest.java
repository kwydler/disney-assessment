package com.kwydler.assessment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
// Combining @DataJpaTest with @SpringBootTest causes issues so we will use the individual annotations that do not
// conflict.
@Transactional
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
public class MediaAssetControllerTest {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private MediaAssetController controller;

    @Test
    public void getAll_empty() {
        // Arrange

        // Act
        var result = controller.getAll();

        // Assert
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);

        var body = result.getBody();
        Assertions.assertEquals(body.size(), 0);
    }

    @Test
    public void getAll() {
        // Arrange
        var asset1 = new MediaAsset();
        asset1.setAssetId("XYZ707AB2");
        asset1.setChannel("ESPN");
        asset1.setAssetType("Promo");
        asset1.setStartOfMaterial("01:00:00;00");
        asset1.setDuration("00:00:30;00");
        asset1.setTitle("Just do it - Nike");
        asset1.setAirDate(LocalDate.of(2021, 4, 23));
        asset1.setClosedCaptioning(true);
        asset1.setAudio("5.1");
        asset1.setFrameRate("59.94");
        manager.persistAndFlush(asset1);

        var asset2 = new MediaAsset();
        asset2.setAssetId("ABC543DJ1");
        asset2.setChannel("ABC");
        asset2.setAssetType("Promo");
        asset2.setStartOfMaterial("02:00:00;00");
        asset2.setDuration("00:00:15;00");
        asset2.setTitle("Just do it - Nike");
        asset2.setAirDate(LocalDate.of(2021, 3, 14));
        asset2.setClosedCaptioning(true);
        asset2.setAudio("2.1");
        asset2.setFrameRate("24");
        manager.persistAndFlush(asset2);

        // Act
        var result = controller.getAll();

        // Assert
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);

        var body = result.getBody();
        Assertions.assertEquals(body.size(), 2);
        assertEquals(body.get(0), asset1);
        assertEquals(body.get(1), asset2);
    }

    @Test
    public void getOne_notFound() {
        // Arrange

        // Act
        var result = controller.getOne("ABC543DJ1");

        // Assert
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void getOne() {
        // Arrange
        var asset = new MediaAsset();
        asset.setAssetId("XYZ707AB2");
        asset.setChannel("ESPN");
        asset.setAssetType("Promo");
        asset.setStartOfMaterial("01:00:00;00");
        asset.setDuration("00:00:30;00");
        asset.setTitle("Just do it - Nike");
        asset.setAirDate(LocalDate.of(2021, 4, 23));
        asset.setClosedCaptioning(true);
        asset.setAudio("5.1");
        asset.setFrameRate("59.94");
        manager.persistAndFlush(asset);

        // Act
        var result = controller.getOne("XYZ707AB2");

        // Assert
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);

        var body = result.getBody();
        assertEquals(body, asset);
    }

    @Test
    public void create() {
        // Arrange
        var asset = new MediaAsset();
        asset.setAssetId("XYZ707AB2");
        asset.setChannel("ESPN");
        asset.setAssetType("Promo");
        asset.setStartOfMaterial("01:00:00;00");
        asset.setDuration("00:00:30;00");
        asset.setTitle("Just do it - Nike");
        asset.setAirDate(LocalDate.of(2021, 4, 23));
        asset.setClosedCaptioning(true);
        asset.setAudio("5.1");
        asset.setFrameRate("59.94");

        // Act
        var result = controller.create(asset);

        // Assert
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(result.getHeaders().get("Location").get(0), "/media-assets/XYZ707AB2");

        var body = result.getBody();
        assertEquals(body, asset);
    }

    @Test
    public void update_notFound() {
        // Arrange
        var asset = new MediaAsset();
        asset.setAssetId("XYZ707AB2");
        asset.setChannel("ESPN");
        asset.setAssetType("Promo");
        asset.setStartOfMaterial("01:00:00;00");
        asset.setDuration("00:00:30;00");
        asset.setTitle("Just do it - Nike");
        asset.setAirDate(LocalDate.of(2021, 4, 23));
        asset.setClosedCaptioning(true);
        asset.setAudio("5.1");
        asset.setFrameRate("59.94");

        // Act
        var result = controller.update("XYZ707AB2", asset);

        // Assert
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void update() {
        // Arrange
        var asset1 = new MediaAsset();
        asset1.setAssetId("XYZ707AB2");
        asset1.setChannel("ESPN");
        asset1.setAssetType("Promo");
        asset1.setStartOfMaterial("01:00:00;00");
        asset1.setDuration("00:00:30;00");
        asset1.setTitle("Just do it - Nike");
        asset1.setAirDate(LocalDate.of(2021, 4, 23));
        asset1.setClosedCaptioning(true);
        asset1.setAudio("5.1");
        asset1.setFrameRate("59.94");
        manager.persistAndFlush(asset1);

        var asset2 = new MediaAsset();
        asset2.setAssetId("XYZ707AB2");
        asset2.setChannel("ABC");
        asset2.setAssetType("Promo");
        asset2.setStartOfMaterial("02:00:00;00");
        asset2.setDuration("00:00:15;00");
        asset2.setTitle("Just do it - Nike");
        asset2.setAirDate(LocalDate.of(2021, 3, 14));
        asset2.setClosedCaptioning(true);
        asset2.setAudio("2.1");
        asset2.setFrameRate("24");

        // Act
        var result = controller.update("XYZ707AB2", asset2);

        // Assert
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);

        var body = result.getBody();
        assertEquals(body, asset2);
    }

    @Test
    public void delete_notFound() {
        // Arrange

        // Act
        var result = controller.delete("XYZ707AB2");

        // Assert
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void delete() {
        // Arrange
        var asset = new MediaAsset();
        asset.setAssetId("XYZ707AB2");
        asset.setChannel("ESPN");
        asset.setAssetType("Promo");
        asset.setStartOfMaterial("01:00:00;00");
        asset.setDuration("00:00:30;00");
        asset.setTitle("Just do it - Nike");
        asset.setAirDate(LocalDate.of(2021, 4, 23));
        asset.setClosedCaptioning(true);
        asset.setAudio("5.1");
        asset.setFrameRate("59.94");
        manager.persistAndFlush(asset);

        // Act
        var result = controller.delete("XYZ707AB2");

        // Assert
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    // Assert two MediaAssets are equal ignoring their ids.
    private void assertEquals(MediaAsset a1, MediaAsset a2) {
        Assertions.assertEquals(a1.getAssetId(), a2.getAssetId());
        Assertions.assertEquals(a1.getChannel(), a2.getChannel());
        Assertions.assertEquals(a1.getAssetType(), a2.getAssetType());
        Assertions.assertEquals(a1.getStartOfMaterial(), a2.getStartOfMaterial());
        Assertions.assertEquals(a1.getDuration(), a2.getDuration());
        Assertions.assertEquals(a1.getTitle(), a2.getTitle());
        Assertions.assertEquals(a1.getAirDate(), a2.getAirDate());
        Assertions.assertEquals(a1.isClosedCaptioning(), a2.isClosedCaptioning());
        Assertions.assertEquals(a1.getAudio(), a2.getAudio());
        Assertions.assertEquals(a1.getFrameRate(), a2.getFrameRate());
    }

}
