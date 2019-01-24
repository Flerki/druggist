# Druggist

## API

for any error HTTP status is 400.

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
	"code" : "AUTH1",
	"message" : "Wrong password"
}
```