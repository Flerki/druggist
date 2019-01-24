# Druggist

## API

for any error HTTP status is 400.

error type:

```
{
	"code" : string,
	"message" : string
}
```

###Registration

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

errors:

in case of existence of username returns code=```AUTH1``` and message=```User with such name is already registered```

in case of existence of email returns code=```AUTH2``` and message=```User with such email is already registered```



