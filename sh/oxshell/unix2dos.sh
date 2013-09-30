perl -i -p -e 's/\n/\r\n/' $1
sed -i -e 's/$/\r/' $1