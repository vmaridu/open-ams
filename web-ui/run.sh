#!/bin/bash
# start SSL server on 8443
node node_modules/http-server/bin/http-server -p 8443 -S --cert assets/ssl/cert.pem --key assets/ssl/key.pem
