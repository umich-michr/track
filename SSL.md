# Generate an ECC private key

```bash
openssl ecparam -genkey -name prime256v1 -out ./docker/apache/certs/privkey.pem
```

# Generate a self-signed certificate (valid for 2365 days)
```bash
openssl req -new -x509 -key ./docker/apache/certs/privkey.pem -out ./docker/apache/certs/fullchain.pem -days 2365 \
-subj "/C=US/ST=MI/L=Ann Arbor/O=MICHR/CN=localhost"
```
