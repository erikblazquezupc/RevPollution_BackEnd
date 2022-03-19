# API Documentation

## LogIn

### Endpoint URL

http://10.4.41.56/RevPollution/services/login/login

### Query parameters

|Name | Type | Description |
|---|---|---|
| username | String | Username of the user who wants to log in. |
| password | String | Password of the user who wants to log in. |
### Examples

Curl:

```
curl -X GET "http://10.4.41.56/RevPollution/services/login/login?username=username&password=password"
```

Dart:

``` Dart
var request = http.Request('GET', Uri.parse('http://10.4.41.56/RevPollution/services/login/login?username=username&password=password'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

## Stations

### Endpoint URL

http://10.4.41.56/RevPollution/services/stations/stations

### Query parameters

None

### Examples

Curl:

```
curl -X GET "http://10.4.41.56/RevPollution/services/stations/stations"
```

Dart:

``` Dart
var request = http.Request('GET', Uri.parse('http://10.4.41.56/RevPollution/services/stations/stations'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```