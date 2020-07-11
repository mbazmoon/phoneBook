#!/usr/bin/env bash
echo 'Creating application user and db'
mongo "phone_book_db" \
        --host localhost \
        --port 27017 \
        -u "admin" \
        -p "admin" \
        --authenticationDatabase admin \
        --eval "db.createUser({user: 'phone_book_user', pwd: '123456', roles:[{role:'dbOwner', db: 'phone_book_db'}]});"
