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

``` bash
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

## SignUp

### Endpoint URL

http://10.4.41.56/RevPollution/services/signup/signup

### Query parameters

|Name | Type | Description |
|---|---|---|
| username | String | Username of the user who wants to log in. |
| password | String | Password of the user who wants to log in. |
| name | String | Name of the user who wants to log in. |
| email | String | Email of the user who wants to log in. |
| telf | String | Phone number of the user who wants to log in. |
| image | String | Profile picture of the user who wants to log in. |

### Examples

Curl:

``` bash
curl -X POST "http://10.4.41.56/RevPollution/services/signup/signup?username=username&password=password&name=name&email=email&telf=telf&image=image"
```

Dart:

``` Dart
var request = http.Request('POST', Uri.parse('http://10.4.41.56/RevPollution/services/signup/signup?username=username&password=password&name=name&email=email&telf=telf&image=image'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

## UserInfo

### Endpoint URL

http://10.4.41.56/RevPollution/services/userinfo?token=token

### Query parameters

|Name | Type | Description |
|---|---|---|
| token | String | Token of the user we want to get information from. |

### Examples

Curl:

```
curl -X GET "http://10.4.41.56/RevPollution/services/userinfo?token=token"
```

Dart:

``` Dart
var request = http.Request('GET', Uri.parse('http://10.4.41.56/RevPollution/services/userinfo?token=token'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

## Stations

### Get all of the activated stations

#### Endpoint URL

http://10.4.41.56/RevPollution/services/stations

#### Query parameters

None

#### Examples

Curl:

``` bash
curl -X GET "http://10.4.41.56/RevPollution/services/stations"
```

Dart:

``` Dart
var request = http.Request('GET', Uri.parse('http://10.4.41.56/RevPollution/services/stations'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

### Get information about one station (has to be activated)

#### Endpoint URL

http://10.4.41.56/RevPollution/services/stations/info

#### Query parameters

|Name | Type | Description |
|---|---|---|
| idStation | Integer | ID of the station we want to get the information about. |

#### Examples

Curl:

``` bash
curl -X GET "http://10.4.41.56/RevPollution/services/stations/info?idStation=idStation"
```

Dart:

``` Dart
var request = http.Request('GET', Uri.parse('http://10.4.41.56/RevPollution/services/stations/info?idStation=idStation'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

## StationsAdmin

### Get all of the stations

#### Endpoint URL

http://10.4.41.56/RevPollution/services/stationsAdmin

#### Query parameters

None

#### Examples

Curl:

``` bash
curl -X GET "http://10.4.41.56/RevPollution/services/stationsAdmin"
```

Dart:

``` Dart
var request = http.Request('GET', Uri.parse('http://10.4.41.56/RevPollution/services/stationsAdmin'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

### Get information about one station

#### Endpoint URL

http://10.4.41.56/RevPollution/services/stationsAdmin/info

#### Query parameters

|Name | Type | Description |
|---|---|---|
| idStation | Integer | ID of the station we want to get the information about. |

#### Examples

Curl:

``` bash
curl -X GET "http://10.4.41.56/RevPollution/services/stationsAdmin/info?idStation=idStation"
```

Dart:

``` Dart
var request = http.Request('GET', Uri.parse('http://10.4.41.56/RevPollution/services/stationsAdmin/info?idStation=idStation'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

### Switch the activation state from a station

#### Endpoint URL

http://10.4.41.56/RevPollution/services/stationsAdmin/switchActivation

#### Query parameters

|Name | Type | Description |
|---|---|---|
| idStation | Integer | ID of the station we want to get the information about. |

#### Examples

Curl:

``` bash
curl -X PUT "http://10.4.41.56/RevPollution/services/stationsAdmin/switchActivation?idStation=idStation"
```

Dart:

``` Dart
var request = http.Request('PUT', Uri.parse('http://10.4.41.56/RevPollution/services/stationsAdmin/switchActivation?idStation=idStation'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

## ConcentrationsFromStation

### Endpoint URL

http://10.4.41.56/RevPollution/services/concentrations?idStation=idStation

### Query parameters

|Name | Type | Description |
|---|---|---|
| idStation | Integer | ID of the station we want to get the last concentrations from. |

### Examples

Curl:

``` bash
curl -X GET "http://10.4.41.56/RevPollution/services/concentrations?idStation=idStation"
```

Dart:

``` Dart
var request = http.Request('GET', Uri.parse('http://10.4.41.56/RevPollution/services/concentrations?idStation=idStation'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

## ElektroGo

### Endpoint URL

http://10.4.41.56/RevPollution/services/elektrogo?lat=lat&lon=lon

### Query parameters

|Name | Type | Description |
|---|---|---|
| lat | double | Latitude of the location we want to look up. |
| lon | double | Longitude of the location we want to look up. |

### Examples

Curl:

``` bash
curl -X GET "http://10.4.41.56/RevPollution/services/elektrogo?lat=lat&lon=lon"
```

Dart:

``` Dart
var request = http.Request('GET', Uri.parse('http://10.4.41.56/RevPollution/services/elektrogo?lat=lat&lon=lon'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

## UserSearches

### Get most recent searches from a user

#### Endpoint URL

http://10.4.41.56/RevPollution/services/userSearches/recentSearches?token=token

#### Query parameters

|Name | Type | Description |
|---|---|---|
| token | String | Token of the user we want to get the most recent searches from. |

#### Examples

Curl:

``` bash
curl -X GET "http://10.4.41.56/RevPollution/services/userSearches/recentSearches?token=token"
```

Dart:

``` Dart
var request = http.Request('GET', Uri.parse('http://10.4.41.56/RevPollution/services/userSearches/recentSearches?token=token'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

### Add a new search to a user

#### Endpoint URL

http://10.4.41.56/RevPollution/services/userSearches/addSearch?token=token&name=name

#### Query parameters

|Name | Type | Description |
|---|---|---|
| token | String | Token of the user we want to add a search from. |
| name | String | Name of the place in the search that wants to be added. |

#### Examples

Curl:

``` bash
curl -X POST "http://10.4.41.56/RevPollution/services/userSearches/addSearch?token=token&name=name"
```

Dart:

``` Dart
var request = http.Request('POST', Uri.parse('http://10.4.41.56/RevPollution/services/userSearches/addSearch?token=token&name=name'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

### Delete a search from a user

#### Endpoint URL

http://10.4.41.56/RevPollution/services/userSearches/deleteSearch?token=token&name=name

#### Query parameters

|Name | Type | Description |
|---|---|---|
| token | String | Token of the user we want to delete a search from. |
| name | String | Name of the place in the search that wants to be deleted. |

#### Examples

Curl:

``` bash
curl -X DELETE "http://10.4.41.56/RevPollution/services/userSearches/deleteSearch?token=token&name=name"
```

Dart:

``` Dart
var request = http.Request('DELETE', Uri.parse('http://10.4.41.56/RevPollution/services/userSearches/deleteSearch?token=token&name=name'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

## DailyExpo

### Get the average daily exposition to pollution of a user in the last 7 days

#### Endpoint URL

http://10.4.41.56/RevPollution/services/dailyExpo?token=token

#### Query parameters

|Name | Type | Description |
|---|---|---|
| token | String | Token of the user we want to get the data from. |

#### Examples

Curl:

``` bash
curl -X GET "http://10.4.41.56/RevPollution/services/dailyExpo?token=token"
```

Dart:

``` Dart
var request = http.Request('GET', Uri.parse('http://10.4.41.56/RevPollution/services/dailyExpo?token=token'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```

### Add an exposition value with the user location

#### Endpoint URL

http://10.4.41.56/RevPollution/services/dailyExpo/addExpo?token=token&lat=lat&lon=lon

#### Query parameters

|Name | Type | Description |
|---|---|---|
| token | String | Token of the user we want to add the data to. |
| lat | Double | Current latitude of the location of the user. |
| lon | Double | Current longitude of the location of the user. |

#### Examples

Curl:

``` bash
curl -X POST "http://10.4.41.56/RevPollution/services/dailyExpo/addExpo?token=token&lat=lat&lon=lon"
```

Dart:

``` Dart
var request = http.Request('POST', Uri.parse('http://10.4.41.56/RevPollution/services/dailyExpo/addExpo?token=token&lat=lat&lon=lon'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```
