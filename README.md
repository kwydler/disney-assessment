# Disney Assessment

## Requirements
* Java 11

## Assumptions
* It is possible to update the asset id of a `MediaAsset`.

## Improvements
* Moving `MediaAsset` logic to a dedicated service layer (if it becomes more complicated).
* Add a PATCH operation to allow updating individual fields in `MediaAsset` without the need to pass unaffected fields.

## Running
Windows
```shell
mvnw.cmd spring-boot:run
```

MacOS or Linux
```shell
./mvnw spring-boot:run
```

## Endpoints

### Get All
Returns all `MediaAsset`s. If no `MediaAsset`s are found an empty list is returned.

**JSON**
```shell
curl --request GET \
  --url http://localhost:8080/media-assets \
  --header 'Accept: application/json'
```
```json
[
  {
    "assetId": "XYZ707AB2",
    "channel": "ESPN",
    "assetType": "Promo",
    "duration": "00:00:30;00",
    "title": "Just do it - Nike",
    "airDate": "04/23/2021",
    "audio": "5.1",
    "frameRate": "59.94",
    "SOM": "01:00:00;00",
    "CC": false
  },
  {
    "assetId": "ABC543DJ1",
    "channel": "ABC",
    "assetType": "Promo",
    "duration": "00:00:15;00",
    "title": "Test",
    "airDate": "03/13/2021",
    "audio": "2.1",
    "frameRate": "24",
    "SOM": "02:00:00;00",
    "CC": true
  }
]
```

**XML**
```shell
curl --request GET \
  --url http://localhost:8080/media-assets \
  --header 'Accept: application/xml'
```
```xml
<List>
  <item>
    <assetId>XYZ707AB2</assetId>
    <channel>ESPN</channel>
    <assetType>Promo</assetType>
    <duration>00:00:30;00</duration>
    <title>Just do it - Nike</title>
    <airDate>04/23/2021</airDate>
    <audio>5.1</audio>
    <frameRate>59.94</frameRate>
    <SOM>01:00:00;00</SOM>
    <CC>false</CC>
  </item>
  <item>
    <assetId>ABC543DJ1</assetId>
    <channel>ABC</channel>
    <assetType>Promo</assetType>
    <duration>00:00:15;00</duration>
    <title>Test</title>
    <airDate>03/13/2021</airDate>
    <audio>2.1</audio>
    <frameRate>24</frameRate>
    <SOM>02:00:00;00</SOM>
    <CC>true</CC>
  </item>
</List>
```

### Get One
Returns a single `MediaAsset` that matches the given asset id. If a matching `MediaAsset` is not found a `404` is 
returned.

**JSON**
```shell
# Replace `{assetId}` with the assets id.
curl --request GET \
  --url http://localhost:8080/media-assets/{assetId} \
  --header 'Accept: application/json'
```
```json
{
  "assetId": "XYZ707AB2",
  "channel": "ESPN",
  "assetType": "Promo",
  "duration": "00:00:30;00",
  "title": "Just do it - Nike",
  "airDate": "04/23/2021",
  "audio": "5.1",
  "frameRate": "59.94",
  "SOM": "01:00:00;00",
  "CC": false
}
```

**XML**
```shell 
curl --request GET \
  --url http://localhost:8080/media-assets/{assetId} \
  --header 'Accept: application/xml'  
```
```xml
<MediaAsset>
  <assetId>XYZ707AB2</assetId>
  <channel>ESPN</channel>
  <assetType>Promo</assetType>
  <duration>00:00:30;00</duration>
  <title>Just do it - Nike</title>
  <airDate>04/23/2021</airDate>
  <audio>5.1</audio>
  <frameRate>59.94</frameRate>
  <SOM>01:00:00;00</SOM>
  <CC>false</CC>
</MediaAsset>
```

### Create
Create a `MediaAsset`. If the `MediaAsset` is successfully created a `201` status is returned along with the created
`MediaAsset` in the body of the response and a `Location` header.

All fields are required. If a field is missing a `400` status is returned.

If a `MediaAsset` already exists with the given asset id a `500` status is returned.

```shell
curl --request POST \
  --url http://localhost:8080/media-assets \
  --header 'Content-Type: application/json' \
  --data '{
	"assetId": "XYZ707AB2",
	"channel": "ESPN",
	"assetType": "Promo",
	"SOM": "01:00:00;00",
	"duration": "00:00:30;00",
	"title": "Just do it - Nike",
	"airDate": "04/23/2021",
	"CC": "false",
	"audio": "5.1",
	"frameRate": "59.94"
}'
```
```
Location: /media-assets/XYZ707AB2
```
```json
{
  "assetId": "XYZ707AB2",
  "channel": "ESPN",
  "assetType": "Promo",
  "duration": "00:00:30;00",
  "title": "Just do it - Nike",
  "airDate": "04/23/2021",
  "audio": "5.1",
  "frameRate": "59.94",
  "SOM": "01:00:00;00",
  "CC": false
}
```

### Update
Update a `MediaAsset`. If the `MediaAsset` is successfully updated a `200` status is returned along with the updated 
`MediaAsset` in the body of the response.

All fields are required, including the `assetId` field. If a field is missing a `400` status is returned.

If a matching `MediaAsset` is not found a `404` is returned.

**Success**
```shell
# Replace `{assetId}` with the assets id.
curl --request PUT \
  --url http://localhost:8080/media-assets/{assetId} \
  --header 'Content-Type: application/json' \
  --data '{
	"assetId": "XYZ707AB2",
	"channel": "ESPN2",
	"assetType": "Promo",
	"SOM": "02:00:00;00",
	"duration": "00:00:15;00",
	"title": "Updated - Just do it - Nike",
	"airDate": "04/23/2021",
	"CC": "true",
	"audio": "5.1",
	"frameRate": "59.94"
}'
```
```json
{
  "assetId": "XYZ707AB2",
  "channel": "ESPN2",
  "assetType": "Promo",
  "duration": "00:00:15;00",
  "title": "Updated - Just do it - Nike",
  "airDate": "04/23/2021",
  "audio": "5.1",
  "frameRate": "60.00",
  "SOM": "02:00:00;00",
  "CC": true
}
```