#!/bin/bash
# start SSL server on 8442
# -S
node node_modules/http-server/bin/http-server -p 8442 -S --cert assets/ssl/cert.pem --key assets/ssl/key.pem
