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

```
curl -X POST "http://10.4.41.56/RevPollution/services/login/login?username=username&password=password&name=name&email=email&telf=telf&image=image"
```

Dart:

``` Dart
var request = http.Request('POST', Uri.parse('http://10.4.41.56/RevPollution/services/login/login?username=username&password=password&name=name&email=email&telf=telf&image=image'));

http.StreamedResponse response = await request.send();

if (response.statusCode == 200) {
  print(await response.stream.bytesToString());
}
else {
  print(response.reasonPhrase);
}
```
