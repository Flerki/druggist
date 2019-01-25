# Druggist

## API

for any error HTTP status is 400.

### Authorization

If endpoint requires authorization, then an ```Authorization``` has to be passed. 

Its value has to be in the following format: ```Bearer jwt```. 

Example,
```Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaXNzIjoiYXV0aDAiLCJsb2dpbiI6ImxvZ2luIiwiZXhwIjoxNTQ4MzYzNzM3LCJlbWFpbCI6ImVtYWlsIn0.NNwYYf4aV2cf10cYD890JeMBafJnz4QZs65BuZXC5Q0```

All endpoints, except ```/register``` and ```/auth```, require authorization.


### Types
#### error 
format :

```
{
	"code" : string,
	"message" : string
}
```

#### jwt

jwt is a string encoded with base64
after decoding:
```
{
	"sub": number, // userId
	"login": string,
	"email": string,
	"exp": number //time in secs, multiply by 1000 to get ms
}
```

#### medicine
format
```
{
	"id" : number,
	"name" : string,
	"expirationDate": string, //dd.mm.yyyy
	"description" : string,
	"categories" : array of categories
}
```


#### category
format:
```
{
	"id" : number,
	"name" : string
}
```


### Endpoints

#### Registration

path: /register

method: POST

request format:

```
{
	"username" : string,
	"email" : string,
	"password" : string,
	"phone" : string (optional)
}
```

response:

success:

```
{
	"jwt" : jwt
}
```

example:

```
{
	"jwt" : "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaXNzIjoiYXV0aDAiLCJsb2dpbiI6ImxvZ2luIiwiZXhwIjoxNTQ4MzYzNzM3LCJlbWFpbCI6ImVtYWlsIn0.NNwYYf4aV2cf10cYD890JeMBafJnz4QZs65BuZXC5Q0"
}
```

errors:

in case of existence of username:

```
{
	"code" : "REG1",
	"message" : "User with such name is already registered"
}
```

in case of existence of email:

```
{
	"code" : "REG2",
	"message" : "User with such email is already registered"
}
```

#### Authentication

path: /auth

method: POST

request format:

```
{
	"email" : string,
	"password" : string,
}
```

response:

success:

```
{
	"jwt" : jwt
}
```

example:


```
{
	"jwt" : "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaXNzIjoiYXV0aDAiLCJsb2dpbiI6ImxvZ2luIiwiZXhwIjoxNTQ4MzYzNzM3LCJlbWFpbCI6ImVtYWlsIn0.NNwYYf4aV2cf10cYD890JeMBafJnz4QZs65BuZXC5Q0"
}
```

errors:

in case of non-existence email:

```
{
	"code" : "AUTH1",
	"message" : "User with such email is not registered"
}
```

in case of wrong password:

```
{
	"code" : "AUTH2",
	"message" : "Wrong password"
}
```

#### Medicine

##### Retrieval

path: /user/{userId}/medicine

method: GET

response format - array of **medicine type**

##### Creation

path: /user/{userId}/medicine

method: POST

request format:

```
{
	"name" : string,
	"expirationDate": string, //dd.mm.yyyy
	"description" : string (optional) ,
	"categories" : array of categories (optional)
}
```

response format:

success:

```
{
	"id" : number // id of the created medicine
}
```

##### Update

path: /user/{userId}/medicine/{medicineId}

method: PUT

request format:

```
{
	"name" : string,
	"expirationDate": string, //dd.mm.yyyy
	"description" : string (optional) ,
	"categories" : array of categories (optional)
}
```

response format 200 for success and 400 for error

##### Removal

path: /user/{userId}/medicine/{medicineId}

method: DELETE

response format 200 for success and 400 for error

#### Category 

##### Retrieval

path: /user/{userId}/category

method: GET

response format - array of **category type**

##### Creation

path: /user/{userId}/category

method: POST

request format:

```
{
	"name" : string,
	"description" : string (optional)
}
```

response format:

```
{
	"id" : number // id of the created category
}
```


##### Removal

path: /user/{userId}/category/{categoryId}

method: DELETE

response format 200 for success and 400 for error
